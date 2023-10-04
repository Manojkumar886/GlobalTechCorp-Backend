package EmployeePay.GlobalTechCorp;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeedetailsRepository extends JpaRepository<EmployeeDetails,Integer>
{
    public EmployeeDetails findAllByEmpName(String Name);//no implementation

//    HQL-Hibernate Query Languages
//    select * from employeedetails where Empsalary>=500000.0;
    @Query("from EmployeeDetails where empSalary>=:usercheck")
    public List<EmployeeDetails> findAllByUserGreatestSalary(double usercheck);

    @Transactional
    @Modifying
    @Query("update EmployeeDetails set empSalary=empSalary+(empSalary*15/100) where empName=:employeename")
    public EmployeeDetails findAllByHikeSalary(String employeename);
}
