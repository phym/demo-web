package com.ssm.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.framework.log.annotation.LogDescription;
import com.ssm.framework.model.user.User;
import com.ssm.framework.service.user.UserService;
import com.ssm.framework.sys.SystemCustomerPrincipal;
import com.ssm.framework.sys.SystemCustomerPrincipalToken;
import com.ssm.framework.sys.SystemSessionManager;
import com.ssm.framework.utils.ConstantDef;

@Controller
public class BaseController {
    
    /**
     * logger
     */
    Logger logger =LoggerFactory.getLogger(this.getClass());
    /**
     * 用户Service
     */
    @Autowired
    private UserService userService;
    /**
     * 
     * 用户登录
     *
     * @param request
     * @param response
     * @return
     *
     * @author zax
     * @throws Exception 
     */
    @RequestMapping("login.htm")
    @LogDescription("用户登录操作")
    String login(HttpServletRequest request, HttpServletResponse response, ModelMap model, SystemCustomerPrincipal SystemCustomerPrincipal, BindingResult result) throws Exception{
        String url = "/login";
        String message = ConstantDef.SUCCESS_FAIL.SUCCESS;
        SystemCustomerPrincipalToken token = null;
        SystemCustomerPrincipal systemCustomerPrincipal = null;
        if(null != SystemCustomerPrincipal){
            String loginName = SystemCustomerPrincipal.getUser().getLoginName();
            String loginPwd = SystemCustomerPrincipal.getUser().getLoginPwd();
            User loginUser = userService.queryUserByLoginName(loginName);
            if(null != loginUser){
                systemCustomerPrincipal =new SystemCustomerPrincipal();
                systemCustomerPrincipal.setUser(loginUser);
                token = new SystemCustomerPrincipalToken(systemCustomerPrincipal, loginPwd, SystemCustomerPrincipal.isRememberMe());
                Subject currentUser = SecurityUtils.getSubject();
                try {
                    //尝试登录
                    currentUser.login(token); 
                    //验证是否登录成功  
                    if(currentUser.isAuthenticated()){  
                        logger.info("用户[" + loginName + "]登录认证通过 ...");
                    }else{  
                        token.clear(); 
                        logger.info("用户[" + loginName + "]登录认失败 ...");
                    }
                }catch(UnknownAccountException uae){  
                    logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
                    message = "未知账户";
                }catch(IncorrectCredentialsException ice){  
                    logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
                    message = "密码不正确";
                }catch(LockedAccountException lae){  
                    logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
                    message = "账户已锁定";
                }catch(ExcessiveAttemptsException eae){  
                    logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
                    message = "用户名或密码错误次数过多";
                }catch(AuthenticationException ae){  
                    //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
                    logger.error("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下" + ae.getStackTrace());
                    message = "用户名或密码不正确";
                }finally {
                    model.put("message", message);
                }
                if(ConstantDef.SUCCESS_FAIL.SUCCESS.equals(message)){
                    url = "forward:to_index.htm";
                }
            }
        }
        return url;
    }
    
    /**
     * 
     * 注册用户
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param user 用户对象
     * @param result 验证结果
     * @return String (SUCCESS、FAil)
     *
     * @author zax
     */
    @RequestMapping("register.htm")
    @ResponseBody
    String register(HttpServletRequest request, HttpServletResponse response, User user){
        return userService.registerUser(user);
    }
    
    /**
     * 
     * 默认加载方法
     *
     * @param request
     * @param response
     * @return
     *
     * @author zax
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response){
        String url = "login";
        logger.info("spring mvc default request");
        User user = (User) request.getAttribute("user");
        if(null != user){
            url = "forward:to_index.htm";
        }
        return url;
    }
    
    @RequestMapping("to_index.htm")
    public ModelAndView to_index(HttpServletRequest request, HttpServletResponse response, ModelMap model){
//        User user = SystemSessionManager.getContextInstance().getLoginInfo().getUser();
        Subject currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getPrincipal();
        model.put("name", user.getName());
        System.out.println(model.get("message"));
        return new ModelAndView("index", model);
    }
}
