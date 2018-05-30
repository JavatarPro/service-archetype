package ${package};

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;


@SpringBootApplication(scanBasePackages = "pro.javatar")
@ActiveProfiles("test")
public class TestApplication {
}
