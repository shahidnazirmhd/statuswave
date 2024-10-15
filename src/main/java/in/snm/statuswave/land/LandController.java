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
        model.addAttribute("fragmentName", "temp");
        return "land";
    }
    @GetMapping("create")
    public String createView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Create");
        model.addAttribute("fragmentName", "create_div");
        return "land";
    }
    @GetMapping("active")
    public String activeView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Active");
        model.addAttribute("fragmentName", "temp");
        return "land";
    }
    @GetMapping("history")
    public String historyView(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "History");
        model.addAttribute("fragmentName", "temp");
        return "land";
    }

    
    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
