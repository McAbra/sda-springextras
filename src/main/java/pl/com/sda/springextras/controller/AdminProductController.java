package pl.com.sda.springextras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.sda.springextras.products.ProductDto;
import pl.com.sda.springextras.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchAllProducts() {
        List<ProductDto> productDtos = productService.fetchAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.addProduct(productDto));
    }


}
