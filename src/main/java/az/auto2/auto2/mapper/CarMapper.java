package az.auto2.auto2.mapper;

import az.auto2.auto2.dao.entity.CarEntity;
import az.auto2.auto2.model.response.CarResponse;

public enum CarMapper {
    CAR_MAPPER;

    public CarResponse mapEntityToResponse(CarEntity carEntity) {
        return CarResponse.builder()
                .brand(carEntity.getBrand())
                .model(carEntity.getModel())
                .year(carEntity.getYear())
                .amount(carEntity.getAmount())
                .status(carEntity.getStatus())
                .build();
    }
}
