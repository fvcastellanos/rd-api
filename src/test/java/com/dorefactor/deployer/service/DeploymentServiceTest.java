package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.DeploymentOrderDao;
import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.domain.error.ServiceError;
import com.dorefactor.deployer.domain.model.DeploymentOrder;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.fixture.ServiceModelFixture;
import com.dorefactor.deployer.message.DeploymentOrderProducer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DeploymentServiceTest {

    private Gson gson;

    @Mock
    private DeploymentOrderDao deploymentOrderDao;

    @Mock
    private DeploymentTemplateDao deploymentTemplateDao;

    @Mock
    private DeploymentOrderProducer deploymentOrderProducer;

    private DeploymentService deploymentService;

    private String templateName = "test-template";


    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        gson = new GsonBuilder().create();
        deploymentService = new DeploymentService(deploymentOrderDao, deploymentTemplateDao, deploymentOrderProducer, gson);
    }

    @Test
    public void testDeploymentRequestIsQueued() {

        var request = ServiceModelFixture.buildDeploymentRequest(templateName);

        expectDeploymentTemplate();
        expectSuccessDeploymentOrderStorage();
        expectSuccessMessageProduced();

        var result = deploymentService.queueDeploymentOrder(request);

        assertThat(result.isRight()).isTrue();

        verify(deploymentTemplateDao).getByName(templateName);
        verify(deploymentOrderDao).save(any(DeploymentOrder.class));
        verify(deploymentOrderProducer).produce(anyString());

        verifyNoMoreInteractions(deploymentTemplateDao, deploymentOrderDao, deploymentOrderProducer);
    }

    @Test
    public void testWhenDeploymentTemplateNotFoundReturnError() {

        var deploymentRequest = ServiceModelFixture.buildDeploymentRequest(templateName);

        expectDeploymentTemplateNotFound();

        var result = deploymentService.queueDeploymentOrder(deploymentRequest);

        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);

        verify(deploymentTemplateDao).getByName(templateName);
        verifyZeroInteractions(deploymentOrderDao, deploymentOrderProducer);
    }

    @Test
    public void testWhenExceptionIsThrownReturnError() {

        var deploymentRequest = ServiceModelFixture.buildDeploymentRequest(templateName);

        expectDeploymentTemplate();
        expectSuccessDeploymentOrderStorage();
        expectMessageProductionWithException();

        var result = deploymentService.queueDeploymentOrder(deploymentRequest);

        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(deploymentTemplateDao).getByName(templateName);
        verify(deploymentOrderDao).save(any(DeploymentOrder.class));
        verify(deploymentOrderProducer).produce(anyString());

        verifyNoMoreInteractions(deploymentTemplateDao, deploymentOrderDao, deploymentOrderProducer);
    }

    // ------------------------------------------------------------------------------------------------------

    private void expectDeploymentTemplate() {

        var template = ModelFixture.buildDeploymentTemplate();
        doReturn(Optional.of(template))
                .when(deploymentTemplateDao).getByName(templateName);
    }

    private void expectDeploymentTemplateNotFound() {

        doReturn(Optional.empty())
                .when(deploymentTemplateDao).getByName(templateName);
    }

    private void expectSuccessDeploymentOrderStorage() {

        var deploymentOrder = ModelFixture.buildDeploymentOrder();
        doReturn(deploymentOrder).when(deploymentOrderDao).save(any(DeploymentOrder.class));
    }

    private void expectSuccessMessageProduced() {

        doNothing().when(deploymentOrderProducer).produce(anyString());
    }

    private void expectMessageProductionWithException() {

        doThrow(new RuntimeException("expected exception"))
                .when(deploymentOrderProducer).produce(anyString());
    }
}
