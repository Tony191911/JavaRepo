package tw.brad.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tw.brad.spring3.entity.Order;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomerDto {
    private String customerId;
    private String companyName;
    private List<OrderDto> orders;
}
