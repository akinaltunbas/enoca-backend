package com.example.enocabackend.dto.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {

    DEPARTMENT_CREATED("Department created"),
    DEPARTMENT_UPDATED("Department updated"),
    DEPARTMENT_DELETED("Department deleted"),

    EMPLOYEE_CREATED("Employee created"),
    EMPLOYEE_UPDATED("Employee updated"),
    EMPLOYEE_DELETED("Employee deleted"),

    LOGIN_SUCCESS("Login Successful"),
    USER_IN_USE("Username already in use."),
    REGISTERED("User successfully registered."),
    TOKEN_REFRESHED("Token successfully refreshed."),
    TOKEN_NOT_VALID("Refresh token is not valid.");


    private final String Message;

}
