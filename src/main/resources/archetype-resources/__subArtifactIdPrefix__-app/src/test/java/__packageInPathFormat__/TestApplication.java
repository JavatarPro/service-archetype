package ${package};

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;


@SpringBootApplication(scanBasePackages = "${package}")
@ActiveProfiles("test")
public class TestApplication {
}
