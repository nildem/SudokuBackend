package sudoku.service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    
    /* Use Java 1.8
     * Run with "mvn spring-boot:run"
     * To access service use /move request  
     * */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
