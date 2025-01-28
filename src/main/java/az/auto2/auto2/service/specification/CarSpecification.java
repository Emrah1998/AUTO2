package az.auto2.auto2.service.specification;

import az.auto2.auto2.dao.entity.CarEntity;
import az.auto2.auto2.model.criteria.CarCriteria;
import az.auto2.auto2.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
public class CarSpecification implements Specification<CarEntity> {

    private final CarCriteria carCriteria;

    @Override
    public Predicate toPredicate(Root<CarEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {

        var predicates = PredicateUtil.builder()
                .addNullSafety(carCriteria.getBrand(),
                        name->criteriaBuilder.like(root.get(CarEntity.Fields.brand),
                                SearchCarsByLike(name)))
                .addNullSafety(carCriteria.getModel(),
                        name2->criteriaBuilder.like(root.get(CarEntity.Fields.model),
                                SearchCarsByLike(name2)))
                .addNullSafety(carCriteria.getYearFrom(),
                        yearFrom->criteriaBuilder.greaterThanOrEqualTo(
                                root.get(CarEntity.Fields.year),yearFrom))
                .addNullSafety(carCriteria.getYearTo(),
                        yearTo->criteriaBuilder.lessThanOrEqualTo(
                                root.get(CarEntity.Fields.year),yearTo))
                .addNullSafety(carCriteria.getAmountFrom(),
                        amountFrom->criteriaBuilder.greaterThanOrEqualTo(
                                root.get(CarEntity.Fields.amount),amountFrom))
                .addNullSafety(carCriteria.getAmountTo(),
                        amountTo->criteriaBuilder.lessThanOrEqualTo(
                                root.get(CarEntity.Fields.amount),amountTo))
                .build();

        return criteriaBuilder.and(predicates);

    }
    private String SearchCarsByLike(String keyword) {
        return "%" + keyword + "%";
    }
}
