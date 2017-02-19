package com.centrumhr.application.employee.dto;

import java.io.Serializable;

/**
 * Created by Seweryn on 25.10.2016.
 */
public class PersonalDataDTO implements Serializable{

    private String firstName;
    private String lastName;
    private String code;

    public PersonalDataDTO(String firstName, String lastName, String code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
