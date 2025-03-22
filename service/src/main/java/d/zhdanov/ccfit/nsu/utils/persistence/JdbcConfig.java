package d.zhdanov.ccfit.nsu.utils.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.JobPostToStringConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.List;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {
  @NotNull
  @Override
  public List<?> userConverters() {
    return List.of(new JobPostToStringConverter());
  }
}