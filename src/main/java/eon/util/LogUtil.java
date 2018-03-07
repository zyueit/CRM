package eon.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import eon.domain.Employee;
import eon.domain.SystemLog;
import eon.service.ISystemLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//遗留问题：?????判断访问方法是否成功并记录日志

public class LogUtil {
    @Autowired
    private ISystemLogService systemLogService;

    public void writeLog(JoinPoint joinPoint) throws Exception {
        Object target = joinPoint.getTarget();
        if (target instanceof ISystemLogService) {
            return;
        }
        SystemLog systemLog = new SystemLog();
        HttpServletRequest request = UserContext.getRequest();
        systemLog.setOpUser((Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION));
        systemLog.setOpTime(new Date());
        systemLog.setOpIp(request.getRemoteAddr());
        //存储访问的service方法，如：eon.service.impl.EmployeeServiceImpl:queryByLogin
        systemLog.setFunction(target.getClass().getName() + ":" + joinPoint.getSignature().getName());
        ObjectMapper objectMapper = new ObjectMapper();
        //舍去值为null的属性或对象
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //将访问service的参数数组转换为json字符串存储
        //?????在这里转换json时，由于admin判断出现空指针，事务没有回滚
        systemLog.setParams(objectMapper.writeValueAsString(joinPoint.getArgs()));
        systemLogService.save(systemLog);
    }
}
