package sun.encryption.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.encryption.constants.Constants;
import sun.encryption.service.EncryptionService;

import sun.encryption.util.*;

import java.io.File;
import java.io.IOException;

@Service("encryptionService")
@Transactional
public class EncryptionServiceImpl implements EncryptionService {

    private static Logger logger = Logger.getLogger(EncryptionServiceImpl.class);

    @Override
    public JSONObject register(JSONObject jsonObject)  {

        try {

            String macAddress = NetworkUtil.getWirelessMacAddress();

            String encryptMacAddress = DLLUtil.textEncryption(macAddress);

            if (jsonObject.getString("registerCode").equals(encryptMacAddress)) {

                return JSONUtil.successJSON("register successful!");

            } else {

                return JSONUtil.errorJSON("register code error!");

            }

        } catch (Exception e) {

            e.printStackTrace();

            return JSONUtil.errorJSON(Constants.GLOBAL_EXCEPTION);

        }




    }

    @Override
    public JSONObject login(JSONObject jsonObject) {

        String userName = jsonObject.getString("username");

        String password = jsonObject.getString("password");

        // 是否存在对应键值
        String isKeyExist = RegistryUtil.readKeyFromRegistry(Constants.USERNAME);

        // 若不存在, 写入键值
        if (isKeyExist == null) {

            String md5Pwd = DLLUtil.textEncryption(Constants.PASSWORD);

            boolean isOk = RegistryUtil.writeToRegistry(Constants.USERNAME, md5Pwd);

            if (!isOk) {    // 若写入失败

                return JSONUtil.errorJSON("Registry write failed!");

            }

        }


        if (userName.equals(Constants.USERNAME)) {

            logger.info(password);

            String md5Password = RegistryUtil.readKeyFromRegistry(userName);

            String blackPassword = DLLUtil.textEncryption(password);

            logger.info(md5Password);

            logger.info(blackPassword);

            // 若加密后的密码与注册表中使用md5加密的密码相同, 则返回成功
            if (blackPassword.equals(md5Password)) {

                return JSONUtil.successJSON("login successful");

            } else {

                return JSONUtil.errorJSON("password error");

            }

        } else {

            return JSONUtil.errorJSON("username error");

        }

    }


    @Override
    public JSONObject encrypt(JSONObject jsonObject) {

        String message = jsonObject.getString("message");

        String encryption = DLLUtil.textEncryption(message);

        return JSONUtil.successJSON(encryption);

    }


    public static void main(String[] args) {

        // 对文件内容加密
        String encryption = DLLUtil.textEncryption("123456789");

        System.out.println(encryption);

    }

}
