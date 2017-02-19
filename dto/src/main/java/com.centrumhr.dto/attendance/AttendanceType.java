package com.centrumhr.dto.attendance;

import com.centrumhr.dto.common.Hour;

/**
 * Created by Seweryn on 07.02.2017.
 */
public enum  AttendanceType {
    NONE(
            "Nie wybrano",
            "","rgba(255, 255, 255, 0.64)"
    ),
    NORMAL_WORK(
            "Praca",
            "P","rgba(76, 175, 80, 0.64)",true,
            new Hour("8:00"),new Hour("16:00"),true),
    MEDICAL_LEAVE(
            "Chorobowe",
            "CH", "rgba(244, 67, 54, 0.64)",
            new Hour("8:00"),new Hour("16:00")
    ),
    FREE_DAY(
            "Odsiadka",
            "O", "rgba(242, 242, 242, 0.64)"
    ),
    VACATION(
            "Urlop",
            "u", "rgba(135, 213, 255, 0.64)",
            new Hour("8:00"),new Hour("16:00")
    ),
    NOT_WORKING(
            "Niepracujący",
            "N", "rgba(244, 67, 54, 0.64)"
    ),;


    AttendanceType(String desc, String label, String color) {
        this(desc,label,color,false,null,null,false);
    }

    AttendanceType(String desc,String label, String color, boolean canEditHours) {
        this(desc,label,color,canEditHours,null,null,false);
    }

    AttendanceType(String desc,String label, String color, Hour selectedFromHour, Hour selectedToHour) {
        this(desc,label,color,false,selectedFromHour,selectedToHour,false);
    }

    AttendanceType(String desc,String label, String color, boolean requiresTime, Hour selectedFromHour, Hour selectedToHour, boolean canEditHours) {
        this.label = label;
        this.desc = desc;
        this.color = color;
        this.requiresTime = requiresTime;
        this.selectedFromHour = selectedFromHour;
        this.selectedToHour = selectedToHour;
        this.canEditHours = canEditHours;
    }

    private String desc;
    private String label;
    private String color;
    private boolean requiresTime;
    private Hour selectedFromHour;
    private Hour selectedToHour;
    private boolean canEditHours;

    public String getDesc() {
        return desc;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }

    public boolean requiresTime() {
        return requiresTime;
    }

    public Hour getSelectedFromHour() {
        return selectedFromHour;
    }

    public Hour getSelectedToHour() {
        return selectedToHour;
    }

    public boolean canEditHours() {
        return canEditHours;
    }
}
