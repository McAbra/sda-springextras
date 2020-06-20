package pl.com.sda.springextras.products;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String productName;
    private String url;
    private BigDecimal price;
    private ProductType productType;
}
