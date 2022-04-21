package movies;


import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;



class MovieRepositoryTest {

    Flyway flyway;
    MovieRepository repository;

    @BeforeEach
    void init(){
        JdbcDataSource dataSource = new JdbcDataSource();

        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");

        flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();


        repository = new MovieRepository(dataSource);
    }

    @Test
    void testDb(){
       repository.saveMovie(new Movie(1L, "Titanic", LocalDate.of(1992, 11, 2), 121));
    }

}