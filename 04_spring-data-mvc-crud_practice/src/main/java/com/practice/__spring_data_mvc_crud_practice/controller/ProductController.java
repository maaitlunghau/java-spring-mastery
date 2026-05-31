package com.practice.__spring_data_mvc_crud_practice.controller;

import com.practice.__spring_data_mvc_crud_practice.service.ProductService;
import com.practice.__spring_data_mvc_crud_practice.model.Product;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService _productService;

    public ProductController(ProductService productService) {
        this._productService = productService;
    }

    @GetMapping("/products")
    public String index(Model model) {
        List<Product> products = _productService.fetchAllProducts();
        model.addAttribute("products", products);

        return "products/index";
    }
    // 2. Trang giao diện tạo mới
    @GetMapping("/product/create")
    public String createPage(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") Product pro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/create";
        }

        this._productService.createProduct(pro);
        return "redirect:/products";
    }

    @GetMapping("/product/update/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        Optional<Product> updatePro = this._productService.findProductById(id);
        if (updatePro.isEmpty()) {
            return "redirect:/products";
        }

        model.addAttribute("product", updatePro.get());
        return "products/update";
    }

    @PostMapping("/product/update/{id}")
    public String updateProduct(
            @PathVariable int id,
            @Valid @ModelAttribute("product") Product pro,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "products/update";
        }

        this._productService.updateProduct(pro);
        return "redirect:/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        this._productService.deleteProduct(id);
        return "redirect:/products";
    }
}