package com.example.java5.demo.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.dto.AccountLoginDTO;
import com.example.java5.demo.service.AccountService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("")
public class LoginController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;

    // @GetMapping("/login")
    // public String login(Model model) {
    // AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
    // model.addAttribute("account", accountLoginDTO);
    // return "login";
    // }

    // @GetMapping("/logout")
    // public String logout(Model model) {
    // AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
    // if (session.getAttribute("username") != null) {
    // session.removeAttribute("username");
    // session.removeAttribute("role");
    // return "redirect:/login";
    // }
    // return "redirect:/login";
    // }

    // @PostMapping("login")
    // public String checkLogin(Model model, @Valid @ModelAttribute("account")
    // AccountLoginDTO dto, BindingResult result,
    // RedirectAttributes redirAttrsAttributes) {
    // Account check = accountService.checkLogin(dto.getUsername(),
    // dto.getPassword());
    // if (check != null) {
    // String role;

    // if (check.isAdmin()) {
    // role = "admin";
    // } else {
    // role = "user";
    // }
    // session.setAttribute("username", check.getUsername());
    // session.setAttribute("role", role);
    // return "dashboard";
    // }
    // redirAttrsAttributes.addFlashAttribute("error", "dang d thanh cong");
    // return "login";
    // }

}
