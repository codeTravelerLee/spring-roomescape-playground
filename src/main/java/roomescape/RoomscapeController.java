package roomescape;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoomscapeController {

    private final List<Reservation> reservations = new ArrayList<>();

    @GetMapping("/")
    public String showPage(){
        return "home";
    }

    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations")
    @ResponseBody
    public List<Reservation> showAllReservations() {
        return reservations;
    }
}
