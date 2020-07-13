package sun.encryption.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EncryptionService {

    JSONObject register(JSONObject jsonObject) throws Exception; // 注册
    JSONObject login(JSONObject jsonObject);     // 登录

    JSONObject encrypt(JSONObject jsonObject);  // 加密文本

}
