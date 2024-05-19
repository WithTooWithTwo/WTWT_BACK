package wtwt.domain.file.application;

import org.springframework.web.multipart.MultipartFile;
import wtwt.domain.file.model.File;

public interface FileService {

    File upload(MultipartFile image);
}
