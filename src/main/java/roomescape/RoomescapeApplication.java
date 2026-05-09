package roomescape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RoomescapeApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RoomescapeApplication.class, args);
    }

    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE IF EXISTS reservation");
        jdbcTemplate.execute("CREATE TABLE reservation(id SERIAL PRIMARY KEY, name VARCHAR(255), date DATE, time TIME)");

        System.out.println("JDBC 초기화 완료, h2 db connected...");
    }
}
