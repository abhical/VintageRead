package com.example.ElectronicStore.Service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String uploadFile(MultipartFile file, String path);
	
	public InputStream getResource(String path, String name);

}
