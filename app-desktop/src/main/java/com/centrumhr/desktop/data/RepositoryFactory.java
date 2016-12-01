package com.centrumhr.desktop.data;

import com.centrumhr.data.domain.*;
import com.centrumhr.data.orm.IORMLiteDataBase;
import com.centrumhr.data.orm.UnitOfWork;
import com.centrumhr.data.repository.*;
import com.centrumhr.data.repository.EmployeeRepository;

/**
 * Created by Seweryn on 28.09.2016.
 */
public class RepositoryFactory {

    private UnitOfWork unitOfWork;

    public RepositoryFactory(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    public IEmployeeRepository createEmployeeRepository(){
        return new EmployeeRepository(unitOfWork);
    }

    public IDepartmentRepository createDepartmentRepository(){
        return new DepartmentRepository(unitOfWork);
    }

    public IWorkFunctionRepository createWorkFunctionRepository(){
        return new WorkFunctionRepository(unitOfWork);
    }

    public IEmployeeDepartmentRepository createEmployeeDepartmentRepository(){
        return new EmployeeDepartmentRepository(unitOfWork);
    }

    public IAttendancePlanRepository createAttendancePlanRepository(){
        return new AttendancePlanRepository(unitOfWork);
    }

    public IAttendanceDayRepository createAttendanceDayRepository(){
        return new AttendanceDayRepository(unitOfWork);
    }

    public IAttendanceEmployeeRepository createAttendanceEmployeeRepository(){
        return new AttendanceEmployeeRepository(unitOfWork);
    }

}

