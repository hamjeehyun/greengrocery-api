package greengrocery.controller;

import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.service.FruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class FruitControllerTest {
    @MockBean
    private FruitService fruitService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 과일_토큰_가져오기() throws Exception {
        // Given
        TokenDto expected = new TokenDto("fruitAccessToken");

        //When
        when(fruitService.getToken()).thenReturn(expected);

        // Then
        mockMvc.perform(get("/fruits/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").value("fruitAccessToken"))
                .andDo(print());
    }

    @Test
    void 과일_전체_목록_조회() throws Exception {
        // Given
        String fruitAccessToken = "fruitAccessToken";

        List<String> expected = new ArrayList<>();
        expected.add("사과");
        expected.add("배");

        //When
        when(fruitService.getFruitList(fruitAccessToken)).thenReturn(expected);

        // Then
        mockMvc.perform(get("/fruits/list").header("token", fruitAccessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.data[0]").value("사과"))
                .andExpect(jsonPath("$.data[1]").value("배"))
                .andDo(print());
    }

    @Test
    void 사과_가격_조회() throws Exception {
        // Given
        String fruitAccessToken = "fruitAccessToken";

        String name = "사과";
        Long price = 1500L;
        ProductDto expected = ProductDto.builder()
                .name(name)
                .price(price)
                .build();

        //When
        when(fruitService.getFruitPrice(fruitAccessToken, name)).thenReturn(expected);

        // Then
        mockMvc.perform(get("/fruits/price").header("token", fruitAccessToken).param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.data.price").value(1500))
                .andDo(print());
    }
}
