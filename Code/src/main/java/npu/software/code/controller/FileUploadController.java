package npu.software.code.controller;

import lombok.extern.slf4j.Slf4j;
import npu.software.code.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController {

    private static String url = "E:\\MajorInternship\\UndergraduatePTJManagementSystem\\Code\\src\\main\\resources\\images\\";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        log.info("上传文件：{}", originalFilename);

        // 保证文件名字是唯一的，防止文件覆盖
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf('.'));
        file.transferTo(new File(url + filename));
        return Result.success(url + filename);
    }
}
