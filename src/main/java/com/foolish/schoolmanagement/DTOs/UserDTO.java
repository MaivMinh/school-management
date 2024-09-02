package com.foolish.schoolmanagement.DTOs;

import com.foolish.schoolmanagement.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  public int userId;
  public String name;
  public String mobileNum;
  public String email;
  public String img;
  public Roles roles;
}
