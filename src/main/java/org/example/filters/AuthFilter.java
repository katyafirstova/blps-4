//package org.example.filters;
//
//import org.example.entity.User;
//import org.example.services.AuthService;
//import org.example.services.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Component
//public class AuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private AuthService authService;
//
//    @Autowired
//    MyUserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//
//        //authService.registerUser(user, Role.OWNER);
//        UserDetails userDetails;
//
//        try {
//            userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//        }
//        catch (UsernameNotFoundException e){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        if (!authService.authenticateUser(user)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
//                (userDetails, null, userDetails.getAuthorities());
//
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(auth);
//        SecurityContextHolder.setContext(context);
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isHeaderVaild(String header) {
//        String[] splittedHeader = header.split(":");
//
//        return splittedHeader.length == 2;
//    }
//
//}
//
//
//
