package cn.czh0123.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    //阿里域名
    public static final String ALI_DOMAIN = "https://czh0123-text.oss-cn-guangzhou.aliyuncs.com/";

    public static String uploadImage(MultipartFile file) throws IOException {
        //生成文件名
        String originalFilename = file.getOriginalFilename(); //原来的图片名
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        //地域节点
        String endpoint = "http://oss-cn-guangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tGQUpuc5EwDcJ94GJ1t";
        String accessKeySecret = "fYy0DdfrrFBwkyfXEB5sgSgY266NrR";
        //OSS客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "czh0123-text", //仓库名
                fileName, //文件名
                file.getInputStream()
        );
        ossClient.shutdown();
        return ALI_DOMAIN + fileName;
    }
}
