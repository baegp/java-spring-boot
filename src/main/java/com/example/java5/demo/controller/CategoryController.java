package com.example.java5.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.java5.demo.domain.Category;
import com.example.java5.demo.dto.CategoryDTO;
import com.example.java5.demo.service.CategoryService;

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

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryDTO category;

    @GetMapping("/category")
    public String HomeCategory(Model model) {
        List<Category> category = categoryService.findAll();
        model.addAttribute("categories", category);
        return "category";
    }

    // create
    @GetMapping("category/create")
    public String create(Model model) {
        CategoryDTO product = new CategoryDTO();
        model.addAttribute("category", product);
        return "category/create";
    }

    @PostMapping("category/create")
    public String createCategory(Model model,
            @Valid @ModelAttribute("category") CategoryDTO dto,
            BindingResult result, RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.err.println("có lỗi");
            return "/category/create";
        }
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        categoryService.save(category);
        redirAttrsAttributes.addFlashAttribute("success", "them thanh cong");
        return "redirect:/category"; // Return tên của View, model sẽ tự động pass vào view
    }

    // edit
    @GetMapping("category/edit/{categoryId}")
    public String edit(Model model, @PathVariable("categoryId") int categoryId) {
        if (categoryId != 0) {
            Optional<Category> categoryEdit = categoryService.findById(categoryId);
            model.addAttribute("category", categoryEdit);
            return "/category/edit";
        }
        return "redirect:/category";
    }

    @PostMapping("category/edit")
    public String editProduct(Model model,
            @Valid @ModelAttribute("category") CategoryDTO dto,
            BindingResult result, RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            System.err.println("có lỗi");
            return "/category/edit";
        }
        Category category = new Category();
        BeanUtils.copyProperties(dto, category);
        category.setCategoryId(dto.getCategoryId());
        categoryService.save(category);
        redirAttrsAttributes.addFlashAttribute("success", "sua thanh cong");
        return "redirect:/category"; // Return tên của View, model sẽ tự động pass vào view
    }

    // remove
    @GetMapping("category/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") int categoryId, RedirectAttributes redirAttrsAttributes) {
        if (categoryId != 0) {
            Optional<Category> detail = categoryService.findById(categoryId);
            if (detail.isPresent()) {
                categoryService.delete(detail.get());
                redirAttrsAttributes.addFlashAttribute("success", "delete thanh cong");
                return "redirect:/category";
            }
        }
        return "redirect:/category"; // Return tên của View, model sẽ tự động pass vào view
    }
}
