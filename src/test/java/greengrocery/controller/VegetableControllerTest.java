package greengrocery.controller;

import greengrocery.domain.dto.ProductDto;
import greengrocery.domain.dto.TokenDto;
import greengrocery.service.VegetableService;
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
public class VegetableControllerTest {
    @MockBean
    private VegetableService vegetableService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 채소_토큰_가져오기() throws Exception {
        // Given
        TokenDto expected = new TokenDto("vegetableAccessToken");

        //When
        when(vegetableService.getToken()).thenReturn(expected);

        // Then
        mockMvc.perform(get("/vegetables/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").value("vegetableAccessToken"))
                .andDo(print());
    }

    @Test
    void 채소_전체_목록_조회() throws Exception {
        // Given
        String fruitAccessToken = "vegetableAccessToken";

        List<String> expected = new ArrayList<>();
        expected.add("치커리");
        expected.add("토마토");
        expected.add("깻잎");
        expected.add("상추");

        //When
        when(vegetableService.getVegetableList(fruitAccessToken)).thenReturn(expected);

        // Then
        mockMvc.perform(get("/vegetables/list").header("token", fruitAccessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.data[0]").value("치커리"))
                .andExpect(jsonPath("$.data[1]").value("토마토"))
                .andExpect(jsonPath("$.data[2]").value("깻잎"))
                .andExpect(jsonPath("$.data[3]").value("상추"))
                .andDo(print());
    }

    @Test
    void 치커리_가격_조회() throws Exception {
        // Given
        String fruitAccessToken = "vegetableAccessToken";

        String name = "치커리";
        Long price = 1600L;
        ProductDto expected = ProductDto.builder()
                .name(name)
                .price(price)
                .build();

        //When
        when(vegetableService.getVegetablePrice(fruitAccessToken, name)).thenReturn(expected);

        // Then
        mockMvc.perform(get("/vegetables/price").header("token", fruitAccessToken).param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.data.price").value(1600))
                .andDo(print());
    }
}
