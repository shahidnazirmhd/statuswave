package in.snm.statuswave.home;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Home");
        model.addAttribute("requestURI", request.getRequestURI());
        return "index"; 
    }

    @GetMapping("/products")
    public String productPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("requestURI", request.getRequestURI());
        return "products"; 
    }

    @GetMapping("/community")
    public String communityPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Community");
        model.addAttribute("requestURI", request.getRequestURI());
        return "community"; 
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("requestURI", request.getRequestURI());
    return "login";
    }

    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Land");
        model.addAttribute("requestURI", request.getRequestURI()); 
    return "land";
    }

    // @GetMapping("/error")
    // public String handleError(HttpServletRequest request, Model model) {
    //     Class<?> exceptionType = (Class<?>) request.getAttribute("javax.servlet.error.exception_type");
    //     Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    //     String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
    //     String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    //     Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

    //     StringWriter stringWriter = new StringWriter();
    //     if (exception != null) {
    //         exception.printStackTrace(new PrintWriter(stringWriter));
    //     }
    //     String stackTrace = stringWriter.toString();

    //     // Add error details to the model
    //     model.addAttribute("pageTitle", "Error");
    //     model.addAttribute("statusCode", statusCode);
    //     model.addAttribute("errorMessage", errorMessage);
    //     model.addAttribute("exceptionType", exceptionType != null ? exceptionType.getName() : "Unknown");
    //     model.addAttribute("exceptionMessage", exception != null ? exception.getMessage() : "N/A");
    //     model.addAttribute("requestUri", requestUri);
    //     model.addAttribute("stackTrace", stackTrace);

    //     return "error/error"; // Thymeleaf template name
    // }

    // @GetMapping("/invalid")
    // public String invalidSession() {
    //     return "error/session/invalid_session";
    // }

    // @GetMapping("/expired")
    // public String sessionExpired() {
    //     return "error/session/session_expired";
    // }
}
