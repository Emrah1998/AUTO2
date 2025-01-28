package az.auto2.auto2.mapper;

import az.auto2.auto2.dao.entity.CarEntity;
import az.auto2.auto2.model.response.PageableResponse;

import java.util.List;

import static az.auto2.auto2.mapper.CarMapper.CAR_MAPPER;

public enum PageableMapper {
    PAGEABLE_MAPPER;

    public PageableResponse buildPageableResponse(List<CarEntity> cars,
                                                  boolean hasNextPage,
                                                  boolean hasPreviousPage,
                                                  int lastPageNumber,
                                                  long totalElements) {
        var carList = cars.stream().map(CAR_MAPPER::mapEntityToResponse).toList();

        return PageableResponse.builder()
                .cars(carList)
                .hasNextPage(hasNextPage)
                .hasPreviousPage(hasPreviousPage)
                .lastPageNumber(lastPageNumber)
                .totalElements(totalElements)
                .build();
    }
}
