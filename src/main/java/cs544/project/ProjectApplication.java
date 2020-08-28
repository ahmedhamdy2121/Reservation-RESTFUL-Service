/**
 * Ahmed Hamdy
 */
package cs544.project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Ahmed Hamdy
 *
 */
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    // for DTO
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
