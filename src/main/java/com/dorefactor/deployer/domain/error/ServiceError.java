package com.dorefactor.deployer.domain.error;

public enum ServiceError implements Error {
    
    APPLICATION_ALREADY_EXITS("Application name already exists"),
    APPLICATION_NOT_FOUND("Application not found"),

    DEPLOYMENT_TEMPLATE_ALREADY_EXISTS("Deployment template name already exists"),
    DEPLOYMENT_TEMPLATE_NOT_FOUND("Deployment template name not found"),

    ERROR_PROCESSING("Can't process operation")
    ;

    private String message;

    ServiceError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}