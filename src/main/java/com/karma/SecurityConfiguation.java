package com.karma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguation extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    //lay bean tu userServiceImpl
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s = encoder.encode("2901");
        System.out.println(s);
    }

    // xac thuc tai khoan
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //phan quyen
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/*").hasAnyRole("Admin")
                .antMatchers("/member/*").hasAnyRole("Member", "Admin").anyRequest().permitAll();

        //login method
//        http.httpBasic();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/admin/user/list").and()
        .logout().logoutSuccessUrl("/login").logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
