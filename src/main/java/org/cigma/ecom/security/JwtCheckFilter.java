package org.cigma.ecom.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.cigma.ecom.service.JwtUserDetailsService;
import org.cigma.ecom.util.JwtTokenUtil;
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
public class JwtCheckFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String headerAuthorization = httpServletRequest.getHeader("Authorization");
        String username = null;
        String token = null;
        if (headerAuthorization != null && headerAuthorization.startsWith("Bearer ")){
            token = headerAuthorization.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);

            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT");
            } catch (ExpiredJwtException e){
                System.out.println("JWT has expired");
            }
        }else {
            logger.warn("JWT does not begin with Bearer String");
        }
        System.out.println(" = ");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        System.out.println(" = ");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
