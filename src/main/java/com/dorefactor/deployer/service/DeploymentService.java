package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.domain.error.Error;
import com.dorefactor.deployer.domain.error.ServiceError;
import com.dorefactor.deployer.domain.model.DeploymentOrder;
import com.dorefactor.deployer.domain.model.DeploymentTemplate;
import com.dorefactor.deployer.domain.model.Host;
import com.dorefactor.deployer.domain.model.HostSetup;
import com.dorefactor.deployer.domain.service.DeploymentHost;
import com.dorefactor.deployer.domain.service.DeploymentRequest;
import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class DeploymentService {

    private static final Logger logger = LoggerFactory.getLogger(DeploymentService.class);
    
    private final DeploymentOrderDao deploymentOrderDao;
    private final DeploymentTemplateDao deploymentTemplateDao;
    private final DeploymentOrderProducer deploymentOrderProducer;
    private final Gson gson;

    public DeploymentService(DeploymentOrderDao deploymentOrderDao,
                             DeploymentTemplateDao deploymentTemplateDao,
                             DeploymentOrderProducer deploymentOrderProducer,
                             Gson gson) {

        this.deploymentOrderDao = deploymentOrderDao;
        this.deploymentTemplateDao = deploymentTemplateDao;
        this.deploymentOrderProducer = deploymentOrderProducer;
        this.gson = gson;
    }

    public Either<Error, DeploymentOrder> queueDeploymentOrder(DeploymentRequest deploymentRequest) {

        try {
            logger.info("processing deployment request: {}", deploymentRequest);
            var deploymentTemplateHolder = deploymentTemplateDao.getByName(deploymentRequest.getTemplateName());

            if (deploymentTemplateHolder.isEmpty()) {

                logger.error("deployment template: {} was not found", deploymentRequest.getTemplateName());
                return Either.left(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);
            }

            var deploymentOrder = buildDeploymentOrder(deploymentRequest, deploymentTemplateHolder.get());
            deploymentOrderDao.save(deploymentOrder);
            deploymentOrderProducer.produce(gson.toJson(deploymentOrder));

            logger.info("deployment order: {} saved and queued", deploymentOrder);

            return Either.right(deploymentOrder);
        } catch (Exception ex) {

            logger.error("can't queue deployment order: {} - ", deploymentRequest, ex);
            return Either.left(ServiceError.ERROR_PROCESSING);
        }
    }

    // ------------------------------------------------------------------------------------------------------

    private static String buildRequestId() {

        return UUID.randomUUID().toString();
    }

    private static DeploymentOrder buildDeploymentOrder(DeploymentRequest deploymentRequest, DeploymentTemplate deploymentTemplate) {

        var deploymentOrder = new DeploymentOrder();
        deploymentOrder.setDeploymentTemplate(deploymentTemplate);
        deploymentOrder.setRequestId(buildRequestId());
        deploymentOrder.setVersion(deploymentRequest.getVersion());
        deploymentOrder.setCreatedAt(LocalDateTime.now()); // check if localDateTime is a good idea
        deploymentOrder.setApplication(deploymentTemplate.getApplication());
        deploymentOrder.setHostsSetup(buildHostSetupList(deploymentRequest.getDeploymentHosts(), deploymentTemplate.getHostsSetup()));

        return deploymentOrder;
    }

    static List<HostSetup> buildHostSetupList(List<DeploymentHost> hosts, List<HostSetup> hostsSetup) {

        if (isNull(hosts) || hosts.isEmpty()) {

            return hostsSetup;
        }

        var matchingDeploymentHosts = getMatchingHostsByTag(hosts, hostsSetup);

        List<HostSetup> hostSetupList = Lists.newArrayList();

        matchingDeploymentHosts.forEach(host -> {
            var setup = new HostSetup();
            setup.setTag(host.getTag());
            setup.setHosts(buildHostList(host.getTag(), host.getHosts(), hostsSetup));

            hostSetupList.add(setup);
        });

        return hostSetupList;
    }

    private static List<DeploymentHost> getMatchingHostsByTag(List<DeploymentHost> hosts, List<HostSetup> hostsSetup) {

        return hosts.stream().filter(host ->
                hostsSetup.stream()
                        .map(HostSetup::getTag)
                        .anyMatch(host.getTag()::equals)
        ).collect(Collectors.toList());
    }

    private static Optional<Host> findHostsByTagAndName(String tag, String name, List<HostSetup> hostsSetup) {

        return hostsSetup.stream().filter(hostSetup -> hostSetup.getTag().equals(tag))
                .flatMap(hostSetup -> hostSetup.getHosts().stream())
                .filter(host -> host.getIp().equals(name))
                .findFirst();
    }

    private static List<Host> buildHostList(String tag, List<String> hostNameList, List<HostSetup> hostsSetup) {

        List<Host> hostList = Lists.newArrayList();
        hostNameList.forEach(hostName -> {
            findHostsByTagAndName(tag, hostName, hostsSetup)
                    .ifPresent(h -> hostList.add(h));
        });

        return hostList;
    }
}