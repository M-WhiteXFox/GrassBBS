package co.yiiu.grassbbs.hook;

import org.aspectj.lang.annotation.Pointcut;

public class FileUtilHook {

    @Pointcut("execution(public * co.yiiu.grassbbs.util.FileUtil.upload(..))")
    public void upload() {
    }

}
