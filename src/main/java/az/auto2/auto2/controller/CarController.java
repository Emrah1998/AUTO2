package az.auto2.auto2.controller;

import az.auto2.auto2.model.criteria.CarCriteria;
import az.auto2.auto2.model.criteria.PageCriteria;
import az.auto2.auto2.model.response.PageableResponse;
import az.auto2.auto2.model.response.UpdateAmountResponse;
import az.auto2.auto2.service.CarServiceHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/cars")
public class CarController {
    private final CarServiceHandle carService;

    @GetMapping
    public PageableResponse getCars(PageCriteria pageCriteria,
                                    CarCriteria carCriteria) {
        return carService.getCars(pageCriteria, carCriteria);
    }
    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{vin}/amount")
    public void updateCarAmount(@PathVariable Long vin,
                                @RequestBody UpdateAmountResponse updateCarAmount) {
        carService.updateCarAmount(vin, updateCarAmount);
    }


    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{vin}")
    public void SoldCar(@PathVariable Long vin) {
        carService.SoldCar(vin);
    }
}
