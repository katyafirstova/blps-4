package org.example.bpls.config;


//import jakarta.annotation.PostConstruct;
import org.example.entity.Role;
//import org.example.security.MyBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//        private final AuthenticationEntryPoint authenticationEntryPoint;  // Ваш кастомный EntryPoint
//
//        // Внедрение зависимостей через конструктор
//        public SecurityConfig(AuthFilter authFilter, AuthenticationEntryPoint authenticationEntryPoint) {
//                this.authFilter = authFilter;
//                this.authenticationEntryPoint = authenticationEntryPoint;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//                http
//                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)  // Добавление кастомного фильтра
//                        .csrf().disable()  // Отключение CSRF защиты
//                        .authorizeRequests()  // Настройка авторизации запросов
//                        .antMatchers("/auth/**").permitAll()  // Доступ к /auth/** для всех
//                        .antMatchers("/api/buyer/**").hasAuthority(Role.BUYER.name())  // Доступ только для BUYER
//                        .antMatchers("/api/owner/**").hasAuthority(Role.OWNER.name())  // Доступ только для OWNER
//                        .anyRequest().permitAll()  // Разрешить любые другие запросы
//                        .and()
//                        .httpBasic()  // Использование базовой аутентификации
//                        .authenticationEntryPoint(authenticationEntryPoint);  // Кастомный AuthenticationEntryPoint
//        }


        @Override
        public void configure(WebSecurity web) throws Exception {
                web.httpFirewall(allowDoubleSlash());
        }

        @Bean
        public HttpFirewall allowDoubleSlash() {
                StrictHttpFirewall firewall = new StrictHttpFirewall();
                firewall.setAllowUrlEncodedDoubleSlash(true);
                return firewall;
        }

}

