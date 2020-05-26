package com.spring.codeblog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // variável do tipo string estática -> que não irão passar pela autenticação
    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/posts/{id}"
    };

    //método que recebe como parametro o HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll() //passado as URIS que não precisa de autenticação
                .anyRequest().authenticated()      //anyRequest precisa de autenticação ex. newpost
                .and().formLogin().permitAll()    // permite login, para entrar com as credenciais (form login padrão Spring)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // logout com estância de AntPathRequestNMatcher

    }

    // método que recebe como parametro o AuthenticationManagerBuilder define o usuario que irá fazer a autenticação em memória
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("solak").password("{noop}123").roles("ADMIN");
    }

    //método que recebe como parametro o WebSecurity, bootstrap CDN usado no projeto
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**");
    }
}
