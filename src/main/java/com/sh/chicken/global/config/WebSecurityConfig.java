package com.sh.chicken.global.config;

import com.sh.chicken.global.jwt.AuthEntryPointJwt;
import com.sh.chicken.global.jwt.AuthTokenFilter;
import com.sh.chicken.global.jwt.JwtExceptionHandlerFilter;
import com.sh.chicken.global.util.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {  // extends WebSecurityConfigurerAdapte, Spring 2.7.0부터 Deprecated 됨(현재 프로젝트 2.7.6)

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final AuthTokenFilter authTokenFilter;
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/api/users/**").permitAll()
//                .antMatchers("/api/issue").permitAll()
//                .antMatchers("/admin/**").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/api-docs/**").permitAll()
//                .antMatchers("/error/**").permitAll()

                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class); // 여기서 jwt 인증
        http.addFilterBefore(jwtExceptionHandlerFilter, authTokenFilter.getClass()); // jwt 예외처리 필터

        return http.build();

    }


}
