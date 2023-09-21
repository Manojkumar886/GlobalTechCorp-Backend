package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
