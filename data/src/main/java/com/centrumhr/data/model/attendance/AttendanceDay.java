package com.centrumhr.data.model.attendance;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Seweryn on 15.10.2016.
 */
public class AttendanceDay {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private int day;

    @DatabaseField
    private AttendanceType type;

    @DatabaseField
    private Date planStartHour;

    @DatabaseField
    private Date planEndHour;

    @DatabaseField
    private Date attendStartHour;

    @DatabaseField
    private Date attendEndHour;

    @DatabaseField
    private String description;

    @DatabaseField(foreign = true)
    private AttendanceEmployee employee;

    public AttendanceDay(){}

    public AttendanceDay(int day) {
        this.uniqueId = UUID.randomUUID();
        this.day = day;
    }
}
