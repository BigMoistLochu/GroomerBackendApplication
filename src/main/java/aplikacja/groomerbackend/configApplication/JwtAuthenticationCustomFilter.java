package aplikacja.groomerbackend.configApplication;

import aplikacja.groomerbackend.repository.UserRepository;
import aplikacja.groomerbackend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthenticationCustomFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Autoryzacja z poziomu filtra");

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7);



        UserDetails userDetails = userRepository.findByEmail("konradKopka@wp.pl");

        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null
                ,userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);
        filterChain.doFilter(request,response);
    }
}
