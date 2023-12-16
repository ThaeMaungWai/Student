package base.controllers;

import base.daos.UserDao;
import base.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/userReg")
    public String getUserRegForm(Model model){
        User user = new User();
        user.setId(userDao.getLatestUserId());
        model.addAttribute("user",user);
        return "user/user_reg";
    }
    @PostMapping("/userReg")
    public String registerUser(@ModelAttribute("user")User user){
        int i = userDao.createUser(user);
        return "redirect:/userView";
    }

    @GetMapping("/userView")
    public String userDisplay(Model model){
        model.addAttribute("users",userDao.getAllUsers());
        model.addAttribute("user",new User());
        return "user/user_view";
    }

    @GetMapping("/userDetail")
    public String getUserProfileForm(@RequestParam("id")String id , Model model){
        model.addAttribute("user",userDao.findUserById(id));
        return "user/user_detail";
    }
    @PostMapping("/userDetail")
    public String userProfileUpdate(@ModelAttribute("user")User user , Model model){
        int result = userDao.updateUser(user);
        return "redirect:/userView";
    }

    @GetMapping("/userDelete")
    public String userDelete(@RequestParam("id")String id ){
        int result =  userDao.deleteUser(id);
        return "redirect:/userView";
    }

    @PostMapping("/userSearch")
    public String searchUser(@ModelAttribute("user")User user,Model model){
        if(!user.getId().isEmpty()){
            List<User>users=userDao.searchUserById(user.getId());
            model.addAttribute("users",users);
            return "user/user_view";
        }else if(!user.getName().isEmpty()){
            List<User>userList=userDao.searchUserByName(user.getName());
            model.addAttribute("users",userList);
            return "user/user_view";
        }else{
            return "redirect:/userView";
        }
    }




}
