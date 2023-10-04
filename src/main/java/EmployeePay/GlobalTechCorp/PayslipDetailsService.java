package EmployeePay.GlobalTechCorp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayslipDetailsService
{
    @Autowired
    PayslipDetailsRepository prepo;

    public PayslipDetails newpayslip(PayslipDetails payslip)
    {
        return prepo.save(payslip);
    }
}
