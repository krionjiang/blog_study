package com.jlq.web.admin;

import com.jlq.pojo.TbUser;
import com.jlq.service.UserService;
import com.jlq.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author ：jlq
 * @date ：Created in 2020/11/24 10:59
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String admin() {
        return "admin/login";
    }

    @GetMapping("/editPassword")
    public String password(){
        return "admin/changePassword";
    }
    
    @PostMapping("/login")
    public String login(TbUser user, HttpSession session,
                        RedirectAttributes attributes) {
        TbUser checkUser = userService.checkUser(user);
        if (checkUser != null) {
            session.setAttribute("user", checkUser);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(UserVo userVo, RedirectAttributes attributes){
        if (StringUtils.equals(userVo.getPassword(),userVo.getNewPassword())) {
            attributes.addFlashAttribute("message","旧密码与新密码不能相同哦！！");
            return "redirect:editPassword";
        }
        TbUser user = userService.changeUser(userVo);
        if (user != null) {
            return "redirect:/admin";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:editPassword";
        }
    }


    @GetMapping("blogs")
    public String blogs() {
        return "admin/blogs";
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
    
}


