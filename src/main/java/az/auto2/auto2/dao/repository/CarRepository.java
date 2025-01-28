package az.auto2.auto2.dao.repository;

import az.auto2.auto2.dao.entity.CarEntity;
import az.auto2.auto2.model.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity,Long>, JpaSpecificationExecutor<CarEntity> {

    Optional<CarEntity> findByVinAndStatusNot(Long vin, CarStatus status);

    //    @Lock(LockModeType.PESSIMISTIC_READ) //FOR PESSIMISTIC LOCKING
    Optional<CarEntity> findByVin(Long vin);
}
