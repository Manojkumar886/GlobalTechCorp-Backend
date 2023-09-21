package EmployeePay.GlobalTechCorp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeedetailsRepository extends JpaRepository<EmployeeDetails,Integer>
{

}
