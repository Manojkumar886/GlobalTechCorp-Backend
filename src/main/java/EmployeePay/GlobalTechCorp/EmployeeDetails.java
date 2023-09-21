package EmployeePay.GlobalTechCorp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails
{
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.AUTO)//auto increment
    private int empId;
    private String empName;
    private String empUsername;
    private String empPassword;
    private String empDesignation;
    private String empExperience;
    @Column(name = "perAnnumSalary")
    private double empSalary;

}
