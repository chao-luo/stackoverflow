package com.questions.stackoverflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@SpringBootApplication
@Slf4j
@EnableOAuth2Sso
public class StackOverflowApplication extends WebSecurityConfigurerAdapter {



    private AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/after");
    }


    private OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter() {
        OAuth2SsoProperties sso = (OAuth2SsoProperties)this.getApplicationContext().getBean(OAuth2SsoProperties.class);
        OAuth2RestOperations restTemplate = ((UserInfoRestTemplateFactory)this.getApplicationContext().getBean(UserInfoRestTemplateFactory.class)).getUserInfoRestTemplate();
        ResourceServerTokenServices tokenServices = (ResourceServerTokenServices)this.getApplicationContext().getBean(ResourceServerTokenServices.class);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(sso.getLoginPath());
        filter.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        filter.setApplicationEventPublisher(this.getApplicationContext());
        filter.setAuthenticationSuccessHandler(successHandler());
        return filter;
    }

    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/login**", "/webjars/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()
                ;
        http.addFilterAfter(oAuth2ClientAuthenticationProcessingFilter(), LogoutFilter.class);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }

}
