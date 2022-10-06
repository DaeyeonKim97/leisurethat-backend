package com.steady.leisurethatapi.attachment.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class UploadS3Service {
    private AmazonS3Client amazonS3Client;
    private final AttachmentRepository attachmentRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public UploadS3Service( AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public String uploadFile(String category, MultipartFile multipartFile) {

        validateFileExists(multipartFile);

        String originalFilename = multipartFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String fileName = category+ UUID.randomUUID() + "." + ext;



        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            Attachment attachment = new Attachment(0,originalFilename,fileName,"https://leisurethat-image.s3.ap-northeast-2.amazonaws.com/"+fileName,new Date());
            attachmentRepository.save(attachment);
        } catch (IOException e) {
            System.out.println("upload s3 error");
            throw new RuntimeException();
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            System.out.println("file is empty");
            throw new RuntimeException();
        }
    }
}
