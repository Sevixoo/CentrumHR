package com.centrumhr.domain.attendance.validation;

import com.centrumhr.domain.attendance.AttendanceDay;
import com.centrumhr.domain.attendance.AttendanceEmployee;

/**
 * Created by Seweryn on 08.02.2017.
 */
public abstract class EmployeeValidator {

    private String errorMessage;

    public EmployeeValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public abstract boolean isValid(AttendanceEmployee employee, AttendanceDay[] days);

}
