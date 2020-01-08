package com.cnaidun.police.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.cnaidun.police.constant.Constant;
import com.cnaidun.police.entity.RequestInfo;
import com.cnaidun.police.entity.UserInfo;
import com.cnaidun.police.service.RequestInfoService;
import com.cnaidun.police.util.IpAdrressUtil;
import com.cnaidun.police.util.JacksonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ClassName LogAop
 * @Description TODO
 * @Author zhouyw
 * @Date 2019/12/9 17:56
 * @Version 1.0
 */

@Aspect
@Component
@Slf4j
public class LogAop {


    @Autowired
    private RequestInfoService requestInfoService;

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        //打印空行方便阅读
//        log.info("--------------->日志打印ing<------------- : ");
//        log.info("---->请求URL : " + request.getRequestURL().toString());
//        log.info("---->HTTP方法 : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("---->方法名 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("---->参数值 : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "webLog()", returning = "rvt")
    public void doAfterReturning(JoinPoint joinPoint, Object rvt) throws Throwable {

        //保存日志
        RequestInfo info = new RequestInfo();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //方法说明
        String operation = method.getAnnotation(ApiOperation.class).value();
        info.setApiOperation(operation);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = null;
        try {
            params = JacksonUtil.obj2json(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        info.setRequestParam(params);
        Session session = SecurityUtils.getSubject().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Constant.SESSION_USER_INFO);
        if (null == userInfo) {
            info.setRequestUser("未知用户");
        } else {
            info.setRequestUser(userInfo.getAccount());
        }
        //获取用户ip地址
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        info.setIp(IpAdrressUtil.getIpAdrress(request));
        info.setResponseResult(JSONObject.toJSONString(rvt));
        //调用service保存 requestInfo实体类到数据库
         requestInfoService.insert(info);
    }


}
