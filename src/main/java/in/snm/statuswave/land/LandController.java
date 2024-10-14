package in.snm.statuswave.land;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("app")
@RequiredArgsConstructor
public class LandController {
    @GetMapping("dashboard")
    public String dashboardView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Dashboard");
        return "land";
    }
    @GetMapping("create")
    public String createView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Create");
        return "land";
    }
    @GetMapping("active")
    public String activeView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Active");
        return "land";
    }
    @GetMapping("history")
    public String historyView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "History");
        return "land";
    }

    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
