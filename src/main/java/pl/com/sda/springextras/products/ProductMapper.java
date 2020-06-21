package pl.com.sda.springextras.products;

import org.springframework.stereotype.Service;
import pl.com.sda.springextras.products.Product;
import pl.com.sda.springextras.products.ProductDto;

@Service
public class ProductMapper {

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .productName(product.getProductName())
                .id(product.getId())
                .price(product.getPrice())
                .productType(product.getProductType())
                .url(product.getUrl())
                .build();
    }

    public Product mapToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getProductName(),
                productDto.getUrl(), productDto.getPrice(),
                productDto.getProductType());
    }

    public Product updateProduct(Product product, ProductDto productDto) {
        product.setUrl(productDto.getUrl());
        product.setProductType(productDto.getProductType());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
