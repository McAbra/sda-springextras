package pl.com.sda.springextras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.sda.springextras.mapper.ProductMapper;
import pl.com.sda.springextras.products.Product;
import pl.com.sda.springextras.products.ProductDto;
import pl.com.sda.springextras.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> fetchAllProducts(String query, Integer page, Integer size, String sortField, String sortOrder) {
        List<Product> products;
        if (query != null || page != null || size != null || sortField != null || sortOrder != null) {
            Sort sort = Sort.by("ASC".equalsIgnoreCase(sortOrder)? Sort.Direction.ASC: Sort.Direction.DESC
                    , sortField );
            Pageable pageRequest = PageRequest.of(page, size, sort);
            Page<Product> pageContent = productRepository.findByProductName(query, pageRequest);
            products = pageContent.getContent();
        } else {
            products = productRepository.findAll();
        }

        List<ProductDto> productDtos = products
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
