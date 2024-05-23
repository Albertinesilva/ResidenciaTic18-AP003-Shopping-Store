package br.com.techie.shoppingstore.AP003.service;


import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import br.com.techie.shoppingstore.AP003.mapper.forms.UserSystemFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserSystemViewMapper;
import br.com.techie.shoppingstore.AP003.model.Token;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;
import br.com.techie.shoppingstore.AP003.infra.exception.AccessDeniedException;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.infra.exception.EmailUniqueViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Base64;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSystemServiceTest {

    @Mock
    private UserSystemRepository userRepository;

    @Mock
    private UserSystemFormMapper userFormMapper;

    @Mock
    private UserSystemViewMapper userViewMapper;

    @InjectMocks
    private UserSystemService userService;

    private UserSystem user;
    private UserSystemFORM userForm;
    private UserSystemVIEW userView;
    private Token token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new UserSystem();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setUsername("testUser");
        user.setPassword("password");
        user.setActive(true);

        userForm = new UserSystemFORM();
        userForm.setEmail("test@example.com");
        userForm.setUsername("testUser");
        userForm.setPassword("password");
        userForm.setPasswordConfirm("password");

        userView = new UserSystemVIEW();
        userView.setEmail("test@example.com");
        userView.setUsername("testUser");

        token = new Token();
        token.setToken("valid-token");
        token.setUserSystem(user);
    }

    @Test
    void testSave_Success() {
        when(userFormMapper.map(any(UserSystemFORM.class))).thenReturn(user);
        when(userRepository.save(any(UserSystem.class))).thenReturn(user);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userService.save(userForm);

        assertNotNull(result);
        assertEquals(userView, result);
    }

    @Test
    void testSave_EmailUniqueViolation() {
        when(userFormMapper.map(any(UserSystemFORM.class))).thenReturn(user);
        when(userRepository.save(any(UserSystem.class)))
                .thenThrow(new org.springframework.dao.DataIntegrityViolationException("Email já cadastrado"));

        EmailUniqueViolationException exception = assertThrows(EmailUniqueViolationException.class, () -> {
            userService.save(userForm);
        });

        assertEquals("Email: test@example.com já cadastrado: ", exception.getMessage());
    }

    @Test
    void testSearchById_Success() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userService.searchById(1L);

        assertNotNull(result);
        assertEquals(userView, result);
    }

    @Test
    void testSearchById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.searchById(1L);
        });

        assertEquals("User with id = 1 not found!", exception.getMessage());
    }

    @Test
    void testEditPassword_Success() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.editPassword(1L, "password", "newPassword", "newPassword");

        verify(userRepository, times(1)).save(user);
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testEditPassword_InvalidCurrentPassword() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            userService.editPassword(1L, "wrongPassword", "newPassword", "newPassword");
        });

        assertEquals("Incorrect password!", exception.getMessage());
    }

    @Test
    void testEditPassword_PasswordsDoNotMatch() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            userService.editPassword(1L, "password", "newPassword", "differentNewPassword");
        });

        assertEquals("New password does not match password confirmation!", exception.getMessage());
    }

    @Test
    void testChangePassword_Success() {
        when(userRepository.save(any(UserSystem.class))).thenReturn(user);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userService.changePassword(token, "newPassword", "newPassword");

        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
        assertEquals(userView, result);
    }

    @Test
    void testChangePassword_PasswordsDoNotMatch() {
        PasswordInvalidException exception = assertThrows(PasswordInvalidException.class, () -> {
            userService.changePassword(token, "newPassword", "differentNewPassword");
        });

        assertEquals("New password does not match password confirmation!", exception.getMessage());
    }

    @Test
    void testSearchAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserSystem> userPage = new PageImpl<>(List.of(user));
        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        Page<UserSystemVIEW> result = userService.searchAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(userView, result.getContent().get(0));
    }

    @Test
    void testSearchByUsername_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userService.searchByUsername("testUser");

        assertNotNull(result);
        assertEquals(userView, result);
    }

    @Test
    void testSearchByUsername_NotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.searchByUsername("testUser");
        });

        assertEquals("User with username = testUser not found!", exception.getMessage());
    }

    @Test
    void testSearchRoleByUsername() {
        when(userRepository.findRoleByEmail(anyString())).thenReturn(RoleEnum.ROLE_CLIENT);

        RoleEnum role = userService.searchRoleByUsername("testUser");

        assertEquals(RoleEnum.ROLE_CLIENT, role);
    }

    @Test
    void testActivateUserRegistration_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        String code = Base64.getEncoder().encodeToString("test@example.com".getBytes());

        userService.activateUserRegistration(code);

        assertTrue(user.isActive());
    }

    @Test
    void testActivateUserRegistration_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        String code = Base64.getEncoder().encodeToString("test@example.com".getBytes());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.activateUserRegistration(code);
        });

        assertEquals("User not found!", exception.getMessage());
    }

    @Test
    void testActivateUserRegistration_AccessDenied() {
        user.setId(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        String code = Base64.getEncoder().encodeToString("test@example.com".getBytes());

        AccessDeniedException exception = assertThrows(AccessDeniedException.class, () -> {
            userService.activateUserRegistration(code);
        });

        assertEquals("Unable to activate registration. Contact support.", exception.getMessage());
    }
}
