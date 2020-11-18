package com.ig5.iwa.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(LocationsController.class)
@AutoConfigureMockMvc
public class LocationsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllShouldReturnArrayOfLocations() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
