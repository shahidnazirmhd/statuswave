package in.snm.statuswave.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.snm.statuswave.common.HtmxValidator;
import in.snm.statuswave.user.User;
import in.snm.statuswave.user.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

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

    @PostMapping("/register")
    public String registerUser(HtmxRequest htmxRequest, @ModelAttribute("user") User user, Model model) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        if (userService.isEmailExist(user.getEmail())) {
            System.out.println("ALREADY EXIST");
            model.addAttribute("registerError", "Email already registered - Please register again");
            return "fragments/register_div :: register";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            System.out.println("PASSWORD MISMATCH");
            model.addAttribute("registerError", "Passwords do not match - Please register again");
            return "fragments/register_div :: register";
        }
        User savedUser = userService.saveUser(user);
        if (savedUser != null && savedUser.getId() != null) {
            model.addAttribute("registered", "Account registration successful");
            System.out.println("SUCCESSFUL "+savedUser.getId()+ " " + savedUser.getUsername());
            return "fragments/login_div :: login";
        } else {
            System.out.println("NOT REG");
            model.addAttribute("registerError", "Failed to create user. Please try again.");
            return "fragments/register_div :: register";
        }
    }

    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Land");
    return "land";
    }

    @GetMapping("/fragments/auth/login")
    public String loginDiv(HtmxRequest htmxRequest) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        return "fragments/login_div :: login";
    }

    @GetMapping("/fragments/auth/register")
    public String registerDiv(HtmxRequest htmxRequest, Model model) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        model.addAttribute("user", new User());
        return "fragments/register_div :: register";
    }

    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
