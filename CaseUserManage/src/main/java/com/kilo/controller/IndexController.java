package com.kilo.controller;

import com.kilo.config.WebConfiguration;
import com.kilo.entity.UserEntity;
import com.kilo.param.LoginParam;
import com.kilo.param.RegisterParam;
import com.kilo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by kilo on 2018/5/19.
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute(WebConfiguration.LOGIN_KEY);
        if (StringUtils.isBlank(id)) {
            return "login";
        } else {
            return "redirect:/list";
        }
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@Valid LoginParam loginParam, BindingResult result, ModelMap modelMap, HttpServletRequest request) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "_" + error.getDefaultMessage() + ";";
            }
            modelMap.addAttribute("errorMsg", errorMsg);
            return "login";
        }
        UserEntity user = userRepository.findByUserName(loginParam.getLoginName());
        if (user == null) {
            user = userRepository.findByEmail(loginParam.getLoginName());
        }
        if (user == null) {
            modelMap.addAttribute("errorMsg", "用户名不存在！");
            return "login";
        } else if (!user.getPassword().equals(loginParam.getPassword())) {
            modelMap.addAttribute("errorMsg", "密码错误！");
            return "login";
        }
        request.getSession().setAttribute(WebConfiguration.LOGIN_KEY, user.getId());
        request.getSession().setAttribute(WebConfiguration.LOGIN_USER, user);
        return "redirect:/list";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(WebConfiguration.LOGIN_KEY);
        request.getSession().removeAttribute(WebConfiguration.LOGIN_USER);
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(@Valid RegisterParam registerParam, BindingResult result, ModelMap modelMap) {
        logger.info("register param " + registerParam.toString());
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "_" + error.getDefaultMessage() + ";";
            }
            modelMap.addAttribute("errorMsg", errorMsg);
            return "register";
        }
        //邮箱可以共用
        UserEntity u = userRepository.findByUserName(registerParam.getUserName());
        //一个邮箱只能给一个用户用来注册
//        UserEntity u = userRepository.findUserByUserNameOrEmail(registerParam.getUserName(), registerParam.getEmail());
        if (u != null) {
            modelMap.addAttribute("errorMsg", "用户名已存在!");
            return "register";
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(registerParam, user);
        user.setRegTime(new Date());
        user.setUserType("manage");
        user.setState("unverified");
        userRepository.save(user);
        sendRegisterMail(user);
        logger.info("register user " + user.toString());
        return "login";
    }


    public void sendRegisterMail(UserEntity user) {
        Context context = new Context();
        context.setVariable("id", user.getId());
        String emailContent = templateEngine.process("emailTemplate", context);
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(user.getEmail());
            helper.setSubject("注册验证邮件");
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送注册邮件时异常！", e);
        }
    }

    @RequestMapping("/verified/{id}")
    public String verified(@PathVariable("id") String id, ModelMap modelMap) {
        UserEntity user = userRepository.findUserById(id);
        if (user != null && "unverified".equals(user.getState())) {
            user.setState("verified");
            userRepository.save(user);
            modelMap.addAttribute("userName", user.getUserName());
        }
        return "verified";
    }

}
