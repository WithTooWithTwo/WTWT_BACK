package wtwt.common.doc.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import wtwt.domain.auth.presentation.dto.response.FileResponse;

@Tag(name = "File", description = "파일 관련 API")
public interface FileSwagger {

    @Operation(summary = "파일 업로드", description = "크기 제한은 100MB")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "파일 업로드 성공",
            useReturnTypeSchema = true)
    })
    ResponseEntity<FileResponse> uploadFile(MultipartFile file);
}
