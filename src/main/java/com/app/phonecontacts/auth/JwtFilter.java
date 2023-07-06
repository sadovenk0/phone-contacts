package com.app.phonecontacts.auth;

import com.app.phonecontacts.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    public final JwtProvider jwtProvider;
    public final UserDetailsServiceImpl userDSI;

    public final String AUTHORIZATION = "Authorization";
    public final String BEARER = "Bearer ";

    @Override
    public void doFilter(
            @NonNull ServletRequest servletRequest,
            @NonNull ServletResponse servletResponse,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if ((token != null) && jwtProvider.validateToken(token)) {
            String login = jwtProvider.getLoginFromToken(token);
            UserDetails userDetails = userDSI.loadUserByUsername(login);
            var auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith(BEARER)) {
            return bearer.substring(BEARER.length());
        }
        return null;
    }
}
