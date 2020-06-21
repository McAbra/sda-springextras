package pl.com.sda.springextras.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.sda.springextras.configuration.jwt.JwtTokenProvider;

import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        boolean passwordMatched = passwordEncoder.matches(password,userDetails.getPassword());
        if (!passwordMatched) {
            throw new RuntimeException("password doesn't match");
        }
        Authentication authenticate = (new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
        String token = jwtTokenProvider.createToken(username, roleRepository.findRolesByUserName(username)
        .stream().map(Role::getRoleName).collect(Collectors.toList()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
