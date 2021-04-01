package com.cos.oauth2jwt.web.user.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserProfileUpdateDto {
   private MultipartFile file;
}