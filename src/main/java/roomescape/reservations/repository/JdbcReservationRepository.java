package roomescape.reservations.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import roomescape.reservations.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcReservationRepository extends ReservationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reservation> rowMapper = (rs, rowNum) -> new Reservation(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getObject("date", LocalDate.class),
            rs.getObject("time", LocalTime.class)
    );

    public JdbcReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reservation> getAllReservations() {
        String query = "SELECT id, name, date, time FROM reservation";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        String query = "SELECT id, name, date, time FROM reservation WHERE id=?";
        return jdbcTemplate.query(query, rowMapper, id).stream().findFirst();
    }
}
