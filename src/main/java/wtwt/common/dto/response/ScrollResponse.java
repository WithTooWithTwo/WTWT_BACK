package wtwt.common.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Builder
@Schema(description = "스크롤 응답")
public record ScrollResponse<T>(
    @ArraySchema(
        schema = @Schema(description = "응답 데이터")
    )
    List<T> contents,
    @Schema(description = "응답 데이터가 비어있는지 여부", example = "true")
    boolean isEmpty,
    @Schema(description = "다음 커서", example = "1")
    String nextCursor,
    @Schema(description = "스크롤 사이즈", example = "10")
    int scrollSize
) {

}
