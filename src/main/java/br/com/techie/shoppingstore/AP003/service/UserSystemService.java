package br.com.techie.shoppingstore.AP003.service;

import lombok.RequiredArgsConstructor;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.infra.exception.AccessDeniedException;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.infra.exception.UsernameUniqueViolationException;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;

import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserSystemService {

  private final UserSystemRepository userRepository;
  // private final PasswordEncoder passwordEncoder;

  @Transactional
  public UserSystem save(UserSystem user) {
    try {
      // user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);

    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
      throw new UsernameUniqueViolationException(
          String.format("Username: %s já cadastrado: ", user.getUsername()));
    }
  }

  @Transactional(readOnly = true)
  public UserSystem searchById(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id)));
  }

  @Transactional
  public UserSystem editPassword(Long id, String currentpassword, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
    }

    UserSystem user = searchById(id);
    // if (!passwordEncoder.matches(currentpassword, user.getPassword())) {
    //   throw new PasswordInvalidException("Sua senha não confere.");
    // }

    // user.setPassword(passwordEncoder.encode(newPassword));

    if(currentpassword.equals(user.getPassword())) {
      user.setPassword(newPassword);
    } else {
      throw new PasswordInvalidException("Sua senha não confere.");
    } 
    return user;

    // Outra forma de fazer a mesma coisa é:
    // return usuarioRepository.save(user);
  }

  @Transactional(readOnly = false)
  public UserSystem changePassword(Token token, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("Nova senha não confere com confirmação de senha.");
    }

    UserSystem user = token.getUserSystem();
    user.setCodeverifier(null);
    // user.setPassword(passwordEncoder.encode(newPassword));
    user.setPassword(newPassword);

    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public List<UserSystem> searchAll() {
    return userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public UserSystem searchByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new EntityNotFoundException(String.format("Usuário username = %s não encontrado", username)));
  }

  @Transactional(readOnly = true)
  public UserSystem.Role searchRoleByUsername(String username) {
    return userRepository.findRoleByUsername(username);
  }

  @Transactional(readOnly = false)
  public void activateUserRegistration(String codigo) {
    String username = new String(Base64.getDecoder().decode(codigo));
    UserSystem user = searchByUsername(username);

    if (user.hasNotId()) {
      throw new AccessDeniedException("Não foi possível ativar o cadastro. Entre em contato com o suporte.");
    }
    user.setActive(true);
  }

}
