package asia.wjm.handler;

import asia.wjm.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(RuntimeException ex){
        log.error("异常信息：", ex); // 打印完整堆栈
        return Result.error(ex.getMessage());
    }

    //捕捉违反了SQL完整性约束错误
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message=ex.getMessage();
        //处理用户名重复的情况
        if(ex.getMessage().contains("Duplicate entry")){
            String username=message.split(" ")[2];
            return Result.error("用户名:"+username+"已存在");
        }
        return Result.error(message);
    }
}
