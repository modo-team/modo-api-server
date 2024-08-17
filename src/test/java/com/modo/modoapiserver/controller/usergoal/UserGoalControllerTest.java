package com.modo.modoapiserver.controller.usergoal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modo.modoapiserver.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserGoalControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtil jwtTokenProvider;

    @Test
    public void 유저_목표생성_and_목표상세_가져오기() throws Exception {
//        // Arrange
//        Map<String, Object> body = new HashMap<>();
//        body.put("goalDatetime", "2021-09-01");
//        body.put("title", "운동하기");
//        body.put("icon", "🙂");
//        body.put("difficulty", 3);
//        body.put("teamId", 1);
//        body.put("categoryId", 1);
//        body.put("verificationMethod", "사진");
//
//        String bodyString = objectMapper.writeValueAsString(body);
//
//        User user = User.builder().id(100L).build();
//
//        String jwtToken = jwtTokenProvider.generateToken(user);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + jwtToken);
//
//        // Act & Assert
//        MvcResult postResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/goal")
//                        .headers(headers)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(bodyString))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseString = postResult.getResponse().getContentAsString();
//        JsonNode responseJson = objectMapper.readTree(responseString);
//        Long id = responseJson.get("id").asLong();
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/user/goal/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.goalDatetime").value(body.get("goalDatetime")))
//                .andExpect(jsonPath("$.title").value(body.get("title")))
//                .andExpect(jsonPath("$.icon").value(body.get("icon")))
//                .andExpect(jsonPath("$.difficulty").value(body.get("difficulty")))
//                .andExpect(jsonPath("$.teamId").value(body.get("teamId")))
//                .andExpect(jsonPath("$.categoryId").value(body.get("categoryId")))
//                .andExpect(jsonPath("$.verificationMethod").value(body.get("verificationMethod"))
//                );
    }

    @Test
    public void 유저_목표_제거() {
        // Given

        // When

        // Then
    }
    @Test
    public void 유저_목표_업데이트() {
        // Given

        // When

        // Then
    }
}
