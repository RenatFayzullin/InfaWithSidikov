package ru.itis.repositories.cars;

import ru.itis.dto.CarDto;
import ru.itis.models.Car;
import ru.itis.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarsRepository extends CrudRepository<Car> {
    List<CarDto> getAll();
    CarDto getById(Integer id);
    Optional<List<CarDto>> getFitCars(String example);
}
