package life.majiang.community.community.provider;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: community
 * @author: liumq
 * @create: 2020-04-07 11:13
 **/
@Component
//@PropertySource("classpath:application.properties")
//@EnableConfigurationProperties
public class AliyunFileProvider {
    //外网访问地域节点
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    //你的accessKeyId
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    // 你的accessKeySecret
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public void opration() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ossClient.shutdown();
    }


    public String upload(MultipartFile file) throws IOException {

        // 初始化阿里云oss客户端
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


        //本地统一临时缓存图片的地址
        String currentPath = System.getProperty("user.dir");
        String recivePath = currentPath + "/upload/pictures";
        File dir = new File(recivePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 原始图片的名称
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String finalFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            //后缀名
            String suffixName = filePaths[filePaths.length - 1];
            finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + "." + suffixName;
        } else {
            return null;
        }


        // 生成上传文件名
        String objectName = sdf.format(new Date()) + "_" + finalFileName;
        FileOutputStream imgOut = new FileOutputStream(new File(dir, objectName));
        imgOut.write(file.getBytes());
        String allPathFileName = dir + "\\" + objectName;
        File fileNew = new File(allPathFileName);
        ossClient.putObject(bucketName, objectName, fileNew);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();

       // fileNew.deleteOnExit();
        if(fileNew.isFile()&&fileNew.exists())
        {
            // 先关闭流,再删除文件
            imgOut.close();
            fileNew.delete();
        }

        return url.toString();
    }

}
