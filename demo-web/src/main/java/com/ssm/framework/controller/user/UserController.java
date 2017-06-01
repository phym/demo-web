package com.ssm.framework.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.framework.model.user.User;
import com.ssm.framework.model.user.User1;
import com.ssm.framework.model.user.UserExt;
import com.ssm.framework.service.user.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /**
     * 用户Service
     */
    @Autowired
    private UserService userService;
    
    @RequestMapping("/getLoginInfo")
    public ModelAndView getLoginInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        User1 user = new User1();
        UserExt userExt = new UserExt();
        user.setAge(10);
        user.setName("userName");
        user.setSex("F");
        userExt.setName("userNameExt");
        user.setUserExt(userExt);
//        model.put("user", SystemSessionManager.getContextInstance().getLoginInfo().getAccout());
        model.put("ip", "192.168.1.1");
        model.put("u", user);
        return new ModelAndView("index", model);
    }
    
    /**
     * 
     * 用户注册
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param user 注册用户对象
     * @return String (FAIL、SUCCESS)
     *
     * @author zax
     */
    @RequestMapping("register.htm")
    public String registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
        logger.info("register user controller begin");
        return userService.registerUser(user);
    }
    
}
