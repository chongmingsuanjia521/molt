package asia.wjm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static io.jsonwebtoken.Claims.ISSUER;

public class JwtUtil {
    // 密钥
    private static final String BASE64_STRING_KEY_JWT = "oFd/+ENGA4riK9NtoJ59mDjoXna/fe2WNg6j/76p0sI=";
    // 过期时间 单位毫秒
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds
    // 颁发者
    private static final String ISSUER = "WeAdmin";
    // 受众
    // private static final String AUDIENCE = "http://localhost:8080/";

    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */

    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        Key key = Base64StringToJwtKey(BASE64_STRING_KEY_JWT);
        Date issuedDate = new Date(System.currentTimeMillis());
        Date ExpirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String Id = UUID.randomUUID().toString().replace("-", "");

        return Jwts.builder()
                .setClaims(claims) // 自定义信息
                .setIssuedAt(issuedDate)  // 签发时间
                .setExpiration(ExpirationDate) // 设置过期时间
                .signWith(key, SignatureAlgorithm.HS256) // 使用HS256对称加密算法签名
                // .setAudience(AUDIENCE)
                .setIssuer(ISSUER) // 签发者
                .setId(Id) // ID 可选
                .compact();
    }
    public static Key Base64StringToJwtKey(String strBase64Key) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(strBase64Key));
    }
    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
