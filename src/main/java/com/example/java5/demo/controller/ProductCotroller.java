package com.example.java5.demo.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import javax.validation.Valid;

import com.example.java5.demo.domain.Category;
import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.ProductDTO;
import com.example.java5.demo.service.CategoryService;
import com.example.java5.demo.service.FileService;
import com.example.java5.demo.service.ProductService;

import aj.org.objectweb.asm.Attribute;
import org.springframework.util.StringUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class ProductCotroller {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/product")
    public String HomeProduct(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("product/create")
    public String create(Model model) {
        Products product = new Products();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", categoryService.findAll());
        return "product/create";
    }

    @GetMapping("product/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        if (id != 0) {
            Optional<Products> detail = productService.findById(id);
            if (detail.isPresent()) {
                model.addAttribute("listCategories", categoryService.findAll());
                System.err.println(categoryService.findAll());
                model.addAttribute("products", detail.get());
                return "/product/edit";
            }
        }
        return "redirect:/product";
    }

    @PostMapping("product/create")
    public String createProduct(Model model, @Valid @ModelAttribute("product") ProductDTO dto, BindingResult result,
            RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            model.addAttribute("listCategories", categoryService.findAll());
            Category category = new Category();
            // List<FieldError> errors = result.getFieldErrors();
            // for (FieldError error : errors) {
            // System.out.println(error.getObjectName() + " - " +
            // error.getDefaultMessage());
            // }
            System.out.println("id category " + dto.getCategoryId());
            System.out.println("image " + dto.getImage());
            System.out.println("name " + dto.getName());

            System.out.println("date " + dto.getCreateDate());
            System.out.println("price " + dto.getPrice());

            System.err.println("có lỗi them pro");
            return "/product/create";
        }

        // String fileName =
        // StringUtils.cleanPath(dto.getImage().getOriginalFilename());

        Category category = new Category();
        Products products = new Products();

        category.setCategoryId(dto.getCategoryId());
        products.setCategoryId(category);
        // products.setImage(fileName);
        System.err.println("id category" + dto.getCategoryId());
        BeanUtils.copyProperties(dto, products);
        productService.save(products);
        System.out.println("product new " + products);
        // try {
        // FileService.saveFile("src/main/resources/static/upLoadImage", fileName,
        // dto.getImage());
        // } catch (IOException e) {
        // // Logger.getLogger(AccountsController.class.getName()).log(Level.SEVERE,
        // null,
        // // ex);
        // e.printStackTrace();
        // }
        redirAttrsAttributes.addFlashAttribute("success", "Add succsess");
        return "redirect:/product"; // Return tên của View, model sẽ tự động pass vào view
    }

    @PostMapping("product/edit")
    public String editProduct(Model model, @Valid @ModelAttribute("products") ProductDTO dto, BindingResult result,
            RedirectAttributes redirAttrsAttributes) {
        // kiểm tra lỗi
        if (result.hasErrors()) {
            // đẩy lại view và đưa ra thông báo lỗi
            model.addAttribute("listCategories", categoryService.findAll());
            System.err.println("có lỗi");
            return "/product/edit";
        }
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        System.err.println("id category" + dto.getCategoryId());

        Products products = new Products();

        products.setCategoryId(category);
        BeanUtils.copyProperties(dto, products);
        productService.save(products);
        System.out.println("product new " + products);
        redirAttrsAttributes.addFlashAttribute("success", "edit succsess");
        return "redirect:/product"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("product/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirAttrsAttributes) {
        if (id != 0) {
            System.out.println("delete " + id);
            Optional<Products> detail = productService.findById(id);
            if (detail.isPresent()) {
                productService.delete(detail.get());
                redirAttrsAttributes.addFlashAttribute("success", "delete thanh cong");
                return "redirect:/product";
            }
        }
        return "redirect:/product"; // Return tên của View, model sẽ tự động pass vào view
    }

}
