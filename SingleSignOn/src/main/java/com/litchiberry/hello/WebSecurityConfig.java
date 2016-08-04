/**
 *
 */
package com.litchiberry.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author lijo - security configuration that ensures that only authenticated users can see the
 *         secret message. The WebSecurityConfig class is annotated with @EnableWebSecurity to
 *         enable Spring Security’s web security support and provide the Spring MVC integration. It
 *         also extends WebSecurityConfigurerAdapter and overrides a couple of its methods to set
 *         some specifics of the web security configuration. The configure(HttpSecurity) method
 *         defines which URL paths should be secured and which should not. Specifically, the "/" and
 *         "/home" paths are configured to not require any authentication. All other paths must be
 *         authenticated. When a user successfully logs in, they will be redirected to the
 *         previously requested page that requires authentication. There is a custom "/login" page
 *         specified by loginPage(), and everyone is allowed to view it. As for the
 *         configureGlobal(AuthenticationManagerBuilder) method, it sets up an in-memory user store
 *         with a single user. That user is given a username of "user", a password of "password",
 *         and a role of "USER".
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http
            .authorizeRequests()
            .antMatchers( "/", "/home" ).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage( "/login" )
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth
            .inMemoryAuthentication()
            .withUser( "user" ).password( "password" ).roles( "USER" );
    }

}
