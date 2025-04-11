package com.eventos.deportivos.backend.models.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* .antMatchers( HttpMethod.GET,
                         "/eventos/", "/eventos/**",
                         "/images/*",
                         "/fases/{id}", "/fases/fechas/{id}","/fases/grupos/{id}",
                         "/fechas/**",
                         "/equipogrupos/**",
                         "/grupos/**",
                         "/equipoEstadisticas/**",
                         "/equipojugador/**",
                         "/equipos/{id}",  "/equipos/uploads/**",
                         "/jugadores/uploads/**",
                         "/equipogrupoduelos/**",
                         "/duelos/**",
                         "/report/**",
                         "/ie/**")
                 .permitAll()
                 .antMatchers(HttpMethod.POST,
                         "/mail/",
                         "/ie/**").permitAll()
                 .antMatchers(HttpMethod.PUT,
                         "/ie/**").permitAll()
                 .antMatchers(HttpMethod.DELETE,
                         "/ie/**").permitAll()*/
                .anyRequest().authenticated()
                .and().cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registration = new
                FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
