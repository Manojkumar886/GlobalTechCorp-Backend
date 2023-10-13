package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService
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
    public EmployeeDetails readbyname(String name)
    {
        return  repo.findAllByEmpName(name);
    }
    public List<EmployeeDetails> readgreatestsalarypeople(double Salary)
    {
      return  repo.findAllByUserGreatestSalary(Salary);
    }
    public EmployeeDetails incrementbysalary(String username)
    {
        return repo.findAllByHikeSalary(username);
    }
    public EmployeeDetails gettingexactid(int id )
    {
        return repo.findById(id).orElse(new EmployeeDetails());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        EmployeeDetails emp=repo.findByEmpUsername(username);
        if(emp==null)
        {
            throw new UsernameNotFoundException(username);
        }
        return emp;
    }
}
