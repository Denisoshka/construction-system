package d.zhdanov.ccfit.nsu.objects.controllers;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import d.zhdanov.ccfit.nsu.objects.service.BrigadeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {BrigadeDataFetcher.class})
class BrigadeDataFetcherTest {
  @Autowired
  private DgsQueryExecutor dgsQueryExecutor;
  
  private BrigadeService brigadeService;
  
  @Test
  public void testBrigadesByConstructionSite() {
    // Подготовка тестовых данных
    UUID siteId = UUID.fromString("d0e018be-f419-4576-9806-1712990d60d1");
    Pagination pagination = new Pagination(0, 10);
    BrigadeDTO brigade = new BrigadeDTO();
    brigade.setForemanId(UUID.randomUUID());
    brigade.setSiteId(siteId);
    List<BrigadeDTO> expectedBrigades = List.of(brigade);
    
    // Настройка поведения mock-объекта
    Mockito.when(brigadeService.findAllBrigadesBySite(siteId, pagination)).thenReturn(expectedBrigades);
    
    // Выполнение GraphQL-запроса
    String query = String.format("{ brigadesByConstructionSite(id: \"%s\", pagination: {page: 0, size: 10}) { foremanId siteId } }", siteId);
    List<BrigadeDTO> result = dgsQueryExecutor.executeAndExtractJsonPath(query, "data.brigadesByConstructionSite");
    
    // Проверка результатов
    assertThat(result).isEqualTo(expectedBrigades);
  }
}