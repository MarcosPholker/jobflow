package com.jobflow.job_service.dto;

public class JobDTO {
    private String title;
    private String description;
    private Double Salary;
    private String location;
    private String company;

    public JobDTO(){}

    public JobDTO(String title, String description, Double salary, String location, String company) {
        this.title = title;
        this.description = description;
        Salary = salary;
        this.location = location;
        this.company = company;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Double return the Salary
     */
    public Double getSalary() {
        return Salary;
    }

    /**
     * @param Salary the Salary to set
     */
    public void setSalary(Double Salary) {
        this.Salary = Salary;
    }

    /**
     * @return String return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return String return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

}
