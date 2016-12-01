package com.centrumhr.desktop.ui.schedule.creator.data;

import com.centrumhr.data.model.employment.Employee;
import javafx.beans.property.SimpleStringProperty;

import java.util.UUID;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EmployeeVM {

    private SimpleStringProperty name;
    private SimpleStringProperty code;
    private UUID uuid;

    public EmployeeVM(Employee employee) {
        uuid = employee.getUniqueId();
        name = new SimpleStringProperty(employee.getName());
        code = new SimpleStringProperty(employee.getCode());
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name.get();
    }

    public String getCode() {
        return code.get();
    }

}
