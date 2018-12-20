package com.prokarma.csv.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prokarma.csv.beans.Employee;


public interface EmployeeMainInterface {
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public ResponseEntity<List<Employee>> getAllEmployees();

	@POST
	@Path("/create")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseEntity<String> createCSVFile(@RequestBody List<Employee> employees);
	
	
	@GET
	@Path("/readCsv")
	@Produces("application/json")
	public ResponseEntity<List<Employee>> readCsvData();
	
	
}
