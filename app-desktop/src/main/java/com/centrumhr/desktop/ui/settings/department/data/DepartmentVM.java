package com.centrumhr.desktop.ui.settings.department.data;

import com.centrumhr.data.model.employment.DepartmentEntity;
import com.centrumhr.dto.employment.DepartmentDTO;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentVM {

    private SimpleStringProperty name;
    private UUID uuid;
    private boolean isSelected;

    public DepartmentVM(DepartmentDTO departmentDTO) {
        this.name = new SimpleStringProperty(departmentDTO.getName());
        this.uuid = departmentDTO.getUniqueId();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name.get();
    }

    public UUID getUuid() {
        return uuid;
    }
}
