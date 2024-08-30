package com.foolish.schoolmanagement.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login/oauth2/code/github")
@Controller
public class OAuthController {
  @GetMapping("")
  public void getCodeGitHub(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
    System.out.println("Code: " + code);
    System.out.println("State: " + state);
  }
}
