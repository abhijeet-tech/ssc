package rewards;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import config.RewardsConfig;

@Configuration
@ImportResource({
		"rewards/test-infrastructure-config.xml",
		"config/rewards-config.xml"
})
public class TestInfrastructureConfig {


}
