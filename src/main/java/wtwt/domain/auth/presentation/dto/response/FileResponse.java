package wtwt.domain.auth.presentation.dto.response;

import wtwt.domain.file.model.File;

public record FileResponse(
    String fileUrl
) {

    public static FileResponse from(File file) {
        return new FileResponse(file.url());
    }
}
