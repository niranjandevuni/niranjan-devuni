package com.prokarma.csv.controller;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.service.EmployeeServiceImpl;

@Component
@Path("/csv")
public class RestControllerCSV implements EmployeeMainInterface {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	private CreateCSVFile createCSVFile;

	@Inject
	private ReadCSV readCSV;

	@Value("${file.path}")
	private String filePath;

	@Inject
	private EmployeeServiceImpl employeeServiceImpl;

	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> employees = employeeServiceImpl.getAllEmpoyees();
			if (employees.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error ==>" + e.getMessage());
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity createCSVFile(@RequestBody List<Employee> employees) {
		createCSVFile.createCSVFile(filePath, employees);
		return new ResponseEntity(HttpStatus.OK);
	}

	// @Scheduled(fixedRate = 10000)
	public ResponseEntity<List<Employee>> readCsvData() {
		List<Employee> employees = null;
		File file = new File(filePath);

		if (file.exists()) {
			employees = readCSV.readingCSVData(filePath);
			if (employees.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			employeeServiceImpl.saveCsvEmployeeData(employees);
			
			if (file.delete()) {
				log.info(file.getName() + " is deleted!");
			} else {
				log.info("Delete operation is failed.");
			}
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
}
