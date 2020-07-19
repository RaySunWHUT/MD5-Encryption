package sun.encryption.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.encryption.constants.Constants;
import sun.encryption.service.EncryptionService;

import sun.encryption.util.*;

/**
 * author: Sun Rui
 */
@Service("encryptionService")
@Transactional
public class EncryptionServiceImpl implements EncryptionService {

    private static Logger logger = Logger.getLogger(EncryptionServiceImpl.class);

    @Override
    public JSONObject register(JSONObject jsonObject)  {

        String registerCode = jsonObject.getString("registerCode");

        try {

            String macAddress = NetworkUtil.getWirelessMacAddress();

            String encryptMacAddress = DLLUtil.textEncryption(macAddress);

            if (registerCode.equals(encryptMacAddress)) {

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
        // 返回的是对应主键的值
        boolean isKeyExist = RegistryUtil.isKeyExists(userName);

        // 若用户不存在
        if (!isKeyExist) {

            return JSONUtil.errorJSON("username don't exist!");

        } else {    // 若用户存在

            String md5Password = DLLUtil.textEncryption(password);

            String blackPassword = RegistryUtil.readKeyFromRegistry(userName);

            if (md5Password.equals(blackPassword)) {    // 若密码正确

                return JSONUtil.successJSON();

            } else {

                return JSONUtil.errorJSON("username or password error!");

            }

        }

    }


    @Override
    public JSONObject userRegister(JSONObject jsonObject) {

        String userName = jsonObject.getString("username");

        String password = jsonObject.getString("password");

        // 是否存在对应键值
        boolean isKeyExists = RegistryUtil.isKeyExists(userName);

        // 若不存在, 写入键值
        if (!isKeyExists) {

            String md5Pwd = DLLUtil.textEncryption(password);

            boolean isOk = RegistryUtil.writeToRegistry(userName, md5Pwd);

            if (!isOk) {    // 若写入失败

                return JSONUtil.errorJSON("Registry write failed!");

            }

        } else {    // 用户名已存在

            return JSONUtil.errorJSON("username exists!");

        }

        return JSONUtil.successJSON("register successful!");

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
