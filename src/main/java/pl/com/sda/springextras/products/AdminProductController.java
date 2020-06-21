package pl.com.sda.springextras.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> fetchAllProducts(@RequestParam(required = false) String query,
                                                             @RequestParam(required = false) Integer page,
                                                             @RequestParam(required = false) Integer size,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortOrder) {
        List<ProductDto> productDtos = productService.fetchAllProducts(query, page, size, sortField, sortOrder);
        return ResponseEntity.ok(productDtos);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(201).body(productService.addProduct(productDto));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity removeProduct(@PathVariable Long productId) {
        productService.removeProduct(productId);
        return ResponseEntity.status(204).build();
    }
}
