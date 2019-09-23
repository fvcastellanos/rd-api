package com.dorefactor.deployer.service;

import com.dorefactor.deployer.dao.ApplicationDao;
import com.dorefactor.deployer.domain.error.ServiceError;
import com.dorefactor.deployer.domain.model.Application;
import com.dorefactor.deployer.fixture.ModelFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class ApplicationServiceTest {

    @Mock
    private ApplicationDao applicationDao;

    private ApplicationService applicationService;

    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        applicationService = new ApplicationService(applicationDao);
    }

    @Test
    void testAddApplication() {

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
    void testAddExistingNameApplication() {

        var app = ModelFixture.buildDockerApplication();

        expectExistingApp();

        var result = applicationService.addApplication(app);

        assertThat(result).isNotNull();
        assertThat(result.isLeft());

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.APPLICATION_ALREADY_EXITS);

        verify(applicationDao).getByName(anyString());
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testAddApplicationThrowsException() {

        var app = ModelFixture.buildDockerApplication();

        expectFindAppByNameThrowsException();

        var result = applicationService.addApplication(app);

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(applicationDao).getByName(anyString());
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testGetAll() {

        expectApplicationList();

        var result = applicationService.getApplications();

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();

        verify(applicationDao).getAll();
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testGetAllThrowsException() {

        expectApplicationListThrowsException();

        var result = applicationService.getApplications();

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();

        var error = result.getLeft();
        assertThat(error).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(applicationDao).getAll();
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testGetApplication() {

        expectExistingApp();

        var result = applicationService.getApplication("test-app");

        assertThat(result).isNotNull();
        assertThat(result.isRight()).isTrue();

        verify(applicationDao).getByName("test-app");
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testGetApplicationNotFound() {

        expectAppNameNotFound();

        var result = applicationService.getApplication("test-app");

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.APPLICATION_NOT_FOUND);

        verify(applicationDao).getByName("test-app");
        verifyNoMoreInteractions(applicationDao);
    }

    @Test
    void testGetApplicationThrowsException() {

        expectFindAppByNameThrowsException();

        var result = applicationService.getApplication("test-app");

        assertThat(result).isNotNull();
        assertThat(result.isLeft()).isTrue();
        assertThat(result.getLeft()).isEqualTo(ServiceError.ERROR_PROCESSING);

        verify(applicationDao).getByName("test-app");
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
