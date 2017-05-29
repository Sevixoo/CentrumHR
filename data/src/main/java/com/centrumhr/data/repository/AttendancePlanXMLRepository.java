package com.centrumhr.data.repository;

import com.centrumhr.data.core.DatabaseException;
import com.centrumhr.data.core.xml.IXMLDataBase;
import com.centrumhr.data.mapper.attendance.AttendancePlanMapper;
import com.centrumhr.data.model.attendance.AttendancePlanEntity;
import com.centrumhr.domain.attendance.AttendancePlan;
import com.centrumhr.domain.attendance.AttendancePlanFactory;
import com.centrumhr.domain.attendance.IAttendancePlanRepository;
import com.centrumhr.dto.attendance.*;
import com.centrumhr.dto.common.Hour;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Seweryn on 24.05.2017.
 */
//com.centrumhr.data.repository;
public class AttendancePlanXMLRepository implements IAttendancePlanRepository {

    private IXMLDataBase xmlDataBase;
    private AttendancePlanFactory attendancePlanFactory;

    public AttendancePlanXMLRepository(IXMLDataBase xmlDataBase,AttendancePlanFactory attendancePlanFactory) {
        this.xmlDataBase = xmlDataBase;
        this.attendancePlanFactory = attendancePlanFactory;
    }

    @Override
    public AttendancePlan find(UUID uniqueId) {
        xmlDataBase.beginTransaction();
        Node xmlData = xmlDataBase.find("attendancePlan",uniqueId);
        AttendancePlanDTO DTO = decodeXmlAttendancePlanData(xmlData);
        xmlDataBase.endTransaction();
        if(DTO==null){
            return null;
        }else{
            return attendancePlanFactory.create(DTO);
        }
    }

    private AttendancePlanDTO decodeXmlAttendancePlanData(Node xmlData) {
        if(xmlData == null)return null;

        Element employeesNode = (Element)((Element)xmlData).getElementsByTagName("employees").item(0);
        AttendanceEmployeeDTO[] attendanceEmployeeDTOs = new AttendanceEmployeeDTO[employeesNode.getElementsByTagName("employee").getLength()];
        for(int i = 0 ; i < employeesNode.getElementsByTagName("employee").getLength() ; i++){
            Element employeeNode = (Element)employeesNode.getElementsByTagName("employee").item(i);
            System.err.print("employeeNode.getAttributes()" + employeeNode.getTagName());
            attendanceEmployeeDTOs[i] = new AttendanceEmployeeDTO(
                    UUID.fromString(employeeNode.getAttributes().getNamedItem("uniqueId").getNodeValue()),
                    employeeNode.getAttributes().getNamedItem("name").getNodeValue(),
                    employeeNode.getAttributes().getNamedItem("code").getNodeValue(),
                    UUID.fromString(employeeNode.getAttributes().getNamedItem("employeeUniqueId").getNodeValue())
            );
        }

        Element attendanceDaysNode =  (Element)((Element)xmlData).getElementsByTagName("attendanceDays").item(0);
        AttendanceDayDTO[][] attendanceDayDTOs = new AttendanceDayDTO[attendanceDaysNode.getElementsByTagName("employeeDays").getLength()][];
        for(int i = 0 ; i < attendanceDaysNode.getElementsByTagName("employeeDays").getLength() ; i++){
            Element employeeDays = (Element)attendanceDaysNode.getElementsByTagName("employeeDays").item(i);
            attendanceDayDTOs[i] = new AttendanceDayDTO[employeeDays.getElementsByTagName("day").getLength()];
            for(int j = 0 ; j < employeeDays.getElementsByTagName("day").getLength() ; j++){
                Element dayNode = (Element)employeeDays.getElementsByTagName("day").item(j);
                attendanceDayDTOs[i][j] = new AttendanceDayDTO(
                        UUID.fromString(dayNode.getAttributes().getNamedItem("uniqueId").getNodeValue()),
                        UUID.fromString(dayNode.getAttributes().getNamedItem("employeeUniqueId").getNodeValue()),
                        readDate(dayNode,"date"),
                        readAttendanceType(dayNode,"type"),
                        Hour.create(readDate(dayNode,"planStartHour")),
                        Hour.create(readDate(dayNode,"planEndHour")),
                        Hour.create(readDate(dayNode,"attendStartHour")),
                        Hour.create(readDate(dayNode,"attendEndHour")),
                        dayNode.getAttributes().getNamedItem("uniqueId").getNodeValue()
                );
            }
        }
        return new AttendancePlanDTO(
                UUID.fromString(xmlData.getAttributes().getNamedItem("uniqueId").getNodeValue()),
                xmlData.getAttributes().getNamedItem("name").getNodeValue(),
                readDate(xmlData,"startDate"),
                readDate(xmlData,"createDate"),
                PlanState.OPEN,
                attendanceEmployeeDTOs,
                attendanceDayDTOs
        );
    }

