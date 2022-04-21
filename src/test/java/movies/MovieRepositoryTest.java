package movies;


import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


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
    void testSaveMovie(){
       Movie saved = repository.saveMovie(new Movie(1L, "Titanic", LocalDate.of(1992, 11, 2), 121));


       assertThat(saved.getId()).isNotEqualTo(null);
       assertThat(saved.getTitle()).isEqualTo("Titanic");
    }

}