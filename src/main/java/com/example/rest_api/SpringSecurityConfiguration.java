package com.example.rest_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .permitAll()
      .and()
      .logout()
      .permitAll();

    http.exceptionHandling()
        .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/api/**"))
        .defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"), AnyRequestMatcher.INSTANCE);

//    CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();
//    cookieCsrfTokenRepository.setCookieHttpOnly(false);
//    http.csrf().csrfTokenRepository(cookieCsrfTokenRepository);
    http.csrf().disable();

//    // RESTful APIを公開する場合、攻撃されやすくなるのでcorsの設定をしておく
//    CorsConfiguration corsConfiguration = new CorsConfiguration();
//    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
//    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//    corsConfiguration.addAllowedOrigin("http://localhost");
//    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
//    corsSource.registerCorsConfiguration("/**", corsConfiguration);
//    http.cors().configurationSource(corsSource);
  }

  // デモ用設定
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
      User.withDefaultPasswordEncoder()
          .username("user")
          .password("password")
          .roles("USER")
          .build();

    return new InMemoryUserDetailsManager(user);
  }
}
