package com.ig5.iwa;

import com.ig5.iwa.controllers.LocationsController;
import com.ig5.iwa.models.Location;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.LocationRepository;
import com.ig5.iwa.services.LocationService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@AutoConfigureMockMvc
public class HttpRequestLocationTest {

    private MockMvc mockMvc;

    @Mock
    private LocationRepository locationRepository;

    @MockBean
    private LocationService service;

    @InjectMocks
    private LocationsController locationsController;

    @BeforeEach
    public void setup() {
        System.out.println("doing setup");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationsController).build();
    }

    @Test
    @DisplayName("GET /locations/1 - Found")
    public void testGetOneLocation() throws Exception {
        int id = 1;
        Location location = new Location(id,40,3);
        when(service.findLocationById(id)).thenReturn(Optional.of(location));
        when(service.findLocationById(id)).thenReturn(java.util.Optional.of(location));
        mockMvc.perform(get("/api/v1/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.longitude", is(40.0)))
                .andExpect(jsonPath("$.latitude", is(3.0)));
    }

    @Test
    @DisplayName("GET /locations/-1 - Not Found")
    public void testGetUnknowLocation() throws Exception {
        mockMvc.perform(get("/api/v1/locations/{id}",-1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete /locations/1 - Success")
    public void deleteLocation() throws Exception {
        int id = 1;
        Location location = new Location(id,40,3);
        when(service.findLocationById(id)).thenReturn(java.util.Optional.of(location));
        mockMvc.perform(delete("/api/v1/locations/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /locations/1 - Not Found")
    public void deleteLocationNotFound() throws Exception {
        doReturn(Optional.empty()).when(service.findLocationById(-1));
        mockMvc.perform(delete("/api/v1/locations/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Put /locations/1 - Success")
    public void PutUser() throws Exception {

    }
}
