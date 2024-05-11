package dev.elias.jsonapi.demo.entity;

public class ResponseInterface {
    private String responseKeyField;

    public ResponseInterface() {}

    public ResponseInterface(String responseKeyField) {
        this.responseKeyField = responseKeyField;
    }

    public String getResponseKeyField() {
        return responseKeyField;
    }

    public void setResponseKeyField(String responseKeyField) {
        this.responseKeyField = responseKeyField;
    }

}
