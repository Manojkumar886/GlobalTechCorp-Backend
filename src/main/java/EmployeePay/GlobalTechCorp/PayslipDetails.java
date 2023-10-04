package EmployeePay.GlobalTechCorp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayslipDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payslipId;
    private Date payslipDate;
    private int payslipBasicsalary;
    private int payslipTds;
    private int payslipAllowance;
    private int payslipTakehome;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "empID")
    private EmployeeDetails employeeDetails;

}
