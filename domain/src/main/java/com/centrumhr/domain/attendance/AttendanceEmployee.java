package com.centrumhr.domain.attendance;

import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.employment.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 07.02.2017.
 */
public class AttendanceEmployee {

    private  UUID           uniqueId;
    private  UUID           employeeUniqueId;
    private  String         name;
    private  String         code;

    private List<String>    errors;

    public AttendanceEmployee(UUID employeeUniqueId, String name, String code) {
        this(UUID.randomUUID(),employeeUniqueId,name,code);
    }

    AttendanceEmployee(UUID uniqueId, UUID employeeUniqueId, String name, String code) {
        this.uniqueId = uniqueId;
        this.employeeUniqueId = employeeUniqueId;
        this.name = name;
        this.code = code;
        this.errors = new ArrayList<>();
    }

    public AttendanceEmployeeDTO convert(){
        return new AttendanceEmployeeDTO( uniqueId, name, code, employeeUniqueId );
    }

    public UUID getEmployeeUniqueId() {
        return employeeUniqueId;
    }

    void clearErrors() {
        this.errors.clear();
    }

    void addError(String errorMessage) {
        this.errors.add(errorMessage);
    }

    UUID uniqueId() {
        return uniqueId;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public int maxWorkingHours() {
        return 160;
    }
}
