package com.centrumhr.data.repository;

import com.centrumhr.data.core.ormlite.DAO;
import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.core.ormlite.IORMLiteDataBase;
import com.centrumhr.data.importer.AttendancePlanImporter;
import com.centrumhr.data.mapper.attendance.AttendancePlanMapper;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.dto.attendance.AttendancePlanDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Seweryn on 11.02.2017.
 */
public class AttendancePlanRepository implements IAttendancePlanRepository {

    private AttendancePlanFactory attendancePlanFactory;
    private DAO<AttendancePlanEntity> attendancePlanEntityDAO;
    private AttendancePlanMapper attendancePlanMapper;
    private AttendancePlanImporter attendancePlanImporter;

    public AttendancePlanRepository(IORMLiteDataBase dataBase, AttendancePlanFactory attendancePlanFactory) {
        this.attendancePlanEntityDAO = dataBase.provideDAO(AttendancePlanEntity.class);
        this.attendancePlanImporter = new AttendancePlanImporter( dataBase );
        this.attendancePlanFactory = attendancePlanFactory;
        this.attendancePlanMapper = new AttendancePlanMapper();
    }

    @Override
    public AttendancePlan find(UUID uniqueId) {
        AttendancePlanEntity data = attendancePlanEntityDAO.load(uniqueId);
        AttendancePlanDTO dto = attendancePlanMapper.backward(data);
        return attendancePlanFactory.create( dto );
    }

    @Override
    public void save(AttendancePlan attendancePlan) {
        AttendancePlanDTO dto = attendancePlan.convert();
        AttendancePlanEntity data = attendancePlanMapper.forward(dto);
        attendancePlanImporter.importData(data);
    }

    @Override
    public List<AttendancePlan> list() {
        try {
            List<AttendancePlanEntity> data = attendancePlanEntityDAO.queryBuilder()
                    .orderBy("createDate",false)
                    .query();

            return attendancePlanMapper.backward(data).stream()
                    .map(attendancePlanFactory::create)
                    .collect(Collectors.toList());

        }catch (SQLException e){
            throw new DatabaseException(e);
        }
    }
}
