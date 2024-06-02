package wtwt.domain.file.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import wtwt.domain.file.model.File;

@Schema(description = "파일 응답")
public record FileApiResponse(
    @Schema(description = "파일 URL", example = "https://wtwt-dev-bucket.s3.ap-northeast-2.amazonaws.com/public/image/6d348778-8487-4af9-9831-cbe805026c49.png")
    String fileUrl
) {

    public static FileApiResponse from(File file) {
        return new FileApiResponse(file.url());
    }
}
