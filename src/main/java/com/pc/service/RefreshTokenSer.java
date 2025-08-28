package com.pc.service;

import com.pc.model.RefreshToken;
import com.pc.model.Users;
import com.pc.repository.RefreshTokenRepo;
import com.pc.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RefreshTokenSer {

    @Autowired
    private RefreshTokenRepo repo;

    @Autowired
    private JwtUtils jwtUtils;

    public RefreshToken issue(Users user, String refreshTokenRaw) {
        var claims = jwtUtils.extractAllClaims(refreshTokenRaw);
        var exp = claims.getExpiration().toInstant();

        RefreshToken rt = new RefreshToken();
        rt.setToken(refreshTokenRaw);
        rt.setUser(user);
        rt.setExpiresAt(exp);
        rt.setRevoked(false);
        return repo.save(rt);
    }

    public boolean isUsable(String token) {
        Optional<RefreshToken> opt = repo.findByToken(token);
        if (opt.isEmpty()) return false;
        RefreshToken rt = opt.get();
        return !rt.isRevoked() && rt.getExpiresAt().isAfter(Instant.now());
    }

    public void revoke(String token) {
        repo.findByToken(token).ifPresent(rt -> {
            rt.setRevoked(true);
            repo.save(rt);
        });
    }

    public void revokeAllForUser(Users user) {
        List<RefreshToken> list = repo.findAllByUser(user);
        for (RefreshToken rt : list) {
            if (!rt.isRevoked()) {
                rt.setRevoked(true);
            }
        }
        repo.saveAll(list);
    }
}
