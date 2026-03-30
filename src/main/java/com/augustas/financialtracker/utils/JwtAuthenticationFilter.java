package com.augustas.financialtracker.utils;

import com.augustas.financialtracker.services.CustomUserDetailsService;
import com.augustas.financialtracker.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {




        var authToken = request.getHeader("Authorization");
        if(authToken != null && authToken.startsWith("Bearer ")){
            authToken = authToken.substring(7);

            var email = jwtService.extractUsername(authToken);
            var user = userDetails.loadUserByUsername(email);

            if(jwtService.isTokenValid(authToken, user)){
                var token = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
