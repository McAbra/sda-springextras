package pl.com.sda.springextras.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.com.sda.springextras.users.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("select r.roleName from User u join Role r where u.userName=?1")
    List<String> findRolesByUserName(String username);
}
