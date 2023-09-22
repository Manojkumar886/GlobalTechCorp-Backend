package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller
{
    @Autowired
    EmployeeDetailsService serv;
//    URL MAPPING -post(create),put(update),get(list,read),delete(delete)
//    http://localhost:8081/create
    @PostMapping("/create")
    public String Makecreate(@RequestBody EmployeeDetails emp)
    {
        return  serv.create(emp).getEmpName()+"has been added in your database";
    }
    @GetMapping("/")
    public List<EmployeeDetails> listall()
    {
        return  serv.viewall();
    }
//    create and list is over

    @PutMapping("/update")
    public String updating(@RequestBody EmployeeDetails emp)
    {
        EmployeeDetails temp=serv.create(emp);
        return temp.getEmpId()+" has been updated in your values";
    }
    @DeleteMapping("/deleteone/{id}")
    public String remove(@PathVariable("id")int id)
    {
       return serv.remove(id)+" ";
    }

    @GetMapping("/readone/{empid}")
    public Optional<EmployeeDetails> readingone(@PathVariable("empid")int empid)
    {
        return serv.readone(empid);
    }
}
