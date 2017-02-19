package com.centrumhr.dto.employment;

import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class DepartmentDTO {

    private UUID uniqueId;
    private String name;

    public DepartmentDTO(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }
}
