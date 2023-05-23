package fr.poei.fines_saveurs_fo.security;


import fr.poei.fines_saveurs_fo.service.CustomerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String[] ENDPOINTS_BlACKLIST = {
            "/customers",
    };
    @Bean
    CustomerDetailsService customUserDetailsService() {
        return new CustomerDetailsService();
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(customUserDetailsService());
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests()
                .requestMatchers(ENDPOINTS_BlACKLIST).authenticated()
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password");
        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");

        return http.build();
    }
}
