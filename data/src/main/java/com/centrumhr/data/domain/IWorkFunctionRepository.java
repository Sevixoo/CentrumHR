package com.centrumhr.data.domain;

import com.centrumhr.data.exception.DatabaseException;
import com.centrumhr.data.model.employment.Employee;
import com.centrumhr.data.model.employment.WorkFunction;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 28.09.2016.
 */
public interface IWorkFunctionRepository {

    void save(WorkFunction item)throws DatabaseException;

    WorkFunction load(UUID uniqueId)throws DatabaseException;

    List<WorkFunction> list()throws DatabaseException;
}
