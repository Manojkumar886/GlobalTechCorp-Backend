package EmployeePay.GlobalTechCorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ZealousEmpDetails")//http://localhost:8082/ZealousEmpDetails/
@CrossOrigin(origins = "http://localhost:3000")
public class Controller
{
    @Autowired
    EmployeeDetailsService serv;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    PayslipDetailsService pserv;
//    URL MAPPING -post(create),put(update),get(list,read),delete(delete)
//    http://localhost:8081/create
    @PostMapping("/create")
    public String Makecreate(@RequestBody EmployeeDetails emp)
    {
//        Manojkumar -  hewhd2-jdsjdjd82_2982n#%
        String temp=encoder.encode(emp.getEmpPassword());
        emp.setEmpPassword(temp);
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
    @DeleteMapping("/deleteone/{user}")
    public String remove(@PathVariable("user")String user)
    {
        EmployeeDetails emp=purpose(user);
       return serv.remove(emp.getEmpId())+" ";
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

    @PostMapping("/createpayslip/{user}")
    public PayslipDetails newpayslip(@PathVariable ("user")String user,@RequestBody PayslipDetails payslip)
    {
//        EmployeeDetails temp=serv.gettingexactid(payslip.getEmployeeDetails().getEmpId());
        EmployeeDetails temp=purpose(user);
        double monthlysalary=temp.getEmpSalary()/12;//360000/12=36000;

        double basicsalary=monthlysalary-(monthlysalary*(payslip.getPayslipAllowance()/100));
//                          =36000-(36000*2/100)---->36000+720----->36720;
        payslip.setPayslipBasicsalary((int)basicsalary);

        monthlysalary=basicsalary-(monthlysalary*payslip.getPayslipTds()/100);

//                 =36720-(36720*18/100)------>36720-6609----->30110;
        payslip.setPayslipTakehome((int)monthlysalary);

        temp.getMypayslip().add(payslip);//one payslip get in my payslip
        payslip.setEmployeeDetails(temp);
        pserv.newpayslip(payslip);
//        serv.create(temp);//updation-added one payslip in your empdetails
        return payslip;
    }
    @GetMapping("getallpayslip/{empid}")
    public  List<PayslipDetails> callbyallpayslip(@PathVariable ("empid")int empid)
    {
        EmployeeDetails emp=serv.gettingexactid(empid);
        return  pserv.getbyempdetails(emp);
    }

    @GetMapping("/{user}")//http://localhost:8082/ZealousEmpDetails/ManoHari
    public EmployeeDetails purpose(@PathVariable("user")String user)
    {
        EmployeeDetails emp=(EmployeeDetails) serv.loadUserByUsername(user);
        return emp;
    }
    @GetMapping("/fetch/{user}")
    public List<PayslipDetails> getbyEmployee(@PathVariable("user")String user)
    {
        return  pserv.getbyempdetails(purpose(user));
    }

//    updating Salary
}
