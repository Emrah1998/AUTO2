package az.auto2.auto2.service;

import az.auto2.auto2.dao.entity.CarEntity;
import az.auto2.auto2.dao.repository.CarRepository;
import az.auto2.auto2.mapper.PageableMapper;
import az.auto2.auto2.model.criteria.CarCriteria;
import az.auto2.auto2.model.criteria.PageCriteria;
import az.auto2.auto2.model.enums.CarStatus;
import az.auto2.auto2.model.response.PageableResponse;
import az.auto2.auto2.model.response.UpdateAmountResponse;
import az.auto2.auto2.service.specification.CarSpecification;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static az.auto2.auto2.dao.entity.CarEntity.Fields.vin;
import static az.auto2.auto2.mapper.PageableMapper.PAGEABLE_MAPPER;
import static az.auto2.auto2.model.enums.CarStatus.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceHandle implements CarService {
    private final CarRepository carRepository;

    @Override
    public PageableResponse getCars(PageCriteria pageCriteria, CarCriteria carCriteria) {
        var pageRequest = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by(vin).descending());
        var specification = new CarSpecification(carCriteria);
        var carPage = carRepository.findAll(specification, pageRequest);
        return PAGEABLE_MAPPER.buildPageableResponse(carPage.getContent(), carPage.hasNext(), carPage.hasPrevious(),
                carPage.getTotalPages(), carPage.getTotalElements());
    }

    @Override
    public void updateCarAmount(Long vin, UpdateAmountResponse amount) {

        var cars = fetchCarIfExist(vin);
        cars.setAmount(amount.getAmount());
        carRepository.save(cars);
    }

    @Override
    public void SoldCar(Long vin) {
        var car = fetchCarIfExist(vin);
        car.setStatus(INACTIVE);
        carRepository.save(car);

    }

    private CarEntity fetchCarIfExist(Long vin) {
        return carRepository.findByVinAndStatusNot(vin, INACTIVE)
                .orElseThrow(RuntimeException::new);
    }
    //FOR OPTIMISTIC LOCKING
    @PostConstruct
    public void updateCar(){
        var car1 = carRepository.findByVin(10000000000000000L).get();
        var car2 = carRepository.findByVin(10000000000000000L).get();
        car1.setAmount(16_000);
        carRepository.save(car1);
        try{
            car2.setAmount(17_000);
            carRepository.save(car2);
        } catch (Exception e){
            log.error("Error: {}",e);
        }
    }

    //FOR PESSIMISTIC LOCKING
//    @PostConstruct
//    public void updateCar2(){
//        var car1 = carRepository.findByVin(1000000000000000000L).get();
//    }
}
