package goodbuyning.api_server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing 활성화
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
