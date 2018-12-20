package com.prokarma.csv.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prokarma.csv.beans.Employee;



public class EmpRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpId(rs.getInt("EMPID"));
		emp.setFirstName(rs.getString("FIRSTNAME"));
//		emp.setId(rs.getLong("ID"));
//		emp.setName(rs.getString("NAME"));
//		emp.setAge(rs.getShort("AGE"));
//		emp.setSalary(rs.getFloat("SALARY"));
		return emp;
	}
}
