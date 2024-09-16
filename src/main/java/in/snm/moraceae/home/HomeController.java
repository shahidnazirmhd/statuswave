package in.snm.moraceae.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "index"; 
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
    return "login";
    }

    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("requestURI", request.getRequestURI()); 
    return "land";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Extract error information
        Object status = request.getAttribute("javax.servlet.error.status_code");
        Object message = request.getAttribute("javax.servlet.error.message");
        Object exception = request.getAttribute("javax.servlet.error.exception");

        // Add error details to the model
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        model.addAttribute("exception", exception);
        model.addAttribute("requestURI", request.getRequestURI());

        return "error/error"; // Thymeleaf template name
    }

    @GetMapping("/invalid")
    public String invalidSession() {
        return "error/session/invalid_session";
    }

    @GetMapping("/expired")
    public String sessionExpired() {
        return "error/session/session_expired";
    }
}
