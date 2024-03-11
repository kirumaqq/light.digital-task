package io.umid.supportservice.model;

public enum Roles {

    USER, OPERATOR, ADMIN;

    public String withRolePrefix() {
        return "ROLE_" + this.name();
    }
}
