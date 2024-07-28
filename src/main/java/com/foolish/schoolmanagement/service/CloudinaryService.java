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
      File uploadedFile = convertMultiPartToFile(file); // Tạo thành File Image hoàn chỉnh.
      Map uploadResult = cloudinary.uploader().upload(uploadedFile, Map.of());  // Kết quả sau khi upload.
      boolean isDeleted = uploadedFile.delete();

      if (isDeleted){
        System.out.println("File successfully deleted");
      }else
        System.out.println("File doesn't exist");
      return  uploadResult.get("url").toString(); // Trả về URL được lưu trong MapResult.
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //Hàm thực hiện việc gộp nhiều phần của File thành 1 File Image hoàn chỉnh.
  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }
}
