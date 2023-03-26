package com.xcorp.springbootpractice.Model;

import lombok.Data;

@Data
public class FileUpload {
  private String fileName;
  private String fileDownloadUri;
  private long size;
}
