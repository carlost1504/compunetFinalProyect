package com.icesi.backend.IntegrationTests;


import com.icesi.backend.DTO.UserCreateDTO;
import com.icesi.backend.models.ShopUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {com.icesi.backend.BackendApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateUser() {

        UserCreateDTO testUser = new UserCreateDTO("jm1811324@gmail.com","+573154175103","P4ssw0rd");
        ResponseEntity<ShopUser> testResponse = this.restTemplate.postForEntity("http://localhost:" + port + "/users/create", testUser, ShopUser.class);
        ShopUser testShopUser = testResponse.getBody();
        assertEquals(201, testResponse.getStatusCodeValue());
        assertNotNull(testShopUser);
        assertEquals("jm1811324@gmail.com", testShopUser.getEmail());
        assertEquals("+573154175103", testShopUser.getPhoneNumber());
        assertEquals("P4ssw0rd", testShopUser.getPassword());

    }


}
