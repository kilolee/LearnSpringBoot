package com.kilo.controller;

import com.kilo.entity.UserEntity;
import com.kilo.param.UserParam;
import com.kilo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by kilo on 2018/5/19.
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list")
//    @Cacheable(value = "user_list")
    public String list(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "6") Integer size, Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserEntity> users = userRepository.findAll(pageable);
        model.addAttribute("users", users);
        logger.info("user list " + users.getContent());
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserParam userParam, BindingResult result, Model model) {

        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "_" + error.getDefaultMessage() + ";";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/userAdd";
        }
        UserEntity u = userRepository.findUserByUserNameOrEmail(userParam.getUserName(), userParam.getEmail());
        if (u != null) {
            model.addAttribute("errorMsg", "用户已存在！");
            return "user/userAdd";
        }
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        user.setUserType("user");
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        UserEntity user = userRepository.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result, ModelMap modelMap) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getCode() + "_" + error.getDefaultMessage() + ";";
            }
            modelMap.addAttribute("errorMsg", errorMsg);
            modelMap.addAttribute("user", userParam);
            return "user/userEdit";
        }
        UserEntity user = userRepository.findUserById(userParam.getId());
        BeanUtils.copyProperties(userParam, user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        userRepository.deleteById(id);
        return "redirect:/list";
    }
}
