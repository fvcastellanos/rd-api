package com.dorefactor.deployer.service.model;

public enum ServiceError implements Error {
    
    APPLICATION_WRITE_ERROR("Application can't be stored now, please try again later"),
    APPLICATION_ALREADY_EXITS("Application name already exists"),

    ERROR_PROCESSING("Can't process operation")


    ;

    private String message;

    private ServiceError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}