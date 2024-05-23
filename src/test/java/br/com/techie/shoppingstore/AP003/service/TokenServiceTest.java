package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordResetFORM;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenService tokenService;

    private Token token;
    private PasswordResetFORM validForm;
    private PasswordResetFORM invalidForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a Token object
        token = new Token();
        token.setId(1L);
        token.setToken("valid-token");
        token.setDateCreation(LocalDateTime.now());

        // Initialize valid and invalid forms
        validForm = new PasswordResetFORM("valid-token");
        invalidForm = new PasswordResetFORM("invalid-token");
    }

    @Test
    void testValidateTokenAndCodeVerifier_Success() {
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));

        ResponseEntity<Void> response = tokenService.validateTokenAndCodeVerifier(validForm);

        assertNull(response, "Response should be null for valid token and code verifier");
    }

    @Test
    void testValidateTokenAndCodeVerifier_TokenNotFound() {
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.empty());

        ResponseEntity<Void> response = tokenService.validateTokenAndCodeVerifier(validForm);

        assertNotNull(response);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    void testValidateTokenAndCodeVerifier_InvalidCodeVerifier() {
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));

        ResponseEntity<Void> response = tokenService.validateTokenAndCodeVerifier(invalidForm);

        assertNotNull(response);
        assertEquals(ResponseEntity.badRequest().build(), response);
    }

    @Test
    void testFindByToken_Success() {
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.of(token));

        Optional<Token> foundToken = tokenService.findByToken("valid-token");

        assertTrue(foundToken.isPresent());
        assertEquals("valid-token", foundToken.get().getToken());
    }

    @Test
    void testFindByToken_TokenNotFound() {
        when(tokenRepository.findByToken(anyString())).thenReturn(Optional.empty());

        Optional<Token> foundToken = tokenService.findByToken("invalid-token");

        assertFalse(foundToken.isPresent());
    }

    @Test
    void testDeleteToken() {
        doNothing().when(tokenRepository).delete(any(Token.class));

        tokenService.deleteToken(token);

        verify(tokenRepository, times(1)).delete(token);
    }
}
