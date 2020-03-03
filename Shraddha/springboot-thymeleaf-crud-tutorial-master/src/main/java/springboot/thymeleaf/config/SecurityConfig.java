package springboot.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import springboot.thymeleaf.service.impl.CustomAccessDeniedHandler;
import springboot.thymeleaf.service.impl.CustomAuthenticationSuccessHandler;
import springboot.thymeleaf.service.impl.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private CustomAuthenticationSuccessHandler successHandler;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(myUserDetailsService);
	}

    protected void configure(HttpSecurity http) throws Exception {
    	http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/list").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")  // Specifies the login page URL (goes to controller to look for this URL)
		//.loginProcessingUrl("/students/login") // Specifies the URL,which is used in action on the <from> tag
		.usernameParameter("username") // Username parameter, used in <input> tag
		.passwordParameter("password") // Password parameter, used in <input> tag
		.successHandler(successHandler)
		.and()
		//.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
		//.and()
		.logout()
		.logoutUrl("/logout");
	 }

	
	    @Bean
	    public PasswordEncoder getPasswordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
}
