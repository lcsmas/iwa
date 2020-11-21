package com.ig5.iwa;

import com.ig5.iwa.controllers.LocationsController;
import com.ig5.iwa.controllers.NotificationsController;
import com.ig5.iwa.controllers.StatesController;
import com.ig5.iwa.controllers.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class IwaApplicationTests {

	@Autowired
	UsersController usersController;
	@Autowired
	StatesController statesController;
	@Autowired
	NotificationsController notificationsController;
	@Autowired
	LocationsController locationsController;

	@Test
	void contextLoads() throws Exception{
		assertThat(usersController).isNotNull();
		assertThat(statesController).isNotNull();
		assertThat(notificationsController).isNotNull();
		assertThat(locationsController).isNotNull();
	}

}
