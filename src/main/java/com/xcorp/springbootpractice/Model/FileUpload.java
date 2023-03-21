package com.xcorp.springbootpractice.Model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileUpload {
    private String fileName;
    private String fileDownloadUri;
    private long size;


}
