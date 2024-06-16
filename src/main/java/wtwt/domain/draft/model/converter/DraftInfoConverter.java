package wtwt.domain.draft.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wtwt.domain.draft.model.DraftInfo;

@Component
@RequiredArgsConstructor
public class DraftInfoConverter implements AttributeConverter<DraftInfo, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(DraftInfo attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("DraftInfo를 JSON으로 변환하는데 실패했습니다.");
        }
    }

    @Override
    public DraftInfo convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, DraftInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON을 DraftInfo로 변환하는데 실패했습니다.");
        }
    }
}