    @Override
    public void save(AttendancePlan attendancePlan) {
        AttendancePlanDTO DTO = attendancePlan.convert();
        xmlDataBase.beginTransaction();
        Element planNode = xmlDataBase.create("attendancePlan");
        planNode.setAttribute("uniqueId",DTO.getUniqueId().toString());
        planNode.setAttribute("name",DTO.getName());
        planNode.setAttribute("createDate",encode(DTO.getCreateDate()));
        planNode.setAttribute("startDate", encode(DTO.getStartDate()));

        Element employeesNode = xmlDataBase.create("employees");
        for (AttendanceEmployeeDTO employeeDTO : DTO.getEmployees()) {
            Element employeeNode = xmlDataBase.create("employee");
            employeeNode.setAttribute("uniqueId",employeeDTO.getUniqueId().toString());
            employeeNode.setAttribute("name",employeeDTO.getName());
            employeeNode.setAttribute("code",employeeDTO.getCode());
            employeeNode.setAttribute("employeeUniqueId",employeeDTO.getEmployeeUniqueId().toString());
            employeesNode.appendChild(employeeNode);
        }
        planNode.appendChild(employeesNode);

        Element attendanceDaysNode = xmlDataBase.create("attendanceDays");
        for (AttendanceDayDTO[] dayDTOs : DTO.getDays()) {
            Element employeeDaysNode = xmlDataBase.create("employeeDays");
            for (AttendanceDayDTO dayDTO : dayDTOs) {
                Element dayNode = xmlDataBase.create("day");
                dayNode.setAttribute("uniqueId",dayDTO.getUniqueId().toString());
                dayNode.setAttribute("description",dayDTO.getDescription());
                dayNode.setAttribute("employeeUniqueId",dayDTO.getEmployeeId().toString());
                dayNode.setAttribute("planStartHour",encode(dayDTO.getPlanStartHour()));
                dayNode.setAttribute("planEndHour",encode(dayDTO.getPlanEndHour()));
                dayNode.setAttribute("attendStartHour",encode(dayDTO.getAttendStartHour()));
                dayNode.setAttribute("attendEndHour",encode(dayDTO.getAttendEndHour()));
                dayNode.setAttribute("date",String.valueOf(dayDTO.getDate().getTime()));
                dayNode.setAttribute("type",encode(dayDTO.getType()));
                employeeDaysNode.appendChild(dayNode);
            }
            attendanceDaysNode.appendChild(employeeDaysNode);
        }
        planNode.appendChild(attendanceDaysNode);

        xmlDataBase.delete(DTO.getUniqueId());
        xmlDataBase.insert(planNode);
        xmlDataBase.endTransaction();
    }

    public Date readDate( Node dayNode , String name ){
        if(dayNode.getAttributes().getNamedItem(name)==null){
            return null;
        }
        String value = dayNode.getAttributes().getNamedItem(name).getNodeValue();
        if(value == null || value.equals("")){
            return null;
        }
        try {
            long time = Long.parseLong(value);
            return new Date(time);
        }catch (NumberFormatException e){
            throw new DatabaseException(e);
        }
    }

    public AttendanceType readAttendanceType( Node dayNode , String name ){
        if (name == null || name.equals("")){
            return null;
        }else{
            return AttendanceType.valueOf(dayNode.getAttributes().getNamedItem(name).getNodeValue());
        }
    }

    public String encode(AttendanceType attendanceType){
        if(attendanceType == null){
            return "";
        }else{
            return attendanceType.name();
        }
    }

    public String encode(Date date){
        if(date == null){
            return "";
        }else{
            return String.valueOf(date.getTime());
        }
    }

    public String encode( Hour hour ){
        if(hour == null){
            return "";
        }else{
            return String.valueOf(hour.getTime());
        }
    }

    @Override
    public List<AttendancePlan> list() {
        List<AttendancePlan> list = new ArrayList<>();
        xmlDataBase.beginTransaction();
        List<Element> xmlData = xmlDataBase.list();
        for (Element node : xmlData) {
            AttendancePlanDTO DTO = decodeXmlAttendancePlanData(node);
            list.add(attendancePlanFactory.create(DTO));
        }
        xmlDataBase.endTransaction();
        return list;
    }
}
