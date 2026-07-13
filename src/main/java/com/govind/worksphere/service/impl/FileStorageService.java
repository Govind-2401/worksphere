package com.govind.worksphere.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String uploadProfileImage(Long employeeId, MultipartFile file);

    String uploadResume(Long employeeId, MultipartFile file);

    Resource downloadProfileImage(Long employeeId);

    Resource downloadResume(Long employeeId);
}