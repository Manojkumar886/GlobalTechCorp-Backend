package EmployeePay.GlobalTechCorp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;

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
//    fetch(Lazy,Eager)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Nullable
    @JoinTable(name = "Allrecords",joinColumns = @JoinColumn(name = "empId")
            ,inverseJoinColumns = @JoinColumn(name = "payslipId"))
    @JsonManagedReference
    private Collection<PayslipDetails> mypayslip=new ArrayList<PayslipDetails>();
//    list<datatype> objectname=new list<datatype>();

}
