package br.com.techie.shoppingstore.AP003.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.techie.shoppingstore.AP003.controller.exceptions.ErrorMessage;
import br.com.techie.shoppingstore.AP003.dto.UserSystemCreateDto;
import br.com.techie.shoppingstore.AP003.dto.UserSystemResponseDto;
import br.com.techie.shoppingstore.AP003.dto.UserSystemSenhaDto;
import br.com.techie.shoppingstore.AP003.mapper.UserSystemMapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.service.EmailService;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações aos recursos para cadastro, edição e leitura de um usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UserSystemController {

        private final UserSystemService userService;
        private final EmailService emailService;

        @Operation(summary = "Cria um novo usuário", description = "Recurso para criar um novo usuário no sistema.", responses = {
                        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemResponseDto.class))),
                        @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "422", description = "Recursos não processados por dados de entrada invalidos.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        })
        @PostMapping
        public ResponseEntity<UserSystemResponseDto> create(@Valid @RequestBody UserSystemCreateDto createDto) throws MessagingException {
                UserSystem user = userService.save(UserSystemMapper.toUserSystem(createDto));
                emailService.RegistrationConfirmationEmail(user.getUsername());
                return ResponseEntity.status(HttpStatus.CREATED).body(UserSystemMapper.toDto(user));
        }

        @Operation(summary = "Recuperar um usuário pelo id", description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN OU CLIENTE logado",
        security = @SecurityRequirement(name = "security"), 
                responses = {
                        @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemResponseDto.class))),
                        @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
                        
        })
        @GetMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN') OR ( hasRole('CLIENTE') AND #id == authentication.principal.id )")
        public ResponseEntity<UserSystemResponseDto> getById(@PathVariable Long id) {
                UserSystem user = userService.searchById(id);
                return ResponseEntity.ok(UserSystemMapper.toDto(user));
        }

        @Operation(summary = "Atualizar senha", description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN OU CLIENTE logado", 
                security = @SecurityRequirement(name = "security"),
                responses = {
                        @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso."),
                        @ApiResponse(responseCode = "400", description = "Senha não confere.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                        @ApiResponse(responseCode = "422", description = "Campos invalidos ou formatados incorretamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
        })
        @PatchMapping("/{id}")
        @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE') AND (#id == authentication.principal.id)")
        public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserSystemSenhaDto dto) {
                userService.editPassword(id, dto.getCurrentpassword(), dto.getNewPassword(), dto.getConfirmPassword());
                return ResponseEntity.noContent().build();
        }

        @Operation(summary = "Listar todos os usuarios cadastratos", description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN logado", 
                security = @SecurityRequirement(name = "security"),
                responses = {
                        @ApiResponse(responseCode = "200", description = "Lista com todos os usuarios cadastrados", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserSystemResponseDto.class)))),
                        @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
        })
        @GetMapping
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<List<UserSystemResponseDto>> getAll() {
                List<UserSystem> users = userService.searchAll();
                return ResponseEntity.ok(UserSystemMapper.toListDto(users));
        }

        @GetMapping("/confirmacao/cadastro")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> Registrationconfirmationresponse(@RequestParam("codigo") String codigo) {
                userService.activateUserRegistration(codigo);
                return ResponseEntity.noContent().build();
        }

}