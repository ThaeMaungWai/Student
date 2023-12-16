package base.controllers;

import base.daos.UserDao;
import base.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user" , new User());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    private final UserDao userDao; // Assuming UserDao is injected into the controller
    public PageController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
                        HttpSession session, RedirectAttributes redirect) {
        User user = userDao.getUserByEmailAndPassword(email, password);
        if (user != null) {
            // User is authenticated, proceed to the view page
            session.setAttribute("name",user.getName());
            model.addAttribute("userName", user.getName()); // Add user name to the model
            return "menu"; // Go to "menu" with your actual view page name
        } else {
            // User authentication failed, handle accordingly
            redirect.addFlashAttribute("message","Invalid credentials. Please try again.");
            return "redirect:/"; // Redirect back to the login page or show an error message
        }
    }
}
