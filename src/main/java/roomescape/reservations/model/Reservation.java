package roomescape.reservations.model;

import roomescape.global.error.InvalidReservationTimeException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Reservation() {
    }

    public Reservation(Long id, String name, LocalDate date, LocalTime time) {
        validateNameNotEmpty(name);
        validateReservationInBusinessHour(time);
        validateReservationTimeIsNotPast(date, time);

        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    private void validateNameNotEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 필수로 입력해주셔야 해요!");
        }
    }

    private void validateReservationInBusinessHour(LocalTime reservationTime) {
        if (reservationTime.isBefore(OPENING_TIME) || reservationTime.isAfter(CLOSING_TIME)) {
            throw new InvalidReservationTimeException("영업 시간외에는 예약이 불가능해요!");
        }
    }

    private void validateReservationTimeIsNotPast(LocalDate date, LocalTime time) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (date.isBefore(today)) {
            throw new IllegalArgumentException("지난 날짜로는 예약할 수 없어요!");
        }

        if (date.isEqual(today) && time.isBefore(now)) {
            throw new IllegalArgumentException("이미 지난 시간으로는 예약이 불가능해요!");
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
