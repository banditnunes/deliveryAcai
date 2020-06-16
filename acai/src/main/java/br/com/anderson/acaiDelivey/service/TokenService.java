package br.com.anderson.acaiDelivey.service;

import br.com.anderson.acaiDelivey.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value(value = "${forum.jwt.expiration}")
    private String expiration;

    @Value(value = "${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();
        Date expirationToken = new Date(hoje.getTime()+Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Açai Delivery")
                .setIssuedAt(hoje)
                .setExpiration(expirationToken)
                .setSubject(usuarioLogado.getId().toString())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
        Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claim = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.valueOf(claim.getSubject());

    }
}
