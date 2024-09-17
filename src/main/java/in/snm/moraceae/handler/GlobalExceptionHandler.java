package in.snm.moraceae.handler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoResourceFoundException ex, HttpServletRequest request, Model model) {

        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();

        model.addAttribute("pageTitle", "Page Not Found");
        model.addAttribute("statusCode", HttpStatus.NOT_FOUND.value());
        model.addAttribute("errorMessage", "The page you are looking for does not exist.");
        model.addAttribute("exceptionType", ex.getClass().getName());
        model.addAttribute("exceptionMessage", ex.getMessage());
        model.addAttribute("requestUri", request.getRequestURI());
        model.addAttribute("stackTrace", stackTrace);

        return "error/error"; // Thymeleaf template name
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
    
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();

        model.addAttribute("pageTitle", "Error");
        model.addAttribute("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        model.addAttribute("exceptionType", ex.getClass().getName());
        model.addAttribute("exceptionMessage", ex.getMessage());
        model.addAttribute("requestUri", request.getRequestURI());
        model.addAttribute("stackTrace", stackTrace);

        return "error/error";
    }
}
