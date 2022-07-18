package com.example.java5.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.domain.Category;
import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.CartDTO;
import com.example.java5.demo.dto.ItemDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.service.CategoryService;
import com.example.java5.demo.service.ProductService;

@Controller
@RequestMapping("")
public class CartController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private HttpSession session;
    @Autowired
    private AccountService accountService;

    @GetMapping("/cart")
    public String cart(Model model) {
        // List<Products> products = productService.findAll();
        // List<Category> category = categoryService.findAll();
        // model.addAttribute("products", products);
        // model.addAttribute("categories", category);
        // Account check = accountService.checkLogin(dto.getUsername(),
        // dto.getPassword());

        // session.setAttribute("username", check.getUsername());

        return "cart/list";
    }

    @GetMapping("cart/add/{id}")
    public String addCart(Model model, @PathVariable("id") int id,
            RedirectAttributes redirAttrsAttributes) {
        if (id != 0) {
            CartDTO carts = (CartDTO) httpSession.getAttribute("cart");
            Optional<Products> detail = productService.findById(id);
            ItemDTO item = new ItemDTO();
            if (detail != null) {
                item.setMaSp(detail.get().getId());
                item.setTitle(detail.get().getName());
                item.setSoLuong(1);
                item.setPrice(detail.get().getPrice());
                item.setImage(detail.get().getImage());
                if (carts != null) {
                    carts.add(item);
                    redirAttrsAttributes.addFlashAttribute("success", "đã cập nhật số lượng sản phẩm");
                } else {
                    carts = new CartDTO();
                    carts.add(item);
                    redirAttrsAttributes.addFlashAttribute("success", "đã thêm vào giỏ hàng");
                }
                httpSession.setAttribute("cart", carts);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("cart/remove/{id}")
    public String removeCart(Model model, @PathVariable("id") int id,
            RedirectAttributes redirAttrsAttributes) {
        if (id != 0) {
            CartDTO carts = (CartDTO) httpSession.getAttribute("cart");
            Optional<Products> detail = productService.findById(id);
            ItemDTO item = new ItemDTO();
            if (detail != null) {
                item.setMaSp(detail.get().getId());
                item.setTitle(detail.get().getName());
                item.setSoLuong(1);
                item.setPrice(detail.get().getPrice());
                item.setImage(detail.get().getImage());
                if (carts != null) {
                    carts.remove(item);
                }
                httpSession.setAttribute("cart", carts);
                redirAttrsAttributes.addFlashAttribute("success", "đã xóa sản phẩm khỏi giỏ hàng");

            }
        }
        return "redirect:/cart";
    }

    @GetMapping("cart/dele/{id}")
    public String delete(Model model, @PathVariable("id") int id,
            RedirectAttributes redirAttrsAttributes) {
        if (id != 0) {
            CartDTO carts = (CartDTO) httpSession.getAttribute("cart");
            Optional<Products> detail = productService.findById(id);
            ItemDTO item = new ItemDTO();
            if (detail != null) {
                item.setMaSp(detail.get().getId());
                item.setTitle(detail.get().getName());
                item.setSoLuong(1);
                item.setPrice(detail.get().getPrice());
                item.setImage(detail.get().getImage());
                if (carts != null) {
                    carts.dete(item);
                }
                httpSession.setAttribute("cart", carts);
                redirAttrsAttributes.addFlashAttribute("success", "đã giảm số lượng sản phẩm");
            }
        }
        return "redirect:/cart";
    }

}
