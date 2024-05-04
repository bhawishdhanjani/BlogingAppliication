package com.bhawish.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bhawish.blog.services.FileService;


@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String fileExtension =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(fileExtension);
		String filename = UUID.randomUUID().toString() + fileExtension;	
		String filePath = path+File.separator+filename;
		File savedFile = new File(path);
		if(!savedFile.exists())
			savedFile.mkdir();
		Files.copy(file.getInputStream(), Path.of(filePath));
		return filename;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		String fullPath = path+ File.separator+filename;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
