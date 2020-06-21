package pl.com.sda.springextras.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p from Product p where lower(p.productName) like concat(lower(?1),'%')") //jpql
    Page<Product> findByProductName(String productName, Pageable pageRequest);
}
