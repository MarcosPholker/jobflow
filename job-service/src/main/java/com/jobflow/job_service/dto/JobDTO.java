package com.jobflow.job_service.dto;

public class JobDTO {
	private String title;
	private String description;
	private Double salary;
	private String location;
	private String company;
	private String jobType;

	public JobDTO() {
	}

	public JobDTO(String title, String description, Double salary, String location, String company, String jobType) {
		this.title = title;
		this.description = description;
		this.salary = salary;
		this.location = location;
		this.company = company;
		this.jobType = jobType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}
