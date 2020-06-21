package pl.com.sda.springextras.users;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.sda.springextras.users.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
