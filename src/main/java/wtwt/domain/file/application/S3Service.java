package wtwt.domain.file.application;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import wtwt.core.properties.S3Properties;
import wtwt.domain.file.model.File;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {

    private final S3Client s3Client;
    private final S3Properties s3Properties;
    private final MultipartProperties multipartProperties;

    @Override
    public File upload(MultipartFile file) {
        validate(file, multipartProperties.getMaxFileSize());
        String fileName = createUniqueFileName(file);

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                .bucket(s3Properties.baseBucket())
                .key(s3Properties.keyPrefix() + "/" + fileName)
                .build();

            s3Client.putObject(putOb,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는 도중 예외가 발생했습니다.");
        }

        return File.builder()
            .url(s3Properties.basePath() + "/" + s3Properties.keyPrefix() + "/" + fileName)
            .build();
    }

    private String createUniqueFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();

        assert originalFileName != null;
        int fileExtensionIndex = originalFileName.lastIndexOf(".");
        String fileExtension = originalFileName.substring(fileExtensionIndex);

        return UUID.randomUUID() + fileExtension;
    }

    private static void validate(MultipartFile file, DataSize fileMaxSize) {
        Assert.isTrue(Objects.nonNull(file) && !file.isEmpty(), "파일과 함께 요청을 보내주세요.");
        Assert.isTrue(file.getSize() <= fileMaxSize.toBytes(),
            "파일 용량은 " + fileMaxSize.toMegabytes() + "MB 이하여야합니다.");
    }

}
