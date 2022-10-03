package com.example.ajax.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
public class AttachedFile {

    private Long fileId;
    private String filename;
    private String filePath;
    private String regId;
    private String regDt;
    private String modId;
    private String modDt;
    private String blindYn;
}
