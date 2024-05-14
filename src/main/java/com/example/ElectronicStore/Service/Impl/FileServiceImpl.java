
//This is for uploading image-> Need to revisit in future
package com.example.ElectronicStore.Service.Impl;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ElectronicStore.Service.FileService;
@Service

public class FileServiceImpl implements FileService {
	private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Override
	public String uploadFile(MultipartFile file, String path) {
		// TODO Auto-generated method stub
		String originalFileName=file.getOriginalFilename();
		logger.info("FileName: {}",originalFileName);
		String filename=UUID.randomUUID().toString();
		String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileWithExtension=filename+extension;
		String filePathWithFileName=path+File.separator+fileWithExtension;
		
		
		return null;
	}

	@Override
	public InputStream getResource(String path, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
