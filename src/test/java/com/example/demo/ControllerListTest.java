package com.example.demo;


import com.example.demo.configSecurity.AuthRequest;
import com.example.demo.configSecurity.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerListTest {

    @Autowired
    private MockMvc mockMvc;

    public String  token = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYWQiLCJleHAiOjE2MTcwMzkwNzgsImlhdCI6MTYxNjkxOTA3OCwiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIifQ.GjNBp91uzHHhSXkcHr1DtGZ6OZDlNVBi8r9U-qj-hcY";

    @Test
    //@WithMockUser(username = "liza", password = "liza", roles = "USER")
    public void creatNewListAc() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/user/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" + "    \"nameAc\": \"Mad Card #2\"\n" + "}")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    public void creatNewElementInListAc() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/user/lists/2/element")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"nameCard\": \"Card 1\",\n" +
                        "    \"cardNumber\": 22374\n" +
                        "}")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteElementInListAc() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/lists/2/elements/16")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    public void creatNewListElementInListAc() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/user/lists/3/elements")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "     \"listBankCard\" : [\n" +
                        "        {\n" +
                        "            \"nameCard\": \"Card Card\",\n" +
                        "            \"cardNumber\": 1\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"nameCard\": \"Mad\",\n" +
                        "            \"cardNumber\": 1000\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    //@WithMockUser(username = "liza", password = "liza", roles = "USER")
    public void getAllListAccount() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" + "    \"nameAc\": \"Mad Card #2\"\n" + "}")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }
}
