package sun.encryption.util;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class DLLUtil {

    public interface CLibrary extends Library {

//        CLibrary INSTANCE = (CLibrary) Native.load(Platform.isWindows() ? "msvcrt": "c", CLibrary.class);
//        void printf(String format, Object...args);

        CLibrary INSTANCE = (CLibrary) Native.load("md5", CLibrary.class);

        String MD5Encrypt(String text);

    }


    // 使用MD5算法进行加密
    public static String textEncryption(String text) {

        // 返回的加密后text, 是以空格为分隔符的小写的加密序列
        String md5Text = CLibrary.INSTANCE.MD5Encrypt(text);

        return md5Text.replace(" ", "").toUpperCase();

    }


    // 根据算法种类进行加密
    public String textEncryption(String text, String encryptionAlgorithm) {

        switch (encryptionAlgorithm) {

            case "MD5":
            case "AES":
            case "DES":
                break;

        }

        return CLibrary.INSTANCE.MD5Encrypt(text);

    }

    public static void main(String[] args) {

        String cc = textEncryption("Hello everyone!");

        System.out.println(cc);

    }

}
