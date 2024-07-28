package com.foolish.schoolmanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
  private final Cloudinary cloudinary;

  @Autowired
  public CloudinaryService(Cloudinary cloudinary) {
    super();
    this.cloudinary = cloudinary;
  }

  public String uploadFile(MultipartFile file) {
    try {
      // Tạo MultipartFile thành một File hoàn chỉnh.
      File uploadedFile = convertMultiPartToFile(file);
      Map uploadResult = cloudinary.uploader().upload(uploadedFile, Map.of());
      boolean isDeleted = uploadedFile.delete();

      if (isDeleted){
        System.out.println("File successfully deleted");
      }else
        System.out.println("File doesn't exist");
      return  uploadResult.get("url").toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }
}
