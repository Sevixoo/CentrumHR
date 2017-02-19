package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.dto.attendance.AttendancePlanDTO;
import com.centrumhr.dto.attendance.PlanState;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class ScheduleVM {

    private UUID uuid;
    private SimpleStringProperty name;
    private SimpleStringProperty state;
    private SimpleStringProperty createDate;
    private boolean isCommitted;

    public ScheduleVM(AttendancePlanDTO attendancePlanDTO) {
        this.uuid = attendancePlanDTO.getUniqueId();
        this.name = new SimpleStringProperty(attendancePlanDTO.getName());
        this.state = new SimpleStringProperty(attendancePlanDTO.getState().name());
        this.createDate = new SimpleStringProperty(new SimpleDateFormat("dd MMMM hh:mm", Locale.getDefault()).format(attendancePlanDTO.getCreateDate()));;
        this.isCommitted = attendancePlanDTO.getState() == PlanState.CLOSED;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public SimpleStringProperty createDateProperty() {
        return createDate;
    }

    public boolean isCommitted() {
        return isCommitted;
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }
}
