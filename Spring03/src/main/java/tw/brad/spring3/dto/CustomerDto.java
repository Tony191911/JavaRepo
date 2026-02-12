package tw.brad.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
/*
@Getter
@AllArgsConstructor
public class CustomerDto {
    private String customerId;
    private String companyName;
    private List<OrderDto> orders;
}
*/

public record CustomerDto(String customerId, String companyName, List<OrderDto> orders) {
}