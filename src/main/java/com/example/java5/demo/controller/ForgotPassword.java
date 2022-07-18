package com.example.java5.demo.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.dto.AccountDTO;
import com.example.java5.demo.dto.ForgotPasswordDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.utils.RandomString;
import com.example.java5.demo.utils.SendMailFgpw;

@Controller
@RequestMapping("")
public class ForgotPassword {
    @Autowired
    private AccountService accountService;

    @GetMapping("/forgotpassword")
    public String HomeCategory(Model model) {
        ForgotPasswordDTO forgotPassword = new ForgotPasswordDTO();
        model.addAttribute("account", forgotPassword);

        return "forgotpassword";
    }

    @PostMapping("forgotpassword")
    public String sendPassword(Model model, @Valid @ModelAttribute("accounts") ForgotPasswordDTO dto,
            BindingResult result, RedirectAttributes redirectAttributes) {
        SendMailFgpw sendMailFgpw = new SendMailFgpw();
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.out.println("CO EROR");
            return "forgotpassword";
        }
        RandomString randomString = new RandomString();

        if (accountService.isExistAccountForget(dto.getUsername(), dto.getEmail())) {

            String passwordRandom = randomString.generateRandomString();

            AccountDTO accountDTO = new AccountDTO();
            Account account = new Account();
            // String updatePass = account.setPassword(passwordRandom);

            // boolean update = accountService.up(passwordRandom, dto.getUsername());

            // if (dto.getUsername() != null && passwordRandom != null) {
            accountService.updateForget(passwordRandom, dto.getUsername());
            // }
            // String passwordForgot = accountService.getPasswordForgot(dto.getUsername(),
            // dto.getEmail());

            System.err.println("password send " + passwordRandom);
            System.err.println("username and email send " + dto.getUsername() + " " + dto.getEmail());
            // System.err.println("update pass " + update);
            try {
                sendMailFgpw.sendAsHtml(dto.getEmail(),
                        "Send Password forgot : " + dto.getUsername(),
                        " <p style=\"color: black;font-size: 13.9px;\"> This is your password please don't share anyone:  </p>"
                                + "\n" + "<h3> " + passwordRandom + " </h3>"
                                + "    <footer style=\"margin-top: 40px;background-color: rgb(34, 34, 34);color: white;padding: 5px;text-align: center;\n"
                                + "    font-size: 14px;font-weight: 10;width: 1000px;\n"
                                + "    \"\n"
                                + "    >Copyright © 2022 All rights reserved | This web is made with  by than dong GP</footer>\n");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("error", "Send password success");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Username or email is not exist");
            return "redirect:/forgotpassword";
        }

    }
}
