package com.xcorp.springbootpractice.controller;

import com.xcorp.springbootpractice.entity.FileUpload;
import com.xcorp.springbootpractice.utils.FileDownloadUtils;
import com.xcorp.springbootpractice.utils.FileUploadUtils;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

  @PostMapping("/upload")
  public ResponseEntity<FileUpload> uploadFile(@RequestParam("file") MultipartFile multipartFile)
      throws IOException {
    String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    long size = multipartFile.getSize();

    String fileCode = FileUploadUtils.saveFile(filename, multipartFile);

    FileUpload fileUpload = new FileUpload();
    fileUpload.setFileName(filename);
    fileUpload.setFileDownloadUri("/download/" + fileCode);
    fileUpload.setSize(size);

    return new ResponseEntity<>(fileUpload, HttpStatus.OK);
  }

  @GetMapping("/download/{fileCode}")
  public ResponseEntity<?> downloadFile(@PathVariable String fileCode) throws IOException {
    FileDownloadUtils fileDownloadUtils = new FileDownloadUtils();
    Resource resource = null;
    try {
      resource = fileDownloadUtils.getFileAsResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }
    if (resource == null) {
      return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
    }

    String contentType = "application/octet-stream";
    String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
        .body(resource);
  }
}
