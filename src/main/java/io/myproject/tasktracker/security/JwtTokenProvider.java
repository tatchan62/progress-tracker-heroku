package io.myproject.tasktracker.security;

/*
1. Generate the token
2. Validate the token
3. Get user id from token

 */

import io.jsonwebtoken.*;
import io.myproject.tasktracker.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.myproject.tasktracker.security.SecurityConstants.EXPIRATION_TIME;
import static io.myproject.tasktracker.security.SecurityConstants.SECRET;

// Generate a token
@Component
public class JwtTokenProvider {

    // Generate a token when user provides a valid username and password
    public String generateToken(Authentication authentication){

        // User is authenticated. Now get that user
        User user = (User) authentication.getPrincipal();
        String userId = Long.toString(user.getId());

        // Time frame for the token
        Date now = new Date(System.currentTimeMillis());
        Date expireDate = new Date(now.getTime() + EXPIRATION_TIME);

        // used Map to store use info in a token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        // build the token and return
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }
        catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }
        catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }
        catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    // Get user ID from token
    public Long getUserIdFromJWT(String token){

        // extract the token
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");

        return Long.parseLong(id);
    }

}
