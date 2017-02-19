package com.centrumhr.application.employee;

import com.centrumhr.application.core.UseCase;
import com.centrumhr.application.employee.data.EmployeeArgument;
import com.centrumhr.data.core.IORMLiteDataBase;
import com.centrumhr.data.importer.EmployeeImporter;
import com.centrumhr.data.mapper.employment.EmployeeMapper;
import com.centrumhr.data.model.employment.*;
import com.centrumhr.domain.common.DomainException;
import com.centrumhr.dto.employment.EmployeeDTO;

import javax.inject.Inject;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class EditEmployeeUseCase extends UseCase<EmployeeArgument,EmployeeDTO> {

    private EmployeeFactory employeeFactory;
    private EmployeeImporter employeeImporter;

    @Inject
    public EditEmployeeUseCase(EmployeeFactory employeeFactory, EmployeeImporter employeeImporter) {
        this.employeeFactory = employeeFactory;
        this.employeeImporter = employeeImporter;
    }

    @Override
    public EmployeeDTO execute(EmployeeArgument argument) throws DomainException {
        EmployeeEntity employeeEntity = employeeFactory.createEmployee(
                argument.personalDataDTO.getFirstName(),
                argument.personalDataDTO.getLastName(),
                argument.personalDataDTO.getCode(),
                argument.workFunction,
                argument.departments,
                argument.employmentDate,
                argument.isJudgment,
                argument.workTime
        );
        employeeEntity.setUniqueId(argument.editingUniqueId);
        employeeImporter.importData(employeeEntity);
        return EmployeeMapper.INSTANCE.backward(employeeEntity);
    }
}
