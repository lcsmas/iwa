package com.ig5.iwa;

import com.ig5.iwa.controllers.NotificationsController;
import com.ig5.iwa.controllers.StatesController;
import com.ig5.iwa.controllers.UsersController;
import com.ig5.iwa.models.Notification;
import com.ig5.iwa.models.State;
import com.ig5.iwa.models.User;
import com.ig5.iwa.repositories.NotificationRepository;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class HttpRequestNotificationTest {

    private MockMvc mockMvc;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationsController notificationsController;

    @BeforeEach
    public void setup() {
        System.out.println("doing setup");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(notificationsController).build();
    }

    @Test
    @DisplayName("GET /notifications/1 - Not Found")
    public void testGetUnknowNotification() throws Exception {
        doReturn(Optional.empty()).when(notificationRepository).findById(1);
        mockMvc.perform(get("/api/v1/notifications/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete /notifications/1 - Success")
    public void deleteNotification() throws Exception {
        int id = 1;

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        Notification notification = new Notification(id,"labelNotification",currentTimestamp);
        when(notificationRepository.findById(id)).thenReturn(java.util.Optional.of(notification));
        mockMvc.perform(delete("/api/v1/notifications/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /notifications/1 - Not Found")
    public void deleteNotificationNotFound() throws Exception {
        doReturn(Optional.empty()).when(notificationRepository).findById(1);
        mockMvc.perform(delete("/api/v1/notifications/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Put /notifications/1 - Success")
    public void PutNotifification() throws Exception {

    }
}
