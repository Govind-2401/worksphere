package com.govind.worksphere.service.impl;

import com.govind.worksphere.entity.Employee;
import com.govind.worksphere.exception.EmployeeNotFoundException;
import com.govind.worksphere.exception.InvalidFileException;
import com.govind.worksphere.repository.EmployeeRepository;
import com.govind.worksphere.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final EmployeeRepository employeeRepository;

    public FileStorageServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String uploadProfileImage(Long employeeId, MultipartFile file) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId
                        ));

        // File Empty Validation
        if (file.isEmpty()) {
            throw new InvalidFileException("Profile image is required.");
        }

        // File Type Validation
        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.toLowerCase().endsWith(".jpg")
                        || fileName.toLowerCase().endsWith(".jpeg")
                        || fileName.toLowerCase().endsWith(".png"))) {

            throw new InvalidFileException("Only JPG, JPEG and PNG files are allowed.");
        }

        try {

            // Create uploads/images directory
            Path uploadPath = Paths.get(uploadDir, "images");

            System.out.println("Upload Directory : " + uploadPath.toAbsolutePath());

            createDirectoryIfNotExists(uploadPath);

            // Generate unique filename
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());

            // Target file path
            Path targetPath = uploadPath.resolve(uniqueFileName);

            Files.copy(file.getInputStream(), targetPath,
                    StandardCopyOption.REPLACE_EXISTING);

            // Save filename in database
            employee.setProfileImage(uniqueFileName);
            employeeRepository.save(employee);

            System.out.println("File Saved At : " + targetPath.toAbsolutePath());

            return "Profile image uploaded successfully.";

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload profile image.");
        }
    }

    @Override
    public String uploadResume(Long employeeId, MultipartFile file) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId
                        ));

        // File Empty Validation
        if (file.isEmpty()) {
            throw new InvalidFileException("Resume file is required.");
        }

        // File Type Validation
        String fileName = file.getOriginalFilename();

        if (fileName == null || !fileName.toLowerCase().endsWith(".pdf")) {
            throw new InvalidFileException("Only PDF files are allowed.");
        }

        try {

            // Create uploads/resumes directory
            Path uploadPath = Paths.get(uploadDir, "resumes");

            System.out.println("Upload Directory : " + uploadPath.toAbsolutePath());

            createDirectoryIfNotExists(uploadPath);

            // Generate unique filename
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());

            // Target file path
            Path targetPath = uploadPath.resolve(uniqueFileName);

            Files.copy(file.getInputStream(), targetPath,
                    StandardCopyOption.REPLACE_EXISTING);

            // Save filename in database
            employee.setResumeFile(uniqueFileName);
            employeeRepository.save(employee);

            System.out.println("File Saved At : " + targetPath.toAbsolutePath());

            return "Resume uploaded successfully.";

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload resume.");
        }
    }

    private void createDirectoryIfNotExists(Path path) {

        try {

            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory Created : " + path.toAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create upload directory.");
        }
    }

    private String getFileExtension(String fileName) {

        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String generateUniqueFileName(String originalFileName) {

        return System.currentTimeMillis() + "_" + originalFileName;
    }

    @Override
    public Resource downloadProfileImage(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId
                        ));

        if (employee.getProfileImage() == null) {
            throw new RuntimeException("Profile image not found.");
        }

        try {

            Path filePath = Paths.get(uploadDir, "images",
                    employee.getProfileImage());

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Profile image not found.");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while reading profile image.");
        }
    }

    @Override
    public Resource downloadResume(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with ID: " + employeeId
                        ));

        if (employee.getResumeFile() == null) {
            throw new RuntimeException("Resume file not found.");
        }

        try {

            Path filePath = Paths.get(uploadDir, "resumes",
                    employee.getResumeFile());

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Resume file not found.");
            }

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while reading resume.");
        }
    }
}