package in.snm.statuswave.land;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.snm.statuswave.common.HtmxValidator;
import in.snm.statuswave.model.StatusCreationRequest;
import in.snm.statuswave.status.Status;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
        model.addAttribute("status", new Status());
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


    @PostMapping("/status")
    public String saveStatus(@Valid @ModelAttribute StatusCreationRequest statusCreationRequest, 
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
        redirectAttributes.addFlashAttribute("errorStatusCreationRequest", statusCreationRequest);
        return "redirect:/app/create";
    }
    
    System.out.println(statusCreationRequest);

    return "redirect:/app/create";
        
    }


    @GetMapping("/fragments/component/progress-row")
    public String loginDiv(HtmxRequest htmxRequest) {
        HtmxValidator.validateHtmxRequest(htmxRequest, "/app/create");
        return "fragments/progress_row :: row";
    }

    
    @ModelAttribute("requestURI")
    public String requestURI(HttpServletRequest request) {
        return request.getRequestURI(); 
    }
}
