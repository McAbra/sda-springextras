package pl.com.sda.springextras.users;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.sda.springextras.products.Product;
import pl.com.sda.springextras.products.ProductType;
import pl.com.sda.springextras.repository.ProductRepository;
import pl.com.sda.springextras.repository.RoleRepository;
import pl.com.sda.springextras.repository.UserRepository;

import java.math.BigDecimal;

@Service
public class DataSeedService implements InitializingBean {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRoleName(ROLE_ADMIN);
            Role user = new Role();
            user.setRoleName(ROLE_USER);
            roleRepository.save(admin);
            roleRepository.save(user);
        }
        if (userRepository.count() == 0) {
            User user = new User();
            user.setFirstName("Janusz");
            user.setLastName("Kowalski");
            user.setPassword(passwordEncoder.encode("password"));
            user.setUserName("user");
            user.getRoles().add(roleRepository.findByRoleName(ROLE_USER));
            User admin = new User();
            admin.setFirstName("Adam");
            admin.setLastName("Nowak");
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.getRoles().add(roleRepository.findByRoleName(ROLE_ADMIN));
            userRepository.save(user);
            userRepository.save(admin);
        }
        if (productRepository.count() == 0) {
            Product product = new Product();
            product.setPrice(BigDecimal.TEN);
            product.setProductName("resorak");
            product.setProductType(ProductType.TOY);
            product.setUrl("");
            productRepository.save(product);
        }
    }
}
