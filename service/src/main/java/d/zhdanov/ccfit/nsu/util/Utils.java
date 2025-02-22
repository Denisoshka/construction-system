package d.zhdanov.ccfit.nsu.util;

import d.zhdanov.graphql.types.Pagination;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class Utils {

  @NotNull
  public static Pageable getPageable(Pagination pagination) {
    if (pagination == null) {
      return Pageable.unpaged();
    }
    return PageRequest.of(pagination.getPage(), pagination.getPageSize());
  }
}
