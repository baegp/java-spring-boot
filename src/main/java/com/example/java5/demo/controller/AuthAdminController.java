package com.example.java5.demo.controller;

import java.util.List;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.domain.Category;
import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.AccountLoginDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.service.CategoryService;
import com.example.java5.demo.service.MailService;
import com.example.java5.demo.service.ProductService;
import com.example.java5.demo.utils.sendMail;

@Controller
@RequestMapping("")
public class AuthAdminController {
    @Autowired
    private AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    private HttpSession session;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    public String login(Model model) {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        model.addAttribute("account", accountLoginDTO);

        return "login";
    }

    // public boolean sendMail(String to, String subject, String content) {
    // try {
    // MimeMessage message = javaMailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message);

    // helper.setSubject(subject);
    // helper.setFrom("tranhrp@gmail.com");
    // helper.setTo(to);
    // } catch (Exception e) {
    // e.printStackTrace();
    // return false;
    // }
    // return true;
    // }

    @GetMapping("/logout")
    public String logout(Model model) {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        if (session.getAttribute("username") != null) {
            session.removeAttribute("username");
            session.removeAttribute("role");
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @PostMapping("login")
    public String checkLogin(Model model, @Valid @ModelAttribute("account") AccountLoginDTO dto, BindingResult result,
            RedirectAttributes redirAttrsAttributes) {
        Account check = accountService.checkLogin(dto.getUsername(), dto.getPassword());
        // try {
        // if (check.getUsername().equalsIgnoreCase("admin")) {
        // redirAttrsAttributes.addFlashAttribute("error", "username or password not
        // correct");
        // return "login";
        // }
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        if (check != null) {
            String role;

            if (check.isAdmin()) {
                role = "admin";
            } else {
                role = "user";
            }

            if (role.equals("admin")) {
                session.setAttribute("username", check.getUsername());

                return "dashboard";
            }
            if (role.equals("user")) {
                List<Products> products = productService.findAll();
                List<Category> category = categoryService.findAll();

                session.setAttribute("username", check.getUsername());

                model.addAttribute("products", products);
                model.addAttribute("categories", category);

                return "index";
            }

            session.setAttribute("username", check.getUsername());
            System.out.println("username sess " + check.getUsername());
            session.setAttribute("role", role);
            return "dashboard";
        } else {
            redirAttrsAttributes.addFlashAttribute("error", "username or password not correct");
            return "redirect:/login";
        }

    }
}
