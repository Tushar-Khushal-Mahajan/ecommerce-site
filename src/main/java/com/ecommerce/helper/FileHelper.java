package com.ecommerce.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.services.AmazonS3Service;

public class FileHelper {

	private AmazonS3Service s3Service;

	public FileHelper(AmazonS3Service s3Service) {
		this.s3Service = s3Service;
	}

	public String saveImageAndReturnImageNameInS3(MultipartFile file) throws IOException {

		String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

		Path tempPath = Files.createTempFile(fileName, ".tmp");

		Files.copy(file.getInputStream(), tempPath, StandardCopyOption.REPLACE_EXISTING);

		s3Service.uploadFile(fileName, tempPath.toString());

		Files.deleteIfExists(tempPath);

		return fileName;
	}

}
