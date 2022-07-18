/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.java5.demo.controller;

import com.example.java5.demo.domain.Account;

import com.example.java5.demo.dto.AccountDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.service.FileService;
import com.example.java5.demo.utils.PasswordHelpers;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementEntry_return;

import java.io.IOException;
import java.util.List;
// import java.util.Optional;
import java.util.Optional;
import java.util.logging.Level;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bang Vu
 */
@Controller
@RequestMapping("")
public class AccountsController {
    // private Model model;
    // private @Valid Account dto;
    // private BindingResult result;

    @Autowired
    AccountService accountService;
    @Autowired
    AccountDTO account;

    @GetMapping("/user")
    public String home(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "user";
    }

    @GetMapping("user/create")
    public String create(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "user/create";
    }

    @PostMapping("user/create")
    public String createUser(Model model,
            @Valid @ModelAttribute("account") AccountDTO dto,
            BindingResult result, RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "/user/create";
        }
        String fileName = StringUtils.cleanPath(dto.getPhoto().getOriginalFilename());

        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setPhoto(fileName);
        accountService.save(account);
        try {
            FileService.saveFile("src/main/resources/static/upLoadImage", fileName, dto.getPhoto());
        } catch (IOException e) {
            // Logger.getLogger(AccountsController.class.getName()).log(Level.SEVERE, null,
            // ex);
            e.printStackTrace();
        }
        redirAttrsAttributes.addFlashAttribute("success", "them thanh cong");

        return "redirect:/user"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        if (username != null) {
            Account accountEdit = accountService.getById(username);
            model.addAttribute("account", accountEdit);
            return "/user/edit";
        }
        return "redirect:/user";
    }

    @PostMapping("user/edit")
    public String editUser(Model model,
            @Valid @ModelAttribute("account") AccountDTO dto,
            BindingResult result, RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "/user/create";
        }
        String fileName = StringUtils.cleanPath(dto.getPhoto().getOriginalFilename());

        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setUsername(dto.getUsername());
        account.setPhoto(fileName);
        accountService.save(account);
        try {
            FileService.saveFile("src/main/resources/static/upLoadImage", fileName, dto.getPhoto());
        } catch (IOException e) {
            // Logger.getLogger(AccountsController.class.getName()).log(Level.SEVERE, null,
            // ex);
            e.printStackTrace();
        }
        System.out.println("account" + account.getUsername());
        redirAttrsAttributes.addFlashAttribute("success", "sua thanh cong");

        return "redirect:/user"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("user/delete/{username}")
    public String delete(
            @PathVariable("username") String username, RedirectAttributes redirAttrsAttributes) {
        if (username != null) {
            Optional<Account> detail = accountService.findById(username);
            if (detail.isPresent()) {
                accountService.delete(detail.get());
                redirAttrsAttributes.addFlashAttribute("success", "delete thanh cong");
                return "redirect:/user";
            }
        }
        return "redirect:/user"; // Return tên của View, model sẽ tự động pass vào view
    }

}
