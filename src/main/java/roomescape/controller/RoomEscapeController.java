package roomescape.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import roomescape.model.Reservation;

@RestController
public class RoomEscapeController {

    private final List<Reservation> reservations = new ArrayList<>();
    AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping("/")
    public String showPage(){
        return "home";
    }

    @GetMapping("/reservation")
    public String showReservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations")
    public List<Reservation> showAllReservations() {
        return reservations;
    }

    @PostMapping("/reservations")
    public List<Reservation> createReservation(@RequestBody Reservation request) {

        Reservation res = new Reservation(
                idGenerator.getAndIncrement(),
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
