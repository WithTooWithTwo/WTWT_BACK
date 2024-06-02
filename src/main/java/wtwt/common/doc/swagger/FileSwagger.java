package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import wtwt.domain.file.presentation.dto.response.FileApiResponse;

@Tag(name = "File", description = "파일 관련 API")
public interface FileSwagger {

    @Operation(summary = "파일 업로드", description = "크기 제한은 100MB")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "파일 업로드 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<FileApiResponse> uploadFile(MultipartFile file);

    @Operation(summary = "다중 파일 업로드", description = "크기 제한은 100MB / 한 번에 10개까지 가능")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "파일 업로드 성공",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = FileApiResponse.class))
            ))
    })
    ResponseEntity<List<FileApiResponse>> uploadFiles(List<MultipartFile> files);
}
