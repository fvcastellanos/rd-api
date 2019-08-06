package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.DeploymentTemplateDao;
import com.dorefactor.deployer.dao.model.DeploymentTemplate;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.service.model.ServiceError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class DeploymentTemplateServiceTest {

    @Mock
    private DeploymentTemplateDao deploymentTemplateDao;
    private DeploymentTemplateService deploymentTemplateService;

    private DeploymentTemplate deploymentTemplate;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        deploymentTemplateService = new DeploymentTemplateService(deploymentTemplateDao);

        deploymentTemplate = ModelFixture.builDeploymentTemplate();
    }

    @Test
    public void testAddDeploymentTemplate() {

        expectNoExistingDeploymentTemplate();

        doReturn(deploymentTemplate)
            .when(deploymentTemplateDao).save(deploymentTemplate);

        var result = deploymentTemplateService.add(deploymentTemplate);

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verify(deploymentTemplateDao).save(deploymentTemplate);

        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testWhenAddDeploymentTemplateAndAlreadyExists() {

        expectExistingDeploymentTemplate();

        var result = deploymentTemplateService.add(deploymentTemplate);

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.DEPLOYMENT_TEMPLATE_ALREADY_EXISTS);

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testWhenAddDeploymentTemplateAnExceptionIsThrown() {

        expectException();

        var result = deploymentTemplateService.add(deploymentTemplate);

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testGetByName() {

        expectExistingDeploymentTemplate();
        var name = deploymentTemplate.getName();

        var result = deploymentTemplateService.getByName(name);

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).isEqualTo(deploymentTemplate);

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testGetByNameNotFound() {

        expectNoExistingDeploymentTemplate();

        var name = deploymentTemplate.getName();

        var result = deploymentTemplateService.getByName(name);

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.DEPLOYMENT_TEMPLATE_NOT_FOUND);

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testGetByNameThrownException() {

        expectException();
        var name = deploymentTemplate.getName();

        var result = deploymentTemplateService.getByName(name);

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(deploymentTemplateDao).getByName(deploymentTemplate.getName());
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testGetAll() {

        doReturn(Collections.singletonList(deploymentTemplate))
                .when(deploymentTemplateDao).getAll();

        var result = deploymentTemplateService.getAll();

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();
        assertThat(result.get()).contains(deploymentTemplate);

        verify(deploymentTemplateDao).getAll();
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    @Test
    public void testGetAllThrowsException() {

        doThrow(new RuntimeException("expected exception"))
                .when(deploymentTemplateDao).getAll();

        var result = deploymentTemplateService.getAll();

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(deploymentTemplateDao).getAll();
        verifyNoMoreInteractions(deploymentTemplateDao);
    }

    // --------------------------------------------------------------------------------------------------

    private void expectNoExistingDeploymentTemplate() {

        doReturn(Optional.empty())
            .when(deploymentTemplateDao).getByName(deploymentTemplate.getName());
    }

    private void expectExistingDeploymentTemplate() {

        doReturn(Optional.of(deploymentTemplate))
                .when(deploymentTemplateDao).getByName(deploymentTemplate.getName());
    }

    private void expectException() {

        doThrow(new RuntimeException("expected exception"))
                .when(deploymentTemplateDao).getByName(deploymentTemplate.getName());
    }
}
