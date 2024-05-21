package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordResetFORM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.repository.TokenRepository;
// import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenService {

  @Autowired
  private TokenRepository tokenRepository;

  // @Autowired
  // JwtUserDetailsService jwtUserDetailsService;

  // @Autowired
  // private EmailService emailService;

  // @Transactional(readOnly = false)
  // public Token requestPasswordReset(User user) throws MessagingException {

  //   JwtToken token = jwtUserDetailsService.getTokenAuthenticated(user.getUsername());

  //   String verifier = token.getToken();
  //   LocalDateTime dateCreation = LocalDateTime.now();
  //   user.setCodeVerifier(verifier);
  //   Token tokenEntity = new Token(verifier, dateCreation, user);
  //   tokenRepository.save(tokenEntity);

  //   emailService.sendOrderResetPassword(user.getUsername(), verifier);
  //   log.info("Token created for user {}", user.getUsername());
  //   return tokenEntity;
  // }

  public ResponseEntity<Void> validateTokenAndCodeVerifier(PasswordResetFORM dto) {
    // Checks if the token is valid
    // if (!JwtUtils.isTokenValid(dto.toke())) {
    //   log.error("Invalid Token: " + token);
    //   return ResponseEntity.badRequest().build();
    // }

    // Fetches user token by given token
    Optional<Token> tokenOfUser = findByToken(dto.token());
    if (tokenOfUser.isEmpty()) {
      log.error("Token not found for token {}", dto.token());
      return ResponseEntity.notFound().build();
    }

    // Checks whether the verification code is valid
    Token tokenFound = tokenOfUser.get();
    if (!dto.token().equals(tokenFound.getToken())) {
      log.error("Invalid verification code for token {}", tokenFound);
      return ResponseEntity.badRequest().build();
    }

    // If everything is valid, return null
    return null;
  }

  @Transactional(readOnly = true)
  public Optional<Token> findByToken(String token) {
    return tokenRepository.findByToken(token);
  }

  public void deleteToken(Token token) {
    tokenRepository.delete(token);
  }

}