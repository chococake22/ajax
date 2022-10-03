package com.example.ajax.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
