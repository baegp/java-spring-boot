package com.example.java5.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.java5.demo.domain.Category;
import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.ProductDTO;
import com.example.java5.demo.dto.SearchBox;
import com.example.java5.demo.service.CategoryService;
import com.example.java5.demo.service.ProductService;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String HomeProduct(Model model) {
        List<Products> products = productService.findAll();
        List<Category> category = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", category);

        return "index";
    }

    @GetMapping("listCategories/{categoryId}")
    public String getListCategories(Model model, @PathVariable("categoryId") int categoryId) {
        if (categoryId != 0) {
            List<ProductDTO> listCat = productService.getListCategory(categoryId);
            List<Category> category = categoryService.findAll();
            System.out.println("list catProducts " + listCat);
            System.err.println("id" + categoryId);
            model.addAttribute("categories", category);
            model.addAttribute("listCategories", listCat);
            return "listCategories";
        }
        return "";
    }

    @GetMapping("/sortbyprice")
    public String sortByPrice(Model model, ProductDTO dto, BindingResult result) {
        List<ProductDTO> products = productService.sortByPrice(dto.getPrice());
        List<Category> category = categoryService.findAll();

        model.addAttribute("products", products);
        System.err.println("sort " + products);
        model.addAttribute("categories", category);

        return "sort";
    }

    @GetMapping("/sortByPriceMinToMax")
    public String sortByPriceMinToMax(Model model, ProductDTO dto, BindingResult result) {
        List<ProductDTO> products = productService.sortByPriceMinToMax(dto.getPrice());
        List<Category> category = categoryService.findAll();
        model.addAttribute("products", products);
        System.err.println("sort " + products);
        model.addAttribute("categories", category);

        return "sort";
    }

    @GetMapping("/sortByNameDESC")
    public String sortByNameDESC(Model model, ProductDTO dto, BindingResult result) {
        List<ProductDTO> products = productService.sortByNameDESC(dto.getName());
        List<Category> category = categoryService.findAll();
        model.addAttribute("products", products);
        System.err.println("sort " + products);
        model.addAttribute("categories", category);

        return "sort";
    }

    @GetMapping("/sortByNameASC")
    public String sortByNameASC(Model model, ProductDTO dto, BindingResult result) {
        List<ProductDTO> products = productService.sortByNameASC(dto.getName());
        List<Category> category = categoryService.findAll();
        model.addAttribute("products", products);
        System.err.println("sort " + products);
        model.addAttribute("categories", category);

        return "sort";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("searchBox") SearchBox searchText) {

        System.err.println("Name search " + searchText.getKeyword());

        String search = "%" + searchText.getKeyword() + "%";

        if (searchText != null) {
            List<ProductDTO> listSearch = productService.searchByName(search);
            model.addAttribute("listSearch", listSearch);
            List<Category> category = categoryService.findAll();
            model.addAttribute("categories", category);

            System.err.println("list search " + listSearch);
            return "search";
        }
        return "index"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model,
            @PathVariable("id") int id) {
        if (id != 0) {
            Products detail = productService.getById(id);
            model.addAttribute("detail", detail);
            return "product/detail";
        }
        return "product/detail"; // Return tên của View, model sẽ tự động pass vào view
    }
}
