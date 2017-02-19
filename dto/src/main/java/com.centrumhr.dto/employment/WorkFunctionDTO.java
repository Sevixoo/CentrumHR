package com.centrumhr.dto.employment;

import java.util.UUID;

/**
 * Created by Seweryn on 06.02.2017.
 */
public class WorkFunctionDTO {

    private UUID uniqueId;
    private String name;

    public WorkFunctionDTO(UUID uniqueId, String name) {
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
