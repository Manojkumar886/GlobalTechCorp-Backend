package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String remove(int id)
    {
        EmployeeDetails temp=repo.findById(id).orElse(new EmployeeDetails());
        repo.delete(temp);
        return temp.getEmpName()+" has been deleted successfully";
    }

    public Optional<EmployeeDetails> readone(int id)
    {
        return  repo.findById(id);
    }

}
