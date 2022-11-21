package kr.smhrd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class BoardControllerTest {

   @Autowired
   private MockMvc mockMvc;
   
   @Test
   public void testHello() throws Exception{
     //HTTP요청
      mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "Maeil"))
     //HTTP응답 검증   
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("Hello : Maeil"));
      
   }
}
