package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoomscapeController {

    private final List<Reservation> reservations = new ArrayList<>();
    int id = 0;

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

    @PostMapping("/reservations")
    @ResponseBody
    public List<Reservation> createReservation(@RequestBody Reservation request) {

        Reservation res = new Reservation(
                ++id,
                request.getName(),
                request.getDate(),
                request.getTime()
        );
        reservations.add(res);
        return reservations;
    }

    @DeleteMapping("/reservations/{id}")
    @ResponseBody
    public void deleteReservation(@PathVariable int id) {
        reservations.removeIf(res -> res.getId() == id);
    }
}
