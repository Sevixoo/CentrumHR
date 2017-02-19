package com.centrumhr.desktop.ui.schedule.data;

import com.centrumhr.domain.attendance.ICalendarService;
import com.centrumhr.domain.schedule.AttendanceEmployeeSummary;
import com.centrumhr.dto.attendance.AttendanceEmployeeDTO;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import java.util.*;

/**
 * Created by Seweryn on 13.02.2017.
 */
public class AttendancePlanVM {

    private String name;
    private UUID uniqueId;
    private List<AttendanceEmployeeVM> employees;

    public AttendancePlanVM(AttendancePlanDTO attendancePlanDTO, ICalendarService calendarService, HashMap<UUID, AttendanceEmployeeSummary> summary) {
        this.name = attendancePlanDTO.getName();
        this.uniqueId = attendancePlanDTO.getUniqueId();
        this.employees = new ArrayList<>();
        for (int i = 0; i < attendancePlanDTO.getEmployees().length; i++) {
            AttendanceEmployeeDTO employeeDTO = attendancePlanDTO.getEmployees()[i];
            AttendanceEmployeeVM employeeVM = new AttendanceEmployeeVM(employeeDTO,attendancePlanDTO.getDays()[i],calendarService);
            employeeVM.setSummary(summary.get(employeeVM.uniqueId));
            this.employees.add(employeeVM);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<AttendanceEmployeeVM> getEmployees() {
        return employees;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }
}
