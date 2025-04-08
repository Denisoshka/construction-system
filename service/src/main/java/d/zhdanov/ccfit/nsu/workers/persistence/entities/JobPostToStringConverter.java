package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;

@WritingConverter
public class JobPostToStringConverter implements Converter<JobPost, JdbcValue> {
    @Override
    public JdbcValue convert(@Nonnull JobPost source) {
        return JdbcValue.of(source, JDBCType.OTHER);
    }
}
