package io.pivotal.pal.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class PalTrackerApplication {
    private InMemoryTimeEntryRepository inMemoryTimeEntryRepository = new InMemoryTimeEntryRepository();

    @Bean
    public TimeEntryRepository timeEntryRepository() {
        return inMemoryTimeEntryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }
}
