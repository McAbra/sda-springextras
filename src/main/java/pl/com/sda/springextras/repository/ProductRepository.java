package pl.com.sda.springextras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.sda.springextras.products.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductName(String productName, PageRequest pageRequest);
}
