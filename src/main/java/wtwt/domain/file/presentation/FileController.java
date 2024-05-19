package wtwt.domain.file.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wtwt.common.doc.swagger.FileSwagger;
import wtwt.domain.auth.presentation.dto.response.FileResponse;
import wtwt.domain.file.application.FileService;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController implements FileSwagger {

    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFile(@RequestParam MultipartFile file
    ) {
        return ResponseEntity.ok()
            .body(FileResponse.from(fileService.upload(file)));
    }
}
