package in.snm.moraceae.home;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @GetMapping("/")
    public String indexPage(){
    return "index";
    }

    @GetMapping("/login")
    public String loginPage(){
    return "login";
    }

    @GetMapping("/land")
    public String landPage(){
    return "land";
    }
}
