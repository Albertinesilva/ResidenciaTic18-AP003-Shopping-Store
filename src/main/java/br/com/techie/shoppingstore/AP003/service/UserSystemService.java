package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.mapper.forms.UserFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.UserSystemUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserSystemViewMapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.infra.exception.AccessDeniedException;
import br.com.techie.shoppingstore.AP003.infra.exception.EmailUniqueViolationException;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.model.Token;

import java.util.Base64;
import java.util.List;

@Service
public class UserSystemService {

    @Autowired
    private UserSystemRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFormMapper userFormMapper;

    @Autowired
    private UserSystemViewMapper userViewMapper;

    @Autowired
    private UserSystemUpdateMapper userUpdateMapper;

    @Transactional
    public UserSystemVIEW save(UserSystemFORM dto) {
        if (!dto.password().equals(dto.passwordConfirm())) {
            throw new PasswordInvalidException("New password does not match password confirmation!");
        }
        try {
            UserSystem entity = userFormMapper.map(dto);
            entity.setPassword(passwordEncoder.encode(dto.password()));
            entity.setPasswordConfirm(passwordEncoder.encode(dto.passwordConfirm()));
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

        UserSystem userSystem = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        if (!passwordEncoder.matches(currentPassword, userSystem.getPassword())) {
            throw new PasswordInvalidException("Incorrect password!");
        }

        userSystem.setPassword(passwordEncoder.encode(newPassword));

    }

    @Transactional(readOnly = false)
    public UserSystemVIEW changePassword(Token token, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordInvalidException("New password does not match password confirmation!");
        }

        UserSystem user = token.getUserSystem();
        user.setCodeVerifier(null);
        user.setPassword(passwordEncoder.encode(newPassword));

        return userViewMapper.map(userRepository.save(user));
    }

    @Transactional
    public UserSystemVIEW update(UserSystemUpdateFORM dto) {
        UserSystem entity = userRepository.findById(dto.user_id()).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        entity = userUpdateMapper.map(dto, entity);
        userRepository.save(entity);
        return userViewMapper.map(entity);
    }

    @Transactional(readOnly = true)
    public List<UserSystemVIEW> searchAll() {
        return userRepository.findAll().stream().map(x -> userViewMapper.map(x)).toList();
    }

    @Transactional(readOnly = true)
    public UserSystem searchByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                String.format(String.format("User with email = %s not found!", email))));
    }

    @Transactional(readOnly = true)
    public UserSystem.Role searchRoleByEmail(String email) {
        return userRepository.findRoleByEmail(email);
    }

    @Transactional(readOnly = false)
    public void activateUserRegistration(String code) {
        String email = new String(Base64.getDecoder().decode(code));
        UserSystem user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!"));

        if (user.hasNotId()) {
            throw new AccessDeniedException("Unable to activate registration. Contact support.");
        }
        user.setActive(true);
    }

}
