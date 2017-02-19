package com.centrumhr.desktop.ui.employee.data;

import com.centrumhr.dto.employment.WorkFunctionDTO;

import java.util.UUID;

/**
 * Created by Seweryn on 13.02.2017.
 */
public class WorkFunctionVM {

    private String name;
    private UUID uniqueId;

    public static WorkFunctionVM create( WorkFunctionDTO workFunctionDTO ){
        if(workFunctionDTO==null)return null;
        return new WorkFunctionVM(workFunctionDTO);
    }

    public WorkFunctionVM( WorkFunctionDTO workFunctionDTO ) {
        this.name = workFunctionDTO.getName();
        this.uniqueId = workFunctionDTO.getUniqueId();
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof WorkFunctionVM){
            return ((WorkFunctionVM)obj).getUniqueId().equals(uniqueId);
        }
        return false;
    }
}
