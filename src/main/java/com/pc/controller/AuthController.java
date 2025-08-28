package com.pc.controller;

import com.pc.dto.ApiResponse;
import com.pc.dto.LoginRequest;
import com.pc.model.Users;
import com.pc.service.RefreshTokenSer;
import com.pc.service.UserSer;
import com.pc.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserSer userSer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenSer refreshTokenSer;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest req) {
        String email = req.getEmail();
        String password = req.getPassword();

        Users user = userSer.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Invalid credentials"));
        }

        List<String> roles = List.of("ROLE_" + user.getRole());

        String accessToken = jwtUtils.generateAccessToken(
                user.getEmail(),
                roles,
                Map.of("id", user.getId())
        );

        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail());
        refreshTokenSer.issue(user, refreshToken);

        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        )));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> req) {
        String refreshToken = req.get("refreshToken");
        if (refreshToken == null || !jwtUtils.isTokenValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid or expired refresh token"));
        }

        if (!refreshTokenSer.isUsable(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Refresh token is revoked or expired"));
        }

        String email = jwtUtils.extractSubject(refreshToken);
        Users user = userSer.findByEmail(email);
        if (user == null) {
            refreshTokenSer.revoke(refreshToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User no longer exists"));
        }

        List<String> roles = List.of("ROLE_" + user.getRole());

        String newAccessToken = jwtUtils.generateAccessToken(
                user.getEmail(),
                roles,
                Map.of("id", user.getId())
        );

        // (TÙY CHỌN) Rotate refresh token: cấp refresh token mới + revoke cái cũ
        // Nếu muốn xoay vòng, bật phần này:
        // String newRefreshToken = jwtUtils.generateRefreshToken(user.getEmail());
        // refreshTokenService.revoke(refreshToken);
        // refreshTokenService.issue(user.getId().longValue(), newRefreshToken);
        // return ResponseEntity.ok(Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken));

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> req) {
        String refreshToken = req.get("refreshToken");
        if (refreshToken == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "refreshToken is required"));
        }
        refreshTokenSer.revoke(refreshToken);
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
//
//    @PostMapping("/logout-all")
//    public ResponseEntity<?> logoutAll(@RequestBody Map<String, String> req) {
//        String email = req.get("email");
//        Users user = userSer.findByEmail(email);
//        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
//        refreshTokenSer.revokeAllForUser(user);
//        return ResponseEntity.ok(Map.of("message", "Logged out from all devices"));
//    }
}
