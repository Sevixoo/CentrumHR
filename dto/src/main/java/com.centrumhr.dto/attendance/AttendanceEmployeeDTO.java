package com.centrumhr.dto.attendance;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class AttendanceEmployeeDTO {

    private UUID uniqueId;
    private String name;
    private String code;
    private UUID employeeUniqueId;

    public AttendanceEmployeeDTO(UUID uniqueId, String name, String code, UUID employeeUniqueId ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.code = code;
        this.employeeUniqueId = employeeUniqueId;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public UUID getEmployeeUniqueId() {
        return employeeUniqueId;
    }
}
