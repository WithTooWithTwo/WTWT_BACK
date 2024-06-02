package wtwt.domain.file.application;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import wtwt.domain.file.model.File;

public interface FileService {

    File upload(MultipartFile image);

    List<File> upload(List<MultipartFile> images);
}
