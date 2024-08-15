package aplikacja.groomerbackend.configApplication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationCustomFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Autoryzacja z poziomu filtra");

        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new SimpleGrantedAuthority("ADMIN"));

        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                "Jarek",
                null
                ,list);

        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);
        filterChain.doFilter(request,response);
    }
}
