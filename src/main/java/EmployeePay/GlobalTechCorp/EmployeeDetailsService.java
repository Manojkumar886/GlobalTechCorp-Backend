package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailsService
{
    @Autowired//object creation
    EmployeedetailsRepository repo;

    public EmployeeDetails create(EmployeeDetails emp)
    {
        return repo.save(emp);
    }

    public List<EmployeeDetails> viewall()
    {
        return (List<EmployeeDetails>) repo.findAll();
    }
}
