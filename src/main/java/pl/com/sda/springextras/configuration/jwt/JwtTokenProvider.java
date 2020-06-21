package pl.com.sda.springextras.configuration.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secretkey:123}")
    private String secretKey;
    @Value("${security.jwt.token.expiretime:60000}")
    private Long expireTime;
    @Autowired
    private UserDetailsService jwtUserDetailsService;

    public String createToken(String username, Collection<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        Date exp = new Date(now.getTime() + expireTime);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();

    }

    public boolean validateToken(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(jwt);
            if (claims.getBody().getExpiration().before(new Date())) {
                System.out.println("token expired");
                return false;
            }
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String subject = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(subject);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}

