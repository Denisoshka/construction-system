package d.zhdanov.ccfit.nsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
/*@EnableJdbcRepositories(basePackages = {
  "d.zhdanov.ccfit.nsu.objects.persistence",
  "d.zhdanov.ccfit.nsu.workers.persistence",
  "d.zhdanov.ccfit.nsu.activity.persistence"
})*/
public class ConstructionServiceService {
  public static void main(String[] args) {
    SpringApplication.run(ConstructionServiceService.class, args);
  }
}
