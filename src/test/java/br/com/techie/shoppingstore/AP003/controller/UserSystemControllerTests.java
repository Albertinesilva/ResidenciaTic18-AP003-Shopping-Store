package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.PasswordUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.service.UserSystemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserSystemControllerTests {

    @InjectMocks
    private UserSystemController userSystemController;

    @Mock
    private UserSystemService userSystemService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Page<UserSystemVIEW> page;
    private UserSystemVIEW userSystemView;
    private UserSystemFORM userSystemForm;
    private PasswordUpdateFORM passwordUpdateForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userSystemController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        userSystemView = new UserSystemVIEW(1L, "User Name", "user@example.com");
        userSystemForm = new UserSystemFORM("User Name", "user@example.com", "password", "password");
        passwordUpdateForm = new PasswordUpdateFORM("oldPassword", "newPassword", "newPassword");

        page = new PageImpl<>(Collections.singletonList(userSystemView));

        when(userSystemService.searchAll(any(Pageable.class))).thenReturn(page);
        when(userSystemService.searchById(anyLong())).thenReturn(userSystemView);
        when(userSystemService.save(any(UserSystemFORM.class))).thenReturn(userSystemView);
        doNothing().when(userSystemService).editPassword(anyLong(), anyString(), anyString(), anyString());
        doNothing().when(userSystemService).activateUserRegistration(anyString());
    }

    @Test
    @DisplayName("Get all users")
    void getAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists());

        verify(userSystemService, times(1)).searchAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Get user by ID")
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/v1/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(userSystemService, times(1)).searchById(1L);
    }

    @Test
    @DisplayName("Create new user")
    void createUser() throws Exception {
        String userSystemFormJson = objectMapper.writeValueAsString(userSystemForm);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userSystemFormJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));

        verify(userSystemService, times(1)).save(any(UserSystemFORM.class));
    }

    @Test
    @DisplayName("Update password")
    void updatePassword() throws Exception {
        String passwordUpdateFormJson = objectMapper.writeValueAsString(passwordUpdateForm);

        mockMvc.perform(patch("/api/v1/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(passwordUpdateFormJson))
                .andExpect(status().isNoContent());

        verify(userSystemService, times(1)).editPassword(anyLong(), anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Confirm user registration")
    void confirmUserRegistration() throws Exception {
        mockMvc.perform(get("/api/v1/users/confirm/register")
                .param("code", "activationCode")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userSystemService, times(1)).activateUserRegistration(anyString());
    }
}
