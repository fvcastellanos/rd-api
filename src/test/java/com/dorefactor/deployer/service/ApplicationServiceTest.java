package com.dorefactor.deployer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.dao.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import com.dorefactor.deployer.service.model.ServiceError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ApplicationServiceTest {

    @Mock
    private ApplicationDao applicationDao;

    private ApplicationService applicationService;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        applicationService = new ApplicationService(applicationDao);
    }

    @Test
    public void testAddApplication() {

        var app = ModelFixture.buildDockerApplication();

        expectAppNameNotFound();
        expectStoredApplication();

        var result = applicationService.addApplication(app);

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();

        verify(applicationDao).getByName(app.getName());
        verify(applicationDao).save(app);
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    public void testAddExistingNameApplication() {

        var app = ModelFixture.buildDockerApplication();

        expectExistingApp();

        var result = applicationService.addApplication(app);

        assertThat(result).isNotNull();
        assertTrue(result.isLeft());

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.APPLICATION_ALREADY_EXITS);

        verify(applicationDao).getByName(anyString());
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    public void testAddApplicationThrowsException() {

        var app = ModelFixture.buildDockerApplication();

        expectFindAppByNameThrowsException();

        var result = applicationService.addApplication(app);

        assertThat(result).isNotNull();
        assertTrue(result.isLeft());

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(applicationDao).getByName(anyString());
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    public void testGetAll() {

        expectApplicationList();

        var result = applicationService.getApplications();

        assertThat(result).isNotNull();
        assertTrue(result.isRight());

        verify(applicationDao).getAll();
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    public void testGetAllThrowsException() {

        expectApplicationListThrowsException();

        var result = applicationService.getApplications();

        assertThat(result).isNotNull();
        assertTrue(result.isLeft());

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(applicationDao).getAll();
        verifyNoMoreInteractions(applicationDao);
    }
    
    // -----------------------------------------------------------------------------

    private void expectStoredApplication() {

        var app = ModelFixture.buildDockerApplication();        
        app.setId(ModelFixture.buildRandomObjectId());

        doReturn(app)
            .when(applicationDao).save(any(Application.class));
    }

    private void expectAppNameNotFound() {

        doReturn(Optional.empty())
            .when(applicationDao).getByName(anyString());
    }

    private void expectExistingApp() {

        doReturn(Optional.ofNullable(ModelFixture.buildDockerApplication()))
            .when(applicationDao).getByName(anyString());
    }

    private void expectFindAppByNameThrowsException() {

        doThrow(new RuntimeException("expected exception"))
            .when(applicationDao).getByName(anyString());
    }

    private void expectApplicationList() {

        var apps = Collections.singletonList(ModelFixture.buildDockerApplication());

        doReturn(apps)
            .when(applicationDao).getAll();
    }

    private void expectApplicationListThrowsException() {

        doThrow(new RuntimeException("expected exception"))
            .when(applicationDao).getAll();
    }
}