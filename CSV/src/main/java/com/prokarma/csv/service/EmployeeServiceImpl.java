package com.prokarma.csv.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.dao.EmployeeDAO;

@Service
@Transactional
public class EmployeeServiceImpl {

	@Inject
	private EmployeeDAO employeeDAO;

	public List<Employee> getAllEmpoyees() {
		List<Employee> domainObjects = employeeDAO.getAllEmpoyees();
		return domainObjects;
	}

	public int saveCsvEmployeeData(List<Employee> employees) {
		return employeeDAO.saveCsvEmployeeData(employees);
	}

}
