package EmployeePay.GlobalTechCorp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails implements UserDetails
{
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.AUTO)//auto increment
    private int empId;
    private String empName;
    private String empUsername;
    private String empPassword;
    private String empDesignation;
    private int empExperience;
    @Column(name = "perAnnumSalary")
    private double empSalary;
//    fetch(Lazy,Eager)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Nullable
    @JoinTable(name = "Allrecords",joinColumns = @JoinColumn(name = "empId")
            ,inverseJoinColumns = @JoinColumn(name = "payslipId"))
    @JsonManagedReference
    private Collection<PayslipDetails> mypayslip=new ArrayList<PayslipDetails>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return empPassword;
    }

    @Override
    public String getUsername() {
        return empUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    list<datatype> objectname=new list<datatype>();

//    ArrayList<Integer> object=new Arraylist<Integer>

}
