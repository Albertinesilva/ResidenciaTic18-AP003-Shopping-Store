package br.com.techie.shoppingstore.AP003.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemEmailFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.service.TokenService;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Passwords Recovery", description = "Contém todas as operações aos recursos para receber um e-mail de recuperação de senha.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/password-recovery")
public class PasswordRecoveryController {

  private final TokenService tokenService;
  private final UserSystemService userService;

  @Operation(summary = "Redefinir senha pelo email", description = "Recurso para redefinir senha pelo email", responses = {
      @ApiResponse(responseCode = "204", description = "Pedido de redefinição de senha enviado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "401", description = "Usuário não autorizado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PostMapping
  public ResponseEntity<?> redefinepassword(@RequestBody @Valid UserSystemEmailFORM dto)
      throws MessagingException {

    UserSystem user = userService.searchByEmail(dto.email());
    Optional<Token> tokenExistenteOptional = tokenService.findByToken(user.getCodeVerifier());
    tokenExistenteOptional.ifPresent(token -> tokenService.deleteToken(token));

    Token token = tokenService.requestPasswordReset(user);
    log.info("Token criado para o usuário {}", dto.email());
    return ResponseEntity.ok(token);
  }

}