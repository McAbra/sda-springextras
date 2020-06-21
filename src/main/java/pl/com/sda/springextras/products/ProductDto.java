package pl.com.sda.springextras.products;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;
    private String productName;
    private String url;
    private BigDecimal price;
    private ProductType productType;
}
