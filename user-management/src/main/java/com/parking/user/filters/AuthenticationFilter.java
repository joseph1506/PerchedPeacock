package com.parking.user.filters;

import com.parking.user.constants.AppConstants;
import com.parking.user.constants.TokenConstants;
import com.parking.user.entity.User;
import com.parking.user.exception.TokenMissingException;
import com.parking.user.service.TokenService;
import com.parking.user.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(AppConstants.AUTHORIZATION.getValue());
        if (header == null || !header.startsWith(AppConstants.BEARER.getValue())) {
            throw new TokenMissingException(TokenConstants.JWT_TOKEN_MISSING.getValue());
        }
        String token = header.substring(7);
        tokenService.validateToken(token);
        User user = tokenService.getUserDetailsFromToken(token);
        UserDetails userDetails = userAuthService.loadUserByUsername(user.getUserId());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }
}
