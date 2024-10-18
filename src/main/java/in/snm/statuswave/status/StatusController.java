package in.snm.statuswave.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.snm.statuswave.model.StatusCreationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @PostMapping("/create")
    public String saveStatus(Authentication connectedUser, @Valid @ModelAttribute StatusCreationRequest statusCreationRequest, 
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
    
    Long savedStatusId = statusService.saveStatus(statusCreationRequest, connectedUser);

    redirectAttributes.addFlashAttribute("statusCreated", "Status Created Successfully | STATUS_ID_" + savedStatusId );

    return "redirect:/app/create";
        
    }

}
