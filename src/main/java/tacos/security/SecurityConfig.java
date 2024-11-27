package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{ //http 보안
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/**").access("permitAll");
//                .and()
//                    .formLogin().loginPage("/login").defaultSuccessUrl("/design", true)
//                .and()
//                    .logout().logoutSuccessUrl("/")
//                .and()
//                    .csrf();




        httpSecurity
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // H2 Console 접근 허용
                .antMatchers("/", "/**").permitAll()      // 나머지 경로도 모두 허용
                .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")   // H2 Console 경로에서 CSRF 비활성화
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }


    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
//    @Autowired
//    DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { //사용자 인증 정보 구성

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        /*
        [인메모리 사용자 스토어]
        auth
                .inMemoryAuthentication()
                    .withUser("user1").password("{noop}password1").authorities("ROLE_USER")
                .and()
                    .withUser("user2").password("{noop}password2").authorities("ROLE_USER");

         */

        /*
        [스프링 시큐리티 기본 jdbc 쿼리]
        auth
                .jdbcAuthentication()
                .dataSource(dataSource);
         */

        /*
        [스크링 시큐리티 대체 쿼리]
        테이블의 이름은 시큐리티의 기본 데이터베이스 테이블과 달라도 되지만 테이블이 갖는 열의 데이터 타입과 길이는 일치해야 한다
        groupAuthoritiesByUsername()을 호출하여 그룹권한 쿼리도 대체할 수 있음
        시큐리티 기본 sql 쿼리 대체할 때
          1. 매개변수는 1개이며 username이어야 한다
          2. 사용자 정보 인증 쿼리에서 username, password, enabled 열의 값을 반환해야 한다
          3. 사용자 권한 쿼리에서는 해당 사용자 이름과 부여된 권한을 포함하는 다수의 행을 반환할 수 있다
          4. 그룹 권한 쿼리에서는 각각 그룹 id, group_name, authority 열을 갖는 다수의 행을 반환할 수 있다

        auth
                .jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPwd) {
                        return rawPwd.toString();
                    }

                    @Override
                    public boolean matches(CharSequence rawPwd, String encodedPwd) {
                        return rawPwd.toString().equals(encodedPwd);
                    }
                });

        BcryptPasswordEncoder, NoOpPasswordEncoder(암호화x), Pbkdf2PAsswordEncoder, ScryptPasswordEncoder, StandartPasswordEncoder

        */
    }
}
