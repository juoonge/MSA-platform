package com.sparta.auth.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.auth.application.dto.LoginRequestDto;
import com.sparta.auth.domain.model.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        // UserDetailsImpl에서 사용자 정보를 가져옵니다.
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        // UserDetailsImpl에서 권한을 가져오는 방법을 사용합니다.
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

//        // 인증 성공 시, Redis 저장을 위한 처리를 위해 User를 가져옵니다.
//        User user = (User) authResult.getPrincipal();
//        // userDto는 redis에 저장하기 위한 중간 dto 입니다.
//        var userDto = UserDto.fromUser(user);
//
//        // redis에 저장될 때 user: 라는 prefix와 username을 합쳐 키를 만들고 userDto를 직렬화하여 저장합니다.
//        redisService.setValue("user:" + username, userDto);

        String token = jwtUtil.createToken(username, roles);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }

//    private record UserDto(String userName, Collection<String> roles) {
//        public static UserDto fromUser(User user) {
//            return new UserDto(
//                    user.getUsername(),
//                    user.getAuthorities().stream()
//                            .map(GrantedAuthority::getAuthority)
//                            .collect(Collectors.toList())
//            );
//        }
//    }
}