package CapstoneProject.CapstoneProject.security;


import CapstoneProject.CapstoneProject.exception.Unauthorized;
import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer"))
            throw new Unauthorized("Token non valido");
        else{
            String token=authorizationHeader.substring(7);
            jwtTools.verificaToken(token);
            String id=jwtTools.getId(token);
            User u=userService.getSingleUser(Integer.parseInt(id));
            Authentication a=new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        boolean isAuthPath = new AntPathMatcher().match("/auth/**", servletPath);
        boolean isAnotherPath = new AntPathMatcher().match("/api/v1/auth/**", servletPath);
        boolean isAnotherPath1 = new AntPathMatcher().match("/v2/api-docs", servletPath);
        boolean isAnotherPath2 = new AntPathMatcher().match("/v3/api-docs", servletPath);
        boolean isAnotherPath3 = new AntPathMatcher().match("/V3/api-docs/**", servletPath);
        boolean isAnotherPath4 = new AntPathMatcher().match("/swagger-resources", servletPath);
        boolean isAnotherPath5 = new AntPathMatcher().match( "/swagger-resources/**", servletPath);
        boolean isAnotherPath6 = new AntPathMatcher().match(  "/configuration/ui", servletPath);
        boolean isAnotherPath7 = new AntPathMatcher().match(  "/configuration/security", servletPath);
        boolean isAnotherPath8 = new AntPathMatcher().match(   "/swagger-ui/**", servletPath);
        boolean isAnotherPath9 = new AntPathMatcher().match(  "/webjars/**", servletPath);
        boolean isAnotherPath10 = new AntPathMatcher().match(  "/swagger-ui.html", servletPath);
        boolean isAnotherPath11 = new AntPathMatcher().match(  "/v3/api-docs/swagger-config", servletPath);

        return isAuthPath || isAnotherPath|| isAnotherPath1|| isAnotherPath2|| isAnotherPath3|| isAnotherPath4|| isAnotherPath5|| isAnotherPath6|| isAnotherPath7|| isAnotherPath8|| isAnotherPath9|| isAnotherPath10||isAnotherPath11;
    }


}
