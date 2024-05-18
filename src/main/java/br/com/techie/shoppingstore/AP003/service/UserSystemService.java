package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.UserFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserVIEW;
import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import br.com.techie.shoppingstore.AP003.mapper.forms.UserFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserViewMapper;
import br.com.techie.shoppingstore.AP003.model.User;
import br.com.techie.shoppingstore.AP003.repository.UserRepository;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.infra.exception.AccessDeniedException;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.model.Token;

import java.util.Base64;
import java.util.List;

@Service
public class UserSystemService {

  @Autowired
  private UserRepository userRepository;

  // private final PasswordEncoder passwordEncoder;

  @Autowired
  private UserFormMapper userFormMapper;

  @Autowired
  private UserViewMapper userViewMapper;

  @Transactional
  public UserVIEW save(UserFORM dto) {
    User entity = userFormMapper.map(dto);
    userRepository.save(entity);
    return userViewMapper.map(entity);
  }

  @Transactional(readOnly = true)
  public UserVIEW searchById(Long id) {
    return userViewMapper.map(userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException(String.format("User with id = %s not found!", id))));
  }

  @Transactional
  public void editPassword(Long id, String currentPassword, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("New password does not match password confirmation!");
    }

    User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    // if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
    //   throw new PasswordInvalidException("Incorrect password!");
    // }

    // user.setPassword(passwordEncoder.encode(newPassword));

    if(!currentPassword.equals(user.getPassword())) {
      throw new PasswordInvalidException("Incorrect password!");
    }

    user.setPassword(newPassword);
  }

  @Transactional(readOnly = false)
  public UserVIEW changePassword(Token token, String newPassword, String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
      throw new PasswordInvalidException("New password does not match password confirmation!");
    }

    User user = token.getUserSystem();
    user.setCodeVerifier(null);
    // user.setPassword(passwordEncoder.encode(newPassword));
    user.setPassword(newPassword);

    return userViewMapper.map(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public List<UserVIEW> searchAll() {
    return userRepository.findAll().stream().map(x -> userViewMapper.map(x)).toList();
  }

  @Transactional(readOnly = true)
  public UserVIEW searchByUsername(String username) {
    return userViewMapper.map(userRepository.findByUsername(username).orElseThrow(
        () -> new EntityNotFoundException(String.format("User with username = %s not found!", username))));
  }

  @Transactional(readOnly = true)
  public RoleEnum searchRoleByUsername(String username) {
    return userRepository.findRoleByUsername(username);
  }

  @Transactional(readOnly = false)
  public void activateUserRegistration(String code) {
    String username = new String(Base64.getDecoder().decode(code));
    User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found!"));

    if (user.hasNotId()) {
      throw new AccessDeniedException("Unable to activate registration. Contact support.");
    }
    user.setActive(true);
  }

}
