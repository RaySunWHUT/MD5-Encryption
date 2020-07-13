package sun.encryption.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.encryption.constants.Constants;
import sun.encryption.service.EncryptionService;
import sun.encryption.util.DLLUtil;
import sun.encryption.util.FileUtil;
import sun.encryption.util.JSONUtil;
import java.io.*;


@CrossOrigin
@RestController
@RequestMapping(value = {"/encryption"}, method = RequestMethod.POST)
public class EncryptionController {

    private static Logger logger = Logger.getLogger(EncryptionController.class);

    @Autowired
    EncryptionService encryptionService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody JSONObject jsonObject) {

        try {

            return encryptionService.register(jsonObject);

        } catch (Exception e) {

            e.printStackTrace();

            return JSONUtil.errorJSON(Constants.GLOBAL_EXCEPTION);

        }

    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject jsonObject) {

        return encryptionService.login(jsonObject);

    }

    @RequestMapping(value = {"/encrypt"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject encrypt(@RequestBody JSONObject jsonObject) {

        return encryptionService.encrypt(jsonObject);

    }

    @RequestMapping(value = {"/upload"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file) throws IOException {

        File fileDir = new File(Constants.DESTINATION);

        if(!fileDir.exists()) {

            // 如果没有目录应该创建目录
            fileDir.mkdirs();

        }

        // 获取图片名称
        String fileName = file.getOriginalFilename();

        // 生成文件总路径
        String path = Constants.DESTINATION + fileName;

        // 文件实现上传
        file.transferTo(new File(path));

        // 读取文件内容
        String content = FileUtil.readFileContent(path);

        // 对文件内容加密
        String encryption = DLLUtil.textEncryption(content);

        return JSONUtil.successJSON(encryption);

    }

}
