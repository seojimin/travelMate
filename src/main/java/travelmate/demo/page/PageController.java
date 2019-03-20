package travelmate.demo.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    ////signOut용 session clear 도 만들어주기

}
