package az.auto2.auto2.model.response;

import az.auto2.auto2.model.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponse {
    private String brand;
    private String model;
    private Integer year;
    private Integer amount;
    private CarStatus status;
}
