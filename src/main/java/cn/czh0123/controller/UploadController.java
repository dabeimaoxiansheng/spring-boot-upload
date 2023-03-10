package cn.czh0123.controller;

import cn.czh0123.util.UploadUtil;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        //file校验
        if (file.isEmpty()) {
            return "图片上传失败";
        }
        //file重命名 (a: 1.png   b:1.png)
        String originalFilename = file.getOriginalFilename(); //原来的图片名
        String ext = "." + originalFilename.split("\\.")[1]; // 1.png
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        //上传图片
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\src\\main\\resources\\static\\images\\"; //    /
        String path = pre + fileName;
        try {
            file.transferTo(new File(path));
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "图片上传失败";
    }

    @PostMapping("/upImg")
    public String upImg(MultipartFile file) throws IOException {
        return UploadUtil.uploadImage(file);
    }
}
