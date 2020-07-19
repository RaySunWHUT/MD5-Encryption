package sun.encryption.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * author: Sun Rui
 */
public class FileUtil {

    // 读取文件内容
    public static String readFileContent(String fileName) {

        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();

        try {

            reader = new BufferedReader(new FileReader(file));
            String tempStr;

            while ((tempStr = reader.readLine()) != null) {

                sbf.append(tempStr);

            }

            reader.close();

            return sbf.toString();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException e1) {

                    e1.printStackTrace();

                }

            }

        }

        return sbf.toString();

    }


    // 删除文件
//    public static boolean deleteFile(String filePath) {
//
//        File file = new File(filePath);
//
//        boolean isOk = false;
//
//        if (!file.isDirectory()) {
//
//            isOk = file.delete();
//
//        }
//
//        return isOk;
//
//    }

}
