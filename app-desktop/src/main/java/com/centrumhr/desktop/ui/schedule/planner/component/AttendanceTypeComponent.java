package com.centrumhr.desktop.ui.schedule.planner.component;

import com.centrumhr.desktop.ui.schedule.planner.widget.TimeSpinner;
import com.centrumhr.dto.attendance.AttendanceType;
import com.centrumhr.dto.common.Hour;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Seweryn on 19.02.2017.
 */
public class AttendanceTypeComponent {

    public interface Callback{
        void onSelectAttendanceType(AttendanceType attendanceType, Hour hourFrom, Hour hourTo);
    }

    @FXML Button buttonAttentType;
    @FXML TimeSpinner hourFromSpinner;
    @FXML TimeSpinner hourToSpinner;
    @FXML HBox timePickerContainer;

    private Callback listener;

    private AttendanceType attendanceType;

    @FXML void initialize(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        hourFromSpinner.valueProperty().addListener((obs, oldTime, newTime) -> System.out.println(formatter.format(newTime)));
        buttonAttentType.setOnAction(event -> {
            if(timePickerContainer.isVisible()){
                listener.onSelectAttendanceType( attendanceType,
                        new Hour(hourFromSpinner.getValue().getHour() + ":" + hourFromSpinner.getValue().getMinute()),
                        new Hour(hourToSpinner.getValue().getHour() + ":" + hourToSpinner.getValue().getMinute())
                );
            }else{
                listener.onSelectAttendanceType(attendanceType,null,null);
            }
        });
    }

    public void setData( AttendanceType attendanceType ){
        setData(null,attendanceType,null,null);
    }

    public void setData( String title, AttendanceType attendanceType , Hour hourFrom , Hour hourTo ){
        this.attendanceType = attendanceType;
        this.timePickerContainer.setVisible(attendanceType.canEditHours());
        if(hourFrom!=null){
            Instant instant = Instant.ofEpochMilli(hourFrom.getTime());
            LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
            hourFromSpinner.getValueFactory().setValue(localTime);
        }
        if(hourTo!=null){
            Instant instant = Instant.ofEpochMilli(hourTo.getTime());
            LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
            hourToSpinner.getValueFactory().setValue(localTime);
        }
        this.buttonAttentType.setText(attendanceType.getDesc());
        this.buttonAttentType.setStyle("-fx-background-color:" + attendanceType.getColor() +";");
        if(title!=null){
            this.buttonAttentType.setText(title);
        }
    }

    public void setListener(Callback listener) {
        this.listener = listener;
    }
}
