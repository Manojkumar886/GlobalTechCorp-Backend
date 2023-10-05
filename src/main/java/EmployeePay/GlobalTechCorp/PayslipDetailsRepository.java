package EmployeePay.GlobalTechCorp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayslipDetailsRepository extends JpaRepository<PayslipDetails,Integer>
{
    public List<PayslipDetails> findAllByEmployeeDetails(EmployeeDetails emp);

}
