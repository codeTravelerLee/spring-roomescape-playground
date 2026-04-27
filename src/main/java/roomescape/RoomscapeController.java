package roomescape;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomscapeController {
    @GetMapping("/")
    public String showPage(){
        return "home";
    }
}
