package com.venkatesh.ai_crm_platform.Controller;


import com.venkatesh.ai_crm_platform.Service.ProductService;
import com.venkatesh.ai_crm_platform.models.Entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(
            @RequestBody Product product){

        return productService.create(product);
    }

    @GetMapping
    public List<Product> getAll(){

        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(
            @PathVariable Long id){

        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product){

        return productService.update(id,
                product);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id){

        productService.delete(id);

        return "Product Deleted";
    }
}