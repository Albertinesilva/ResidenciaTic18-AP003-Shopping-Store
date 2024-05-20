package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import br.com.techie.shoppingstore.AP003.mapper.forms.UserSystemFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserSystemViewMapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.infra.exception.AccessDeniedException;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.infra.exception.EmailUniqueViolationException;
import br.com.techie.shoppingstore.AP003.model.Token;

import java.util.Base64;
import java.util.List;

@Service
public class UserSystemService {

  @Autowired
  private UserSystemRepository userRepository;

  // private final PasswordEncoder passwordEncoder;

  @Autowired
  private UserSystemFormMapper userFormMapper;

  @Autowired
  private UserSystemViewMapper userViewMapper;

  // @Transactional
  // public UserVIEW save(UserFORM dto) {
  // User entity = userFormMapper.map(dto);
  // userRepository.save(entity);
  // return userViewMapper.map(entity);
  // }

  @Transactional
  public UserSystemVIEW save(UserSystemFORM dto) {
    try {
      UserSystem entity = userFormMapper.map(dto);
      // dto.setPassword(passwordEncoder.encode(dto.password()));
      userRepository.save(entity);
      return userViewMapper.map(entity);

    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
      throw new EmailUniqueViolationException(
          String.format("Email: %s já cadastrado: ", dto.email()));
    }
  }

  @Transactional(readOnly = true)
  public UserSystemVIEW searchById(Long id) {
    return userViewMapper.map(userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException(String.format("User with id = %s not found!", id))));
  }

  @Transactional
  public void editPassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("New password does not match password confirmation!");
    }

    UserSystem user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    // if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
    // throw new PasswordInvalidException("Incorrect password!");
    // }

    // user.setPassword(passwordEncoder.encode(newPassword));

    if (!currentPassword.equals(user.getPassword())) {
      throw new PasswordInvalidException("Incorrect password!");
    }

    user.setPassword(newPassword);
  }

  @Transactional(readOnly = false)
  public UserSystemVIEW changePassword(Token token, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("New password does not match password confirmation!");
    }

    UserSystem user = token.getUserSystem();
    user.setCodeVerifier(null);
    // user.setPassword(passwordEncoder.encode(newPassword));
    user.setPassword(newPassword);

    return userViewMapper.map(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public Page<UserSystemVIEW> searchAll(Pageable pageable) {
    return userRepository.findAll(pageable).map(x -> userViewMapper.map(x));
  }

  @Transactional(readOnly = true)
  public UserSystemVIEW searchByUsername(String username) {
    return userViewMapper.map(userRepository.findByEmail(username).orElseThrow(
        () -> new EntityNotFoundException(String.format("User with username = %s not found!", username))));
  }

  @Transactional(readOnly = true)
  public RoleEnum searchRoleByUsername(String username) {
    return userRepository.findRoleByEmail(username);
  }

  @Transactional(readOnly = false)
  public void activateUserRegistration(String code) {
    String username = new String(Base64.getDecoder().decode(code));
    UserSystem user = userRepository.findByEmail(username)
        .orElseThrow(() -> new EntityNotFoundException("User not found!"));

    if (user.hasNotId()) {
      throw new AccessDeniedException("Unable to activate registration. Contact support.");
    }
    user.setActive(true);
  }

}
