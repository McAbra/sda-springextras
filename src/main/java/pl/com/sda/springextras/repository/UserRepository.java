package pl.com.sda.springextras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.com.sda.springextras.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
