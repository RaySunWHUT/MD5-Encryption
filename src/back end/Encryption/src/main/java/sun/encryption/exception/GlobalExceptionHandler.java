package sun.encryption.exception;

import com.alibaba.fastjson.JSONObject;
import sun.encryption.constants.Constants;
import sun.encryption.util.JSONUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 定义全局异常检查
 * author: Sun Rui
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕捉其他所有异常
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public JSONObject globalException() {

        return JSONUtil.errorJSON(Constants.GLOBAL_ERROR);
    }

}