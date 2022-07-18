package com.example.java5.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.java5.demo.domain.Account;
import com.example.java5.demo.domain.OrderDetailKey;
import com.example.java5.demo.domain.Orders;
import com.example.java5.demo.domain.OrdersDetail;
import com.example.java5.demo.domain.Products;
import com.example.java5.demo.dto.CartDTO;
import com.example.java5.demo.dto.ItemDTO;
import com.example.java5.demo.dto.OrderDTO;
import com.example.java5.demo.dto.OrderDetailDTO;
import com.example.java5.demo.service.AccountService;
import com.example.java5.demo.service.OrderDetailService;
import com.example.java5.demo.service.OrderService;
import com.example.java5.demo.utils.SendMailFgpw;

@Controller
@RequestMapping("")
public class CheckoutController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    AccountService accountService;

    @GetMapping("/checkout")
    public String cart(Model model) {
        OrderDTO orderDTO = new OrderDTO();
        model.addAttribute("order", orderDTO);
        return "cart/checkout";
    }

    @PostMapping("checkout")
    public String createCheckout(@Valid @ModelAttribute("order") OrderDTO dto, BindingResult result,
            RedirectAttributes redirectAttributes) {
        SendMailFgpw sendMailFgpw = new SendMailFgpw();

        if (result.hasErrors()) {
            System.err.println("loi");
            return "cart/checkout";
        }

        Orders order = new Orders();
        BeanUtils.copyProperties(dto, order);
        String username = (String) httpSession.getAttribute("username");
        Account account = accountService.getById(username);
        order.setAccount(account);
        CartDTO cart = (CartDTO) httpSession.getAttribute("cart");
        order.setTotal(cart.getTongTien());
        Orders orderCreated = orderService.save(order);
        for (ItemDTO item : cart.getCarts()) {
            OrdersDetail ordersDetail = new OrdersDetail();
            OrderDetailKey key = new OrderDetailKey(order.getId(), item.getMaSp());
            ordersDetail.setId(key);
            ordersDetail.setOrders(order);

            Products product = new Products();
            product.setId(item.getMaSp());
            ordersDetail.setProducts(product);
            ordersDetail.setPrice(item.getPrice());
            ordersDetail.setQuantity(item.getSoLuong());
            ordersDetail.setTotal(item.getPrice() * item.getSoLuong());
            ordersDetail.setImage(item.getImage());
            orderDetailService.save(ordersDetail);
        }

        float total = order.getTotal();
        String diachi = order.getAddress();
        String sdt = order.getPhone();
        try {
            sendMailFgpw.sendAsHtml(dto.getEmail(),
                    "Chúc mừng " + dto.getUsername() + " đã đặt hàng thành công",
                    " <p>Tổng tiền thanh toán: <span style=\"color: red\"> " + total + "</span><span>VNĐ</span> </p> "
                            + "<p>Địa chỉ: " + diachi + "</p>"
                            + "<p>SĐT: " + sdt + "</p>"
                            + " <p> Vui lòng kiểm tra lại thông tin đặt hàng </p> ");
            System.err.println("total send " + total);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "cart/checkoutSuccess";
    }

    @GetMapping("/order")
    public String order(Model model) {
        List<Orders> order = orderService.findAll();
        model.addAttribute("order", order);
        return "order/listOrder";
    }

    @GetMapping("order/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirAttrsAttributes) {
        if (id != 0) {
            System.out.println("delete " + id);
            Optional<Orders> detail = orderService.findById(id);
            if (detail.isPresent()) {
                orderService.delete(detail.get());
                redirAttrsAttributes.addFlashAttribute("success", "delete thanh cong");
                return "redirect:/order";
            }
        }
        return "redirect:/order"; // Return tên của View, model sẽ tự động pass vào view
    }

    @GetMapping("listOrderDetail/{orderId}")
    public String getListCategories(Model model, @PathVariable("orderId") int orderId) {
        if (orderId != 0) {
            List<OrderDetailDTO> listCat = orderDetailService.getListOrderDetail(orderId);
            // List<Category> category = categoryService.findAll();
            System.out.println("list orderdetail " + listCat);
            // System.err.println("id" + categoryId);
            // model.addAttribute("categories", category);
            model.addAttribute("listOrderDetail", listCat);
            return "order/listDetail";
        }
        return "";
    }

    // @GetMapping("orderDetail/delete/{orderId}")
    // public String deleteDetail(@PathVariable("orderId") int orderId,
    // RedirectAttributes redirAttrsAttributes) {
    // OrderDetailKey orderDetailKey = new OrderDetailKey();
    // if (orderId != 0) {
    // System.out.println("delete " + orderId);
    // Optional<OrdersDetail> detail = orderDetailService.findById(orderId);
    // if (detail.isPresent()) {
    // orderDetailService.delete(detail.get());
    // redirAttrsAttributes.addFlashAttribute("success", "delete thanh cong");
    // return "order/listDetail";
    // }
    // }
    // return "order/listDetail"; // Return tên của View, model sẽ tự động pass
    // vàoview
    // }

    @GetMapping("orderDetail/delete/{orderId}/{productId}")
    public String editOrdersDetail(Model model, @PathVariable("orderId") int orderId, RedirectAttributes redirAttrs,
            @PathVariable("productId") int producId) {
        if (orderId != 0 && producId != 0) {
            // Order order = new Order();
            OrderDetailKey key = new OrderDetailKey(orderId, producId);
            Optional<OrdersDetail> prd = orderDetailService.findById(key);
            if (prd.isPresent()) {
                orderDetailService.delete(prd.get());
                redirAttrs.addFlashAttribute("success", "Xóa thành công");
                return "redirect:/order";
            }
        }
        return "redirect:/order";
    }

    @GetMapping("/checkoutSuccess")
    public String checkOutSuccess(Model model) {
        // OrderDTO orderDTO = new OrderDTO();
        // model.addAttribute("order", orderDTO);
        return "cart/checkoutSuccess";
    }
}
