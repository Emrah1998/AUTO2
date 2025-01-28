package az.auto2.auto2.dao.entity;

import az.auto2.auto2.model.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cars")
@EqualsAndHashCode(of = "vin")
@FieldNameConstants
public class CarEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long vin;
    private String brand;
    private String model;
    private Integer year;
    private Integer amount;
    @Enumerated(STRING)
    private CarStatus status;
    @Version //FOR OPTIMISTIC LOCKING
    private Long version; //FOR OPTIMISTIC LOCKING
}
