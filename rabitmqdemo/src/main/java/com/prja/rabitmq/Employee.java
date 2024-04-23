package com.prja.rabitmq;

import java.io.Serializable;

public class Employee implements Serializable {
	 String empName;
	 String empEmail;
	public Employee(String empName, String empEmail) {
		this.empName = "";
		this.empEmail = "";
	}
	public String getEmpName() {
		return empName;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empEmail=" + empEmail + "]";
	}

	
}
