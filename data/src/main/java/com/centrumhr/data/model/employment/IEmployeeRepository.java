package com.centrumhr.data.model.employment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 15.02.2017.
 */
public interface IEmployeeRepository {

    List<EmployeeEntity> list(List<UUID> ids);

}
