package greengrocery.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Long price;

    @Builder
    ProductDto(String name, Long price) {
        this.name = name;
        this.price = price;
    }

}
