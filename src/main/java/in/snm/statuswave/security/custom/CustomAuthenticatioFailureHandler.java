package in.snm.statuswave.security.custom;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import in.snm.statuswave.model.AuthFailureResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticatioFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
                request.getSession().setAttribute("authFailure", getAuthFailureAction(exception));
                response.sendRedirect("/login");
    }

    private AuthFailureResponse getAuthFailureAction(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return AuthFailureResponse.builder()
                    .message("incorrect username or password")
                    .build();
        } else if (exception instanceof DisabledException) {
            return AuthFailureResponse.builder()
                    .message("Verify your account to activate")
                    .action("verify")
                    .build();
        } else if (exception instanceof LockedException) {
            return AuthFailureResponse.builder()
                    .message("Your account is locked")
                    .build();
        } else if (exception instanceof AccountExpiredException) {
            return AuthFailureResponse.builder()
                    .message("Account has expired")
                    .build();
        } else if (exception instanceof CredentialsExpiredException) {
            return AuthFailureResponse.builder()
                    .message("Credentials have expired")
                    .build();
        } else if (exception instanceof SessionAuthenticationException) {
            return AuthFailureResponse.builder()
                    .message("Maximum session limit reached")
                    .build();
        } else if (exception instanceof InsufficientAuthenticationException) {
            return AuthFailureResponse.builder()
                    .message("Insufficient authentication")
                    .build();
        } else {
            return AuthFailureResponse.builder()
                    .message(exception.getMessage())
                    .build();
        }
    }
    
}
