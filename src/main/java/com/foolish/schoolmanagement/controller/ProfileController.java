package com.foolish.schoolmanagement.controller;

import com.foolish.schoolmanagement.model.Address;
import com.foolish.schoolmanagement.model.Profile;
import com.foolish.schoolmanagement.model.User;
import com.foolish.schoolmanagement.repo.UserRepo;
import com.foolish.schoolmanagement.service.CloudinaryService;
import com.foolish.schoolmanagement.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class ProfileController {
  private final UserRepo userRepo;
  // Vì Profile không phải là @Entity bean nên do đó không thể sử dụng được @Valid annotation. Nên do đó phải sử dụng Validator như sau.
  // reference: https://stackoverflow.com/questions/65773990/spring-boot-non-entity-bean-validation-not-working
  private final Validator validator;
  private final CloudinaryService cloudinaryService;
  private final UserService userService;

  @Autowired
  public ProfileController(UserRepo _userRepo, Validator _validator, CloudinaryService cloudinaryService, UserService userService) {
    super();
    this.userRepo = _userRepo;
    this.validator = _validator;
    this.cloudinaryService = cloudinaryService;
    this.userService = userService;
  }

  @GetMapping(value = "/profile")
  public String displayProfile(HttpSession httpSession, Model model, @RequestParam(name = "success", required = false) boolean success, Authentication authentication) {
    User user = (User) httpSession.getAttribute("user");
    if (user == null || user.getUserId() <= 0) {
      user = userService.findUserByEmail(authentication.getPrincipal().toString());  // Lấy thông tin user thông qua Email của user đã được xác thực.
    }
    Profile profile = new Profile();
    profile.setName(user.getName());
    profile.setEmail(user.getEmail());
    profile.setMobileNum(user.getMobileNum());
    profile.setUrlImg(user.getImg());
    if (user.getAddress() != null && user.getAddress().getAddressId() > 0) {
      profile.setAddress1(user.getAddress().getAddress1());
      profile.setAddress2(user.getAddress().getAddress2());
      profile.setCity(user.getAddress().getCity());
      profile.setState(user.getAddress().getState());
      profile.setZipcode(user.getAddress().getZipcode());
    }
    if (success) {
      model.addAttribute("message", "Update profile successfully!");
    }
    model.addAttribute("profile", profile);
    return "profile";
  }

  @PostMapping(value = "/profile")
  public String updateProfile(Profile profile, HttpSession httpSession, Model model, Authentication authentication) {
    Set<ConstraintViolation<Profile>> result = validator.validate(profile);
    if (!result.isEmpty()) {
      List<String> errors = new ArrayList<>();
      for (ConstraintViolation<Profile> constraint : result) {
        errors.add(constraint.getMessage());
      }
      model.addAttribute("profile", profile);
      model.addAttribute("errors", errors);
      return "profile";
    }
    User user = (User) httpSession.getAttribute("user");
    if (user == null || user.getUserId() <= 0) {
      user = userService.findUserByEmail(authentication.getPrincipal().toString());  // Lấy thông tin user thông qua Email của user đã được xác thực.
    }
    //  Lưu thông tin profile mới cập nhật vào database.
    user.setName(profile.getName());
    user.setEmail(profile.getEmail());
    user.setMobileNum(profile.getMobileNum());
    MultipartFile file = profile.getFile();
    if (file != null && !file.isEmpty()) {
      String url = cloudinaryService.uploadFile(file);
      user.setImg(url);
    }

    if (user.getAddress() == null || !(user.getAddress().getAddressId() > 0)) {
      user.setAddress(new Address());
    }
    user.getAddress().setAddress1(profile.getAddress1());
    user.getAddress().setAddress2(profile.getAddress2());
    user.getAddress().setCity(profile.getCity());
    user.getAddress().setState(profile.getState());
    user.getAddress().setZipcode(profile.getZipcode());
    user = userRepo.save(user);
    httpSession.setAttribute("user", user);
    return "redirect:/profile?success=true";
  }

}
