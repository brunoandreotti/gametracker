package com.brunoandreotti.gametracker.domain.models.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole existsValue(String value) throws IllegalAccessException {

        for (UserRole role : UserRole.values()) {
             if (role.name().equalsIgnoreCase(value)) {
                 return role;
             }
        }
        throw new IllegalAccessException("Enum invalido");
    }
}
