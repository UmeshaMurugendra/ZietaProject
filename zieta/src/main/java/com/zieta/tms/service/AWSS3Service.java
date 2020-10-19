package com.zieta.tms.service;

import org.springframework.web.multipart.MultipartFile;



public interface AWSS3Service {

	void uploadFile(MultipartFile multipartFile, String objectKey);
	
	byte[] downloadFile(String keyName);
}

