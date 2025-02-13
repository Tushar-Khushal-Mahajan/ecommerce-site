package com.ecommerce.services;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class AmazonS3Service {

	@Value("${aws.s3.bucketName}")
	private String bucketName;

	private final S3Client s3Client;

	public AmazonS3Service(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	/**
	 * FOR UPLOAD IMAGE TO S3
	 * 
	 * @param fileName name of the file i.e., ObjectId
	 * @param filePath Contain full Path of the image
	 * @return
	 */
	public PutObjectResponse uploadFile(String fileName, String filePath) {
		Path path = Paths.get(filePath);
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();

		return s3Client.putObject(putObjectRequest, path);
	}

	public String deleteFile(String fileName) {
		try {
			DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder().bucket(bucketName).key(fileName)
					.build();

			DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(deleteObjectRequest);

			return "File '" + fileName + "' deleted successfully from bucket '" + bucketName + "'.";
		} catch (S3Exception e) {
			throw new RuntimeException(
					"Failed to delete file '" + fileName + "' from bucket '" + bucketName + "': " + e.getMessage(), e);
		}
	}
}
