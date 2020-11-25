package com.ig5.iwa;

import com.ig5.iwa.controllers.LocationsController;
import com.ig5.iwa.controllers.UsersController;
import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.LocationRepository;
import com.ig5.iwa.repositories.UserRepository;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class HttpRequestUserTest {

        private MockMvc mockMvc;

        @Mock
        private UserRepository userRepository;

        @InjectMocks
        private UsersController usersController;

        @BeforeEach
        public void setup() {
            System.out.println("doing setup");
            MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
        }

    @Test
    @DisplayName("GET /users/1 - Found")
    public void testGetOneUser() throws Exception {
        int id = 1;
        User user = new User(id,"iwa@gmail.com","mdp");
        when(userRepository.getOne(id)).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_user", is(1)))
                .andExpect(jsonPath("$.mail", is("iwa@gmail.com")))
                .andExpect(jsonPath("$.password", is("mdp")));
    }

    @Test
    @DisplayName("GET /users/1 - Not Found")
    public void testGetUnknowUser() throws Exception {
    doReturn(Optional.empty()).when(userRepository).findById(-1);
    mockMvc.perform(get("/api/v1/users/{id}",-1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete /users/1 - Success")
    public void deleteUser() throws Exception {
        int id = 1;
        User user = new User(id,"iwa@gmail.com","mdp");
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /users/1 - Not Found")
    public void deleteUserNotFound() throws Exception {
            doReturn(Optional.empty()).when(userRepository).findById(1);
            mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Put /users/1 - Success")
    public void PutUser() throws Exception {

    }
}
