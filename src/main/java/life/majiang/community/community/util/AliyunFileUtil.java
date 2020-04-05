package life.majiang.community.community.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-03 20:30
 **/
@Component
public class AliyunFileUtil {

    //外网访问地域节点
    //@Value("${aliyun.oss.endpoint}")
    private static String endpoint="http://oss-cn-beijing.aliyuncs.com";
    //你的accessKeyId
    //@Value("${aliyun.oss.accessKeyId}")
    private static String accessKeyId="LTAI4FuMPpeDiSHswVmGmwFa";
    //你的accessKeySecret
    //@Value("${aliyun.oss.accessKeySecret}")
    private static String accessKeySecret="4xSQpNqDswLbKY4EAi400oiD7AgI7e";

    public void opration ()
    {
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        ossClient.shutdown();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMdd");
        // 原图地址
        String fileName = "E:/Pictures/huiyaunmaimeng.jpg";
        String bucketName ="xiaofeichai";
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(fileName);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, objectName, file);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();
        System.out.println(url.toString());

    }

}
