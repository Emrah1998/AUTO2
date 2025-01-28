package az.auto2.auto2.service;

import az.auto2.auto2.model.criteria.CarCriteria;
import az.auto2.auto2.model.criteria.PageCriteria;
import az.auto2.auto2.model.response.PageableResponse;
import az.auto2.auto2.model.response.UpdateAmountResponse;

public interface CarService {
    PageableResponse getCars(PageCriteria pageCriteria, CarCriteria criteria);
    void updateCarAmount (Long vin, UpdateAmountResponse amount);
    void SoldCar (Long vin);
}
