package in.snm.statuswave.land;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LandController {
    @GetMapping("/land")
    public String landPage(HttpServletRequest request, Model model){
        model.addAttribute("pageTitle", "Land");
        return "land";
    }
}
