package ru.itis.services.cars;

import ru.itis.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarsService {
    List<CarDto> getAll();
    CarDto getById(Integer id);
    Optional<List<CarDto>> getFitCars(String example);
}
