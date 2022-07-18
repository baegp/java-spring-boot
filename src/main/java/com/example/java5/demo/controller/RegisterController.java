package com.example.java5.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
import com.example.java5.demo.dto.AccountDTO;
import com.example.java5.demo.dto.RegisterDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.utils.PasswordHelpers;
import com.example.java5.demo.utils.sendMail;

@Controller
@RequestMapping("")
public class RegisterController {
    @Autowired
    private AccountService accountService;

    @Autowired
    RegisterDTO registerDTO;

    @GetMapping("/register")
    public String login(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute("account", registerDTO);

        return "register";
    }

    @PostMapping("register")
    public String createUser(Model model,
            @Valid @ModelAttribute("account") RegisterDTO dto,
            BindingResult result, RedirectAttributes redirAttrsAttributes) {
        if (result.hasErrors()) {
            return "/register";
        }
        String email = dto.getEmail();
        String username = dto.getUsername();
        try {
            sendMail.sendEmail(email, username);
            System.out.println("sendmail " + email + username);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

        Account account = new Account();
        account.setAdmin(false);
        account.setActivated(false);
        account.setPhoto("https://shop.mixigaming.com/wp-content/uploads/2021/01/Ao-ba-lo-Mixi-BVCBG-300x400.jpg");
        BeanUtils.copyProperties(dto, account);
        accountService.save(account);
        redirAttrsAttributes.addFlashAttribute("error", "dang ky tai khoan thanh cong");

        return "redirect:/login"; // Return tên của View, model sẽ tự động pass vào view
    }
}
