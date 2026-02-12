package tw.brad.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderDetailDto {
    private BigDecimal price;
    private Integer qty;
    private String productName;
}
