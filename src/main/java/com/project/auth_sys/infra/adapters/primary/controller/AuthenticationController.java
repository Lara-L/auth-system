package com.project.auth_sys.infra.adapters.primary.controller;

import com.project.auth_sys.application.service.AuthenticationService;
import com.project.auth_sys.domain.entity.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @Autowired
  private  AuthenticationService authenticationService;

  @PostMapping("authenticate")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
