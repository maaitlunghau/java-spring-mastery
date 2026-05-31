package com.practice.__spring_data_mvc_crud_practice.controller;

import com.practice.__spring_data_mvc_crud_practice.service.ProductService;
import com.practice.__spring_data_mvc_crud_practice.model.Product;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService _productService;

    /**
     * Create a ProductController wired with the provided ProductService.
     *
     * @param productService the service used to perform product operations (fetch, create, update, delete)
     */
    public ProductController(ProductService productService) {
        this._productService = productService;
    }

    /**
     * Display a paginated list of products with optional keyword filtering.
     *
     * Adds the following attributes to the supplied model:
     * "products" (current page content), "keyword" (search term), "currentPage",
     * "totalPages", "totalItems", and "size".
     *
     * @param keyword optional search keyword to filter products
     * @param page    zero-based page index to display
     * @param size    number of items per page
     * @param model   MVC model to populate for the view
     * @return        the view name for the product listing ("products/index")
     */
    @GetMapping("/products")
    public String index(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size,
            Model model) {

        Page<Product> productPage = (Page<Product>) _productService.fetchProductsWithSpec(keyword, page, size);

        model.addAttribute("products", productPage.getContent());

        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("size", size);

        return "products/index";
    }

    /**
     * Show the product creation form.
     *
     * @param model the MVC model; receives a new Product instance under the attribute name "product"
     * @return the view name for the product creation page ("products/create")
     */
    @GetMapping("/product/create")
    public String createPage(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    /**
     * Handle submission of the product creation form and persist a valid product.
     *
     * @param pro            the product populated from the form to be created
     * @param bindingResult  validation results for the submitted product
     * @return               the view name `products/create` when validation fails, otherwise redirects to `/products`
     */
    @PostMapping("/product/create")
    public String createProduct(@Valid @ModelAttribute("product") Product pro, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/create";
        }

        this._productService.createProduct(pro);
        return "redirect:/products";
    }

    /**
     * Displays the product edit form for the product identified by the given ID.
     *
     * If a product with the specified ID exists, the product is added to the model under the
     * attribute name "product" and the "products/update" view is returned; otherwise the
     * method redirects to the product list.
     *
     * @param id    the ID of the product to edit (from the path variable)
     * @param model the MVC model to populate; on success a "product" attribute is added
     * @return      the view name to render or a redirect to the product list
     */
    @GetMapping("/product/update/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        Optional<Product> updatePro = this._productService.findProductById(id);
        if (updatePro.isEmpty()) {
            return "redirect:/products";
        }

        model.addAttribute("product", updatePro.get());
        return "products/update";
    }

    /**
     * Process the submitted product update form and persist changes when validation passes.
     *
     * @param id            the path variable identifying the product to update
     * @param pro           the Product populated from the submitted form
     * @param bindingResult the validation results for `pro`
     * @return               `"products/update"` if validation fails, otherwise `"redirect:/products"`
     */
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

    /**
     * Delete the product identified by the given ID and redirect to the products listing.
     *
     * @param id the identifier of the product to delete
     * @return the redirect view name to the products listing ("redirect:/products")
     */
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        this._productService.deleteProduct(id);
        return "redirect:/products";
    }
}