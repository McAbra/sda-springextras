package pl.com.sda.springextras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.sda.springextras.mapper.ProductMapper;
import pl.com.sda.springextras.products.Product;
import pl.com.sda.springextras.products.ProductDto;
import pl.com.sda.springextras.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> fetchAllProducts() {
        List<ProductDto> productDtos = productRepository.findAll()
                .stream()
                .map(p -> productMapper.mapToDto(p))
                .collect(Collectors.toList());
        return productDtos;
    }

    public ProductDto addProduct(ProductDto productDto) {
        return productMapper.mapToDto(productRepository
                .save(productMapper
                        .mapToEntity(productDto)));

    }
}
