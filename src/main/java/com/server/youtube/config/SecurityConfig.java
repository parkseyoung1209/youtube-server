package com.server.youtube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired JwtFilter jwtFilter;

    @Autowired
    private Oauth2SuccessHandler handler;
    // 백엔드 서버 : http://localhost:8080
    // 클라이언트 서버 : http://localhost:3000
    /*
     * @Bean
     * @Bean 어노테이션은 Spring에게 해당 메서드가 반환하는 객체를 Spring 컨테이너에 빈(Bean)으로 등록하라고 알려줍니다.
     * 이렇게 등록된 객체는 애플리케이션 전반에서 주입(injection)될 수 있어, 객체를 재사용할 수 있도록 합니다.
     *
     *
     * Bean이란 무엇인가...
     * Spring에서 빈은 Spring IoC 컨테이너가 관리하는 객체를 의미합니다.
     *일반적으로 애플리케이션에서 사용되는 서비스, DAO(Data Access Object), 컨트롤러 등의 객체를 빈으로 정의하여
     Spring 컨테이너가 생성, 초기화, 의존성 주입, 소멸 등의 라이프사이클을 관리하도록 합니다.
     *
     *
     *
     * 
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/private/*")
                        .authenticated()
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2.successHandler(handler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    /*
    * PasswordEncoder 인터페이스
    * PasswordEncoder는 Spring Security에서 비밀번호 암호화와 복호화를 담당하는 인터페이스입니다.
    * 이를 구현하는 여러 클래스 중 하나가 BCryptPasswordEncoder입니다.
    *
    * 원리
    * 비밀번호를 암호화하는 과정에서 단방향 해시 함수를 사용하여 비밀번호를 변환합니다.
    * 이는 해커가 데이터베이스를 탈취하더라도 원래 비밀번호를 유추하기 어렵게 하기 위함입니다.
    * 중요한 점은, 암호화된 비밀번호는 복호화가 불가능하며, 비밀번호를 확인할 때는 사용자가 입력한 비밀번호를 동일한 방식으로 암호화한 후 저장된 값과 비교합니다.
    * */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
