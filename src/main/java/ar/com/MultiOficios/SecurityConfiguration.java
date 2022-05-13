package ar.com.MultiOficios;


import ar.com.MultiOficios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
	public UsuarioServicio usuarioServicio;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioServicio).
		passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/*", "/js/*", "/img/*", "/**","/main/**","/cliente/**","/actividad/**","/login/**", "/glosario/**").permitAll()
				.and().formLogin()
					.loginPage("/login") //url donde se va a mostrar el login, tiene que matchear con el controlador
						.loginProcessingUrl("/logincheck") //url de un Post de Spring, lo tenemos que poner en el form del login
						.usernameParameter("email") //name del input donde ingresamos el email
						.passwordParameter("password")//name del input donde ingresamos el password
						.defaultSuccessUrl("/") //si todo sale bien vamos al index
						.failureUrl("/logout2") //si todo sale mal volvemos al login
						.permitAll()
				.and().logout()
					.logoutUrl("/logout") //para deslogearse
					.logoutSuccessUrl("/")
					.permitAll()
				.and().csrf()
					.disable();
	}
}

