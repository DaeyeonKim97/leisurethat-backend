package com.steady.leisurethatapi.attachment.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.steady.leisurethatapi.database.entity.Attachment;
import com.steady.leisurethatapi.database.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadS3Service {
    private final AmazonS3Client amazonS3Client;
    private final AttachmentRepository attachmentRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;
    @Value("${spring.servlet.multipart.location}")
    private String configLocation;

//    @Autowired
//    public UploadS3Service( AttachmentRepository attachmentRepository) {
//        this.attachmentRepository = attachmentRepository;
//    }

    public Attachment uploadFile(String category, MultipartFile multipartFile) {
        System.out.println(multipartFile.getOriginalFilename());
        validateFileExists(multipartFile);
        File uploadFile = convert(multipartFile);
        System.out.println("uploadFile path : "+uploadFile.getPath());
        File configFile = new File(configLocation +"/"+ uploadFile.getPath());

        String originalFilename = multipartFile.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String fileName = category+ "/" + UUID.randomUUID() + "." + ext;
//        long size = multipartFile.getSize();

//        File file = new File()

        System.out.println("file name: "+fileName);
        Attachment attachment = null;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
//        objectMetadata.setContentLength(size);

//        try (InputStream inputStream = multipartFile.getInputStream()) {
            System.out.println(bucketName);
            System.out.println("client : "+amazonS3Client);
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, configFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            String imagePath = amazonS3Client.getUrl(bucketName,fileName).toString();
            System.out.println("imagePath: " + imagePath);
            attachment = new Attachment(0,originalFilename,fileName,"https://leisurethat-image.s3.ap-northeast-2.amazonaws.com/"+fileName,new Date());
            attachmentRepository.save(attachment);
//        } catch (IOException e) {
//            System.out.println("upload s3 error");
//            throw new RuntimeException();
//        }
        configFile.delete();
        return attachment;
    }

    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            System.out.println("file is empty");
            throw new RuntimeException();
        }
    }

    private File convert(MultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }
}
