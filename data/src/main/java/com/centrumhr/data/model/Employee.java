package com.centrumhr.data.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
@DatabaseTable(tableName = "employee")
public class Employee {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField( dataType = DataType.UUID , unique = true)
    private UUID uniqueId;

    @DatabaseField
    private String firstName;

    @DatabaseField
    private String surname;

    @DatabaseField
    private String code;

    public Employee() {
        this.uniqueId = UUID.randomUUID();
    }

    public Employee(String firstName, String surname, String code) {
        this.uniqueId = UUID.randomUUID();
        this.firstName = firstName;
        this.surname = surname;
        this.code = code;
    }

    public void update( Employee employee ){
        this.firstName = employee.firstName;
        this.surname = employee.surname;
        this.code = employee.code;
    }

    public String getName(){
        return firstName + " " + surname;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getCode() {
        return code;
    }
}
