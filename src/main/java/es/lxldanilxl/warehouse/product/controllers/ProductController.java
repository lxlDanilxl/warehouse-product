package es.lxldanilxl.warehouse.product.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.lxldanilxl.warehouse.product.model.dtos.ProductRequest;
import es.lxldanilxl.warehouse.product.services.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(ProductRequest productRequest) {
        productService.addProduct(productRequest);
    }

}
