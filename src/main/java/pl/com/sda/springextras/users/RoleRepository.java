package pl.com.sda.springextras.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.com.sda.springextras.users.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    @Query("select r from User u join u.roles r where u.userName=?1")
    List<Role> findRolesByUserName(String username);
}
