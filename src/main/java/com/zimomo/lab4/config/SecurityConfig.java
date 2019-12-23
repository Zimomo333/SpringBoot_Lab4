package com.zimomo.lab4.config;

import com.zimomo.lab4.service.security.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginUserDetailsService loginUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/","/information").hasAnyRole("Salesman","SalesManager","Keeper")
                .antMatchers("/salesman/**").hasRole("Salesman")
                .antMatchers("/salesManager/**").hasRole("SalesManager")
                .antMatchers("/salesManager_keeper/**").hasAnyRole("SalesManager","Keeper")
                .antMatchers("/keeper/**").hasRole("Keeper")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(loginUserDetailsService)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return "";
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(charSequence.toString());
                        //charSequence为输入密码，s为数据库密码
                    }
                });
    }
}
