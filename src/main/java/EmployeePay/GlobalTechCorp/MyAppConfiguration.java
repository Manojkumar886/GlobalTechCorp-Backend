package EmployeePay.GlobalTechCorp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.stream.Stream;

@Configuration/// collection object define
@EnableWebSecurity //allow to Spring Configuration(Automate)
public class MyAppConfiguration
{
    @Bean
    public PasswordEncoder crpter()
    {
        return new BCryptPasswordEncoder();//4sgasvhbs-2w6y2wkj2-2tsy2usy2
    }
    @Bean//return a object values
    public InMemoryUserDetailsManager userDetailsservice()
    {
//        userdetails is interface class
        UserDetails user1= User.withUsername("Manojkumar").password(crpter().encode("password1")).roles("USER").build();
        UserDetails user2= User.withUsername("Tamil").password(crpter().encode("englis")).roles("CUSTOMER").build();
        Collection<UserDetails> myusers= Stream.of(user1,user2).toList();
        return  new InMemoryUserDetailsManager(myusers);
    }

    @Bean
    @Deprecated
    public SecurityFilterChain pavi420(HttpSecurity hp) throws Exception
    {
        hp.authorizeHttpRequests().
                requestMatchers("/ZealousEmpDetails").
                authenticated();
            hp.csrf().disable();//csrf-cross site request forgery  -a web attack exploits loopholes
            hp.cors();//Cross-Origin Resourse Sharing
            hp.httpBasic();
            hp.formLogin();

            return hp.build();
    }
}
