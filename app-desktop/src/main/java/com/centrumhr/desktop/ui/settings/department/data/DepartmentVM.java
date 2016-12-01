package com.centrumhr.desktop.ui.settings.department.data;

import com.centrumhr.data.model.employment.Department;
import javafx.beans.property.SimpleStringProperty;

import java.util.UUID;

/**
 * Created by Seweryn on 23.10.2016.
 */
public class DepartmentVM {

    private SimpleStringProperty name;
    private UUID uuid;

    public DepartmentVM(Department department) {
        this.name = new SimpleStringProperty(department.getName());
        this.uuid = department.getUniqueId();
    }

    public String getName() {
        return name.get();
    }

    public UUID getUuid() {
        return uuid;
    }
}
