package com.nutriapp.auth;

import com.google.common.base.Function;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;

@Service
//@FieldDefaults(level = PRIVATE, makeFinal = true)
final class JWTTokenService implements Clock, TokenService {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    String issuer;

    @Deprecated
    String secretKey;

    private final String jwtSigningKey = "secretKey";

//    JWTTokenService() {
//        super();
//        this.issuer = requireNonNull("infoworld");
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!! issuer " + issuer);
//        this.secretKey = BASE64.encode("www.infoworld.com");
//    }


    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user);
    }


    @Deprecated
    @Override
    public String newToken(final Map<String, String> attributes) {
        final DateTime now = DateTime.now();
        final Claims claims = Jwts.claims().setIssuer(issuer).setIssuedAt(now.toDate());

        claims.putAll(attributes);

        return Jwts.builder().setClaims(claims).signWith(HS256, secretKey).compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public String createToken(Map<String, Object> claims, User user) {
        final String toReturn = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(now())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(12)))
                .signWith(HS256, jwtSigningKey).compact();

        return toReturn;
    }

    @Override
    public Map<String, String> verify(final String token) {
        final JwtParser parser = Jwts.parser().requireIssuer(issuer).setClock(this).setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        String username = extractUsernameFromToken(token); // TODO: 04/01/2023 implement method to extract username from token
        boolean isValid = username.equals(user.getUsername()) && !isTokenExpired(token);
        return isValid;
    }

    public String extractUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token).getBody();
    }

    @Deprecated
    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (final IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }

    @Override
    public Date now() {
        final DateTime now = DateTime.now();
        return now.toDate();
    }

    public boolean isTokenExpired(String token) {
        return false; // TODO: 04/01/2023 implement logic
    }
}