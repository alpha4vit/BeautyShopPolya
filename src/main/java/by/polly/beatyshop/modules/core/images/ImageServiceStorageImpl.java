package by.polly.beatyshop.modules.core.images;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceStorageImpl {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @PostConstruct
    private void init() {
        try {
            createBucket();
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
}
