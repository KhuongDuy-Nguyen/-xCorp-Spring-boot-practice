package com.xcorp.springbootpractice.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownloadUtils {
  private Path foundFile;

  public Resource getFileAsResource(String fileCode) throws IOException {
    Path uploadPath = Paths.get("File-uploads");

    Files.list(uploadPath)
        .forEach(
            file -> {
              if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
              }
            });
    if (foundFile != null) {
      return new UrlResource(foundFile.toUri());
    }

    return null;
  }
}
