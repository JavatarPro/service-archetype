package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@RefreshScope
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication(scanBasePackages = "pro.javatar")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //NOSONAR
    }
}