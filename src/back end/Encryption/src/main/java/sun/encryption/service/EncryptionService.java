package sun.encryption.service;

import com.alibaba.fastjson.JSONObject;

/**
 * author: Sun Rui
 */
public interface EncryptionService {

    JSONObject register(JSONObject jsonObject) throws Exception; // 注册
    JSONObject login(JSONObject jsonObject);     // 登录

    JSONObject encrypt(JSONObject jsonObject);  // 加密文本


    JSONObject userRegister(JSONObject jsonObject); // 注册

}
