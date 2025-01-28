package az.auto2.auto2.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCriteria {
    private String brand;
    private String model;
    private Integer yearFrom;
    private Integer yearTo;
    private Integer amountFrom;
    private Integer amountTo;
}
