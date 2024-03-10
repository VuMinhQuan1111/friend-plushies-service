package com.crms.model.request.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by thanhtrung on 10/4/19
 */

@Getter
@Setter
public class UploadingFileRequest {

    private MultipartFile file;
    private Long userId;
}
