package com.xcorp.springbootpractice.entity;

import lombok.Data;

@Data
public class FileUpload {
  private String fileName;
  private String fileDownloadUri;
  private long size;
}
