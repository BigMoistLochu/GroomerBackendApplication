package aplikacja.groomerbackend.configApplication;

import aplikacja.groomerbackend.repository.UserRepository;
import aplikacja.groomerbackend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@AllArgsConstructor
public class JwtAuthenticationCustomFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        if(!jwtService.validateToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }


        String emailFromToken = jwtService.getEmailFromToken(jwt);
        if(emailFromToken==null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userRepository.findByEmail(emailFromToken);

        UsernamePasswordAuthenticationToken userTokenToAuth = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null
                ,userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userTokenToAuth);
        filterChain.doFilter(request,response);
    }
}
