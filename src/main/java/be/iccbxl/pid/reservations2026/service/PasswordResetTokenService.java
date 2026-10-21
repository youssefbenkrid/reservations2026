package be.iccbxl.pid.reservations2026.service;

import be.iccbxl.pid.reservations2026.model.PasswordResetToken;
import be.iccbxl.pid.reservations2026.model.User;
import be.iccbxl.pid.reservations2026.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository tokenRepo;

    public PasswordResetToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken prt = new PasswordResetToken();
        prt.setToken(token);
        prt.setUser(user);
        prt.setExpiryDate(LocalDateTime.now().plusHours(1));
        return tokenRepo.save(prt);
    }

    public User validatePasswordResetToken(String token) {
        PasswordResetToken prt = tokenRepo.findByToken(token).orElse(null);
        if (prt == null || prt.isExpired()) {
            return null;
        }
        return prt.getUser();
    }

    @Transactional
    public void deleteToken(String token) {
        tokenRepo.deleteByToken(token);
    }
}