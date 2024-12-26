package by.polly.beatyshop.modules.core.images;


import by.polly.beatyshop.modules.product.api.dto.Image;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceStorage {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    private final Map<String, String> supportedExtensions = new HashMap<>();

    @PostConstruct
    private void init() {
        try {
            createBucket();
            supportedExtensions.put("jpeg", "image/jpeg");
            supportedExtensions.put("jpg", "image/jpeg");
            supportedExtensions.put("heic", "image/heic");
            supportedExtensions.put("png", "image/png");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void createBucket() {
        try (InputStream policyJson = getClass().getClassLoader().getResourceAsStream("minio/minio-config.json")) {
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(minioProperties.getBucket())
                            .build()
            );
            if (!found) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(minioProperties.getBucket())
                                .build()
                );
                SetBucketPolicyArgs policyArgs = SetBucketPolicyArgs.builder()
                        .bucket("images")
                        .config(new String(policyJson.readAllBytes()))
                        .build();
                minioClient.setBucketPolicy(policyArgs);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during creating image bucket: " + e.getMessage());
        }
    }

    public String uploadToStorage(Image image) throws RuntimeException {
        MultipartFile file = image.getFile();
        if (file.getOriginalFilename() == null || file.getOriginalFilename().isBlank())
            throw new RuntimeException("Invalid file name");
        String fileName = generateStringFileName(file);
        try {
            InputStream inputStream = file.getInputStream();
            saveImage(inputStream, fileName, file);
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Image upload exception: " + e.getMessage());
        }
    }

    private void saveImage(InputStream inputStream, String fileName, MultipartFile file) throws Exception {
        String extension = getFileExtension(file);
        if (!supportedExtensions.containsKey(extension))
            throw new RuntimeException("Incorrect image extension!");
        minioClient.putObject(
                PutObjectArgs.builder()
                        .stream(inputStream, inputStream.available(), -1)
                        .object(fileName)
                        .bucket(minioProperties.getBucket())
                        .contentType(supportedExtensions.get(extension))
                        .build()
        );
    }

    private String generateStringFileName(MultipartFile multipartFile) throws RuntimeException {
        String extension = getFileExtension(multipartFile);
        return String.format("%s.%s", UUID.randomUUID(), extension);
    }

    private String getFileExtension(MultipartFile multipartFile) throws RuntimeException {
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        if (extension == null || extension.isEmpty()) {
            throw new RuntimeException("Unsupported file extension!");
        }
        return extension;
    }
}
