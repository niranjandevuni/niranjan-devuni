package com.prokarma.csv.dao;

import java.lang.invoke.MethodHandles;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.csv.beans.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmpoyees() {
		log.info("testing ==>");
		return jdbcTemplate.query("select * from employee", new EmpRowMapper());
	}

	@Override
	public int saveCsvEmployeeData(List<Employee> employees) {
		try {
			String sql = "insert into employee (empid,firstName,lastName,middleName,salary,gender,Street,city,active)"
					+ "values(?,?,?,?,?,?,?,?,?)";
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Employee employee = employees.get(i);
					ps.setInt(1, employee.getEmpId());
					ps.setString(2, employee.getFirstName());
					ps.setString(3, employee.getLastName());
					ps.setString(4, employee.getMiddleName());
					ps.setDouble(5, employee.getSalary());
					ps.setString(6, employee.getGender());
					ps.setString(7, employee.getStreet());
					ps.setString(8, employee.getCity());
					ps.setBoolean(9, employee.getActive());
				}

				@Override
				public int getBatchSize() {
					return employees.size();
				}
			});
			return 1;
		} catch (Exception e) {
			log.error("error ==>" + e.getMessage());
		}
		return 0;
	}
}
