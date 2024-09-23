package in.snm.statuswave.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index"; 
    }

    @GetMapping("/products")
    public String productPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Products");
        return "products"; 
    }

    @GetMapping("/community")
    public String communityPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Community");
        return "community"; 
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        model.addAttribute("pageTitle", "Login");
    return "login";
    }

    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Land");
    return "land";
    }

    @GetMapping("/fragments/auth/login")
    public String loginDiv() {
        return "fragments/login_div :: login";
    }

    @GetMapping("/fragments/auth/register")
    public String registerDiv() {
        return "fragments/register_div :: register";
    }

    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
