package com.springboot.blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {

        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    //get jwt token from http request
        String token = getTokenFromRequest(request);
        //validate token
        //jwttokenprovider class 里面有get name from token
        if(StringUtils.hasText(token)&&jwtTokenProvider.validateToken(token)){

            //get username from token
            String username = jwtTokenProvider.getUsername(token);
            //load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //create authentication object
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,//userdetail already contains credentials like username
                    userDetails.getAuthorities()//getAuthorities return a list of authorities
            );
            //pass request to authentication object
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);//doFilterInternal is a method from OncePerRequestFilter
    }
    private String getTokenFromRequest(HttpServletRequest request){//from header
       String bearerToken = request.getHeader("Authorization");//Authorization is the key
        //extract the value and subtract the bearer with a space
       if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
           return bearerToken.substring(7,bearerToken.length());//因为bearer后面有一个空格所以从7开始

       }
       return null;
    }
}
