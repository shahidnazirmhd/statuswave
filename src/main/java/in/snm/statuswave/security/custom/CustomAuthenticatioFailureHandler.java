package in.snm.statuswave.security.custom;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticatioFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                System.out.println("Authentication failed: " + exception.getMessage());

                if (exception instanceof DisabledException) {
                    System.out.println("USER DISABLED EXCEPTION");
                }
    
                request.getSession().setAttribute("errorMessage", exception.getMessage());
                response.sendRedirect("/login");
    }
    
}
