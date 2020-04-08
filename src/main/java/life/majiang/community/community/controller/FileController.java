package life.majiang.community.community.controller;

import life.majiang.community.community.dto.FileDTO;
import life.majiang.community.community.enums.FileUploadStatusEnum;
import life.majiang.community.community.provider.AliyunFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-02 16:12
 **/
@Controller
public class FileController {


    @Autowired
    private AliyunFileProvider aliyunFileProvider;


    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {

            String fileName = aliyunFileProvider.upload(file);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setUrl(fileName);
            fileDTO.setSuccess(FileUploadStatusEnum.UPLOAD_SUCCESS.getStatus());
            return fileDTO;
        } catch (Exception e) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(FileUploadStatusEnum.UPLOAD_FAILD.getStatus());
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }

    }
}
