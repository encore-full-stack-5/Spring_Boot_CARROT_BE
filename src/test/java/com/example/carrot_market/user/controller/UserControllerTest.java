package com.example.carrot_market.user.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Nested
    class dbAccessEndpointTest {
        @Test
        void dbAccess() throws Exception {
            mockMvc.perform(get("/api/v1/users/aop_test")
                            .param("test", "test"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.status").value("OK"))
                    .andExpect(jsonPath("$.message").value("success"))
                    .andExpect(jsonPath("$.data").value("test"));


            mockMvc.getDispatcherServlet().getServletContext().getServletRegistrations().forEach((servletName, registration) -> {
                System.out.println("servletName = " + servletName);
                System.out.println("registration = " + registration);
            });

        }
    }


    @Test
    void signUp() {
    }

    @Test
    void signIn() {
    }
}