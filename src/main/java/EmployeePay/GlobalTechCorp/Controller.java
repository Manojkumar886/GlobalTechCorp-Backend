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

    @Autowired
    PayslipDetailsService pserv;
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

    @GetMapping("/readbyname/{name}")
    public EmployeeDetails readingbyname(@PathVariable("name")String name)
    {
        return serv.readbyname(name);
    }

    @GetMapping("/greatestsalaries/{useramount}")
    public List<EmployeeDetails> gettingtopsalarypeople(@PathVariable("useramount")double useramount)
    {
        return serv.readgreatestsalarypeople(useramount);
    }

    @PutMapping("/updateyoursalary/{empname}")
    public  EmployeeDetails hikesalary(@PathVariable("empname")String empname)
    {
        return  serv.incrementbysalary(empname);
    }


//    payslip performance

    @PostMapping("/createpayslip")
    public PayslipDetails newpayslip(@RequestBody PayslipDetails payslip)
    {
        EmployeeDetails temp=serv.gettingexactid(payslip.getEmployeeDetails().getEmpId());


        double monthlysalary=temp.getEmpSalary()/12;//360000/12=36000;

        double basicsalary=monthlysalary+(monthlysalary*(payslip.getPayslipAllowance()/100));
//                          =36000+(36000*2/100)---->36000+720----->36720;
        payslip.setPayslipBasicsalary((int)basicsalary);

        basicsalary=basicsalary-(basicsalary*payslip.getPayslipTds()/100);

//                 =36720-(36720*18/100)------>36720-6609----->30110;
        payslip.setPayslipTakehome((int)basicsalary);

        temp.getMypayslip().add(payslip);//one payslip get in my payslip

        pserv.newpayslip(payslip);//creating an new payslip in payslip table

        serv.create(temp);//updation-added one payslip in your empdetails

        return payslip;
    }
}
