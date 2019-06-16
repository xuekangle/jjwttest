package com.example.jjwttest.springsecurity.config;

import com.example.jjwttest.springsecurity.exceptions.MyAuthenticationException;
import com.example.jjwttest.springsecurity.filters.JwtTokenFilter;
import com.example.jjwttest.springsecurity.handlers.MyAccessDeniedHandler;
import com.example.jjwttest.springsecurity.handlers.MyAuthenticationFailHandler;
import com.example.jjwttest.springsecurity.handlers.MyAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

/**
 * springsecurity总配置类，配置密码验证规则，拦截的url，登陆接口地址，登陆成功和失败后的处理，各种异常处理器
 * @author xuekangle
 * @date 2019-05-20 13:12
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
    @Autowired
    private UserDetailsService userDetailsService;

    //登陆成功处理类，如返回自定义jwt
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //登陆失败处理类
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    //token过滤类，解析token
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    //权限不足处理类
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    //其他异常处理类
    @Autowired
    private MyAuthenticationException myAuthenticationException;

    /**
     * 该方法校验用户密码是否正确
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        //校验用户
        authenticationManagerBuilder
                //设置userDetailsService，获取user对象
                .userDetailsService(userDetailsService)
                //自定义密码验证方法
                .passwordEncoder( new PasswordEncoder() {
                    //对密码进行加密 服务启动后，每个用户第一次登陆时，进入该方法。该方法暂时不起作用，获取到的密码不正确。
                    @Override
                    public String encode(CharSequence charSequence) {
                        log.info("对密码进行加密");
                        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                    }
                    //对密码进行判断匹配 自定义密码验证方法，rawPassword：用户输入的密码，encodedPassword：我们查出来的数据库密码
                    @Override
                    public boolean matches(CharSequence charSequence, String rawPassword) {
                        //String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                        String encode = encode(charSequence);
                        boolean res = rawPassword.equals( encode );
                        log.info("密码校验结果：" + res);
                        return res;
                    }
                } );

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //指定登陆的地址，用户登录请求必须是表单格式，application/json方式是接受不到参数的
                //这样写参数可以 http://localhost:8080/user/login?username=admin&password=123456
                .formLogin()
                .loginProcessingUrl("/auth/login")
                //.failureForwardUrl("/test/error") //这个没效果
                //添加登陆成功处理类
                .successHandler(myAuthenticationSuccessHandler)
                //添加登陆失败处理类
                .failureHandler(myAuthenticationFailHandler)
                .and()
                //由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //OPTIONS请求全部放行
                .antMatchers( HttpMethod.OPTIONS, "/**").permitAll()
                //允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                //登录接口放行
                .antMatchers("/auth/**").permitAll()
                //对于获取token的rest api要允许匿名访问
                .antMatchers("/test/**").permitAll()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/tasks/**").authenticated()
                //设定接口用什么方法，什么角色访问
//                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
                //其他接口全部接受验证
                .anyRequest().authenticated();

        //使用自定义的 Token过滤器 验证请求的Token是否合法
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //添加权限不足 filter
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                //其他异常处理类
                .authenticationEntryPoint(myAuthenticationException);

        //禁用缓存
        httpSecurity.headers().cacheControl();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //jwtTokenFilter 将忽略以下路径
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        "/auth"
                )
                //允许静态资源请求
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css"
                )
                //测试环境中，放开druid页面访问.
                .and()
                .ignoring()
                .antMatchers("/druid/*");
    }
}
