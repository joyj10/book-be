package com.won.bookdomain.config.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.data.util.Pair;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.Random;

@UtilityClass
public class JwtKey {
    private static final Map<String, String> SECRET_KEY_SET = Map.of(
            "key1", "bookProject",
            "key2", "GoodSpringSecurityNiceSpringSecurity",
            "key3", "HelloSpringSecurity"
    );
    private static final String[] KID_SET = SECRET_KEY_SET.keySet().toArray(new String[0]);
    private static Random randomIndex = new Random();

    public static Pair<String, Key> getRandomKey() {
        String kid = KID_SET[randomIndex.nextInt(KID_SET.length)];
        String secretKey = SECRET_KEY_SET.get(kid);
        return Pair.of(kid, Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)));
    }

    public static Key getKey(String kid) {
        String key = SECRET_KEY_SET.getOrDefault(kid, null);
        if (key == null)
            return null;
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
}
