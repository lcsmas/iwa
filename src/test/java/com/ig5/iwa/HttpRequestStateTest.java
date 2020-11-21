package com.ig5.iwa;

import com.ig5.iwa.controllers.StatesController;
import com.ig5.iwa.controllers.UsersController;
import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.StateRepository;
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
public class HttpRequestStateTest {

    private MockMvc mockMvc;

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StatesController statesController;

    @BeforeEach
    public void setup() {
        System.out.println("doing setup");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(statesController).build();
    }

    @Test
    @DisplayName("GET /states/1 - Not Found")
    public void testGetUnknowState() throws Exception {
        doReturn(Optional.empty()).when(stateRepository).findById(1);
        mockMvc.perform(get("/api/v1/states/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete /states/1 - Success")
    public void deleteState() throws Exception {
        int id = 1;
        State state = new State(id,"sane");
        when(stateRepository.findById(id)).thenReturn(java.util.Optional.of(state));
        mockMvc.perform(delete("/api/v1/states/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /users/1 - Not Found")
    public void deleteStateNotFound() throws Exception {
        doReturn(Optional.empty()).when(stateRepository).findById(1);
        mockMvc.perform(delete("/api/v1/states/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Put /states/1 - Success")
    public void PutState() throws Exception {

    }
}
