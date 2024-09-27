package in.snm.statuswave.home;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import in.snm.statuswave.common.CaptchaValidator;
import in.snm.statuswave.common.HtmxValidator;
import in.snm.statuswave.model.AuthFailureResponse;
import in.snm.statuswave.model.UserVerifyRequest;
import in.snm.statuswave.user.User;
import in.snm.statuswave.user.UserService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final CaptchaValidator captchaValidator;

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
        AuthFailureResponse authFailure = (AuthFailureResponse) request.getSession().getAttribute("authFailure");
        model.addAttribute("pageTitle", "Login");
    if (authFailure != null) {
        model.addAttribute("errorMessage", authFailure.message());
        if(authFailure.action() != null && authFailure.action().equals("verify")) {
            model.addAttribute("needToverify", true);
        }
        request.getSession().removeAttribute("authFailure");
    }
    return "login";
    }

    @PostMapping("/register")
    public String registerUser(HtmxRequest htmxRequest, @ModelAttribute("user") User user, Model model, @RequestParam("h-captcha-response") String captchaResponse) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");

        if (!StringUtils.hasText(captchaResponse)) {
            model.addAttribute("registerError", "Please complete the captcha to proceed");
            return "fragments/register_div :: register";
        }

        if (!captchaValidator.validateCaptcha(captchaResponse)) {
            model.addAttribute("registerError", "Captcha verification failed. Please try again");
            return "fragments/register_div :: register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("registerError", "Passwords do not match - Please register again");
            return "fragments/register_div :: register";
        }
        if (userService.isEmailExist(user.getEmail())) {
            model.addAttribute("registerError", "Email already registered - Please register again");
            return "fragments/register_div :: register";
        }
        if (userService.isProfileNameExist(user.getProfileName())) {
            model.addAttribute("registerError", "Profile name not available - Try different");
            return "fragments/register_div :: register";
        }
        User savedUser = userService.registerUser(user);
        if (savedUser != null && savedUser.getId() != null) {
            // model.addAttribute("registered", "Account registration successful");
            UserVerifyRequest userVerifyRequest = userService.sendValidationEmail(savedUser);
            model.addAttribute("userVerifyRequest", userVerifyRequest);
            return "fragments/register_otp_div :: register_otp";
        } else {
            model.addAttribute("registerError", "Registration failed. Please try again.");
            return "fragments/register_div :: register";
        }
    }

    @PostMapping("/verify/otp")
    public String verifyAccount(HtmxRequest htmxRequest, @ModelAttribute("userVerifyRequest") UserVerifyRequest userVerifyRequest, Model model) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        if(userVerifyRequest.otp().equals("1234")) {
            User user = userService.findByEmail(userVerifyRequest.username()).orElseThrow();
            user.setEnabled(true);
            userService.save(user);
            model.addAttribute("registered", "You are verified. Please login");
            return "fragments/login_div :: login";
        } else {
            model.addAttribute("errorMessage", "Incorrect OTP");
            model.addAttribute("userVerifyRequest", UserVerifyRequest.builder().username(userVerifyRequest.username()).build());
            return "fragments/register_otp_div :: register_otp";
        }
    }

    @PostMapping("/verify/subject")
    public String verifySubject(HtmxRequest htmxRequest, @RequestParam("email") String email, Model model) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        User verificationSubject = userService.findByEmail(email).orElse(null);
        if (verificationSubject == null) {
            model.addAttribute("errorMessage", "Please enter correct registered email");
            return "fragments/verification_subject_div :: verification_subject";
        } else if(verificationSubject.isEnabled()) {
            model.addAttribute("registered", "You are verified. Please login");
            return "fragments/login_div :: login";
        } else {
            UserVerifyRequest userVerifyRequest = userService.sendValidationEmail(verificationSubject);
            model.addAttribute("userVerifyRequest", userVerifyRequest);
            return "fragments/register_otp_div :: register_otp";
        }
    }

    @GetMapping("/fragments/auth/verification-subject")
    public String verificationSubjectDiv(HtmxRequest htmxRequest) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/login");
        return "fragments/verification_subject_div :: verification_subject";
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

    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Land");
        return "land";
    }

    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
