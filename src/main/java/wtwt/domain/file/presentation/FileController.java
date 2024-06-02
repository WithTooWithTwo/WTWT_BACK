package wtwt.domain.file.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wtwt.common.doc.swagger.FileSwagger;
import wtwt.domain.auth.presentation.dto.response.FileResponse;
import wtwt.domain.file.application.FileService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FileController implements FileSwagger {

    private final FileService fileService;

    @PostMapping(
        value = "/file",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFile(@RequestPart MultipartFile file
    ) {
        return ResponseEntity.ok()
            .body(FileResponse.from(fileService.upload(file)));
    }

    @PostMapping(value = "/files",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<FileResponse>> uploadFiles(@RequestPart List<MultipartFile> files
    ) {
        List<FileResponse> response = fileService.upload(files).stream()
            .map(FileResponse::from)
            .toList();

        return ResponseEntity.ok()
            .body(response);
    }
}
