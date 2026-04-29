package roomescape.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.model.Reservation;
import roomescape.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class RoomEscapeController {

    private final ReservationService reservationService;

    public RoomEscapeController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation request) {
        Reservation newReservation = reservationService.createReservation(request);
        URI location = URI.create("/reservations/" + newReservation.getId());
        return ResponseEntity.created(location).body(newReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }
}
