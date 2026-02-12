package tw.brad.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private Date orderDate;
    private List<OrderDetailDto> details;
}
