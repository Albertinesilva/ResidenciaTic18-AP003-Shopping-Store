package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Contains all operations to resources for registering, editing and reading a user.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserSystemController {

  private final UserSystemService userService;
  // private final EmailService emailService;

  @Operation(summary = "Create a new user", description = "Feature to create a new user in the system.", responses = {
      @ApiResponse(responseCode = "201", description = "User created successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserVIEW.class))),
      @ApiResponse(responseCode = "409", description = "Email user already registered in the system.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Resources not processed due to invalid input data.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @PostMapping
  public ResponseEntity<UserVIEW> create(@Valid @RequestBody UserFORM createDto) {
    UserVIEW view = userService.save(createDto);
    // emailService.RegistrationConfirmationEmail(user.getUsername());
    return ResponseEntity.status(HttpStatus.CREATED).body(view);
  }

  @Operation(summary = "Retrieve a user by id", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Resource retrieved successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserVIEW.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Resource not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))

  })
  @GetMapping("/{id}")
  // @PreAuthorize("hasRole('ADMIN') OR ( hasRole('CLIENT') AND #id ==
  // authentication.principal.id )")
  public ResponseEntity<UserVIEW> getById(@PathVariable Long id) {
    UserVIEW view = userService.searchById(id);
    return ResponseEntity.ok(view);
  }

  @Operation(summary = "Update password", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN OR CLIENT", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "Password updated successfully."),
      @ApiResponse(responseCode = "400", description = "Password does not match.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Invalid or incorrectly formatted fields.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
  })
  @PatchMapping("/{id}")
  // @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT') AND (#id ==
  // authentication.principal.id)")
  public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordUpdateFORM dto) {
    userService.editPassword(id, dto.old_password(), dto.new_password(), dto.confirm_password());
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "List all registered users", description = "Request requires a Bearer Token. Access restricted to logged in ADMIN", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "List of all registered users", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserVIEW.class)))),
      @ApiResponse(responseCode = "403", description = "User without permission to access this resource.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
  })
  @GetMapping
  // @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserVIEW>> getAll() {
    List<UserVIEW> users = userService.searchAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/confirm/register")
  // @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> Registrationconfirmationresponse(@RequestParam("code") String code) {
    userService.activateUserRegistration(code);
    return ResponseEntity.noContent().build();
  }

}