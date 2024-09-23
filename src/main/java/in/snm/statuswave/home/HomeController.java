package in.snm.statuswave.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.snm.statuswave.user.User;
import in.snm.statuswave.user.UserService;
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
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("pageTitle", "Login");
        if (userService.isEmailExist(user.getEmail())) {
            model.addAttribute("error", "Email already registered - Please register again");
            return "redirect:/login";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match - Please register again");
            return "redirect:/login";
        }

        User savedUser = userService.saveUser(user);
        System.out.println("REGISTER TRIGGERED");
        if (savedUser != null && savedUser.getId() != null) {
            model.addAttribute("registered", "Account registration successful");
            System.out.println("SUCCESSFUL");
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Failed to create user. Please try again.");
            return "redirect:/login";
        }
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
    public String registerDiv(Model model) {
        model.addAttribute("user", new User());
        return "fragments/register_div :: register";
    }

    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
