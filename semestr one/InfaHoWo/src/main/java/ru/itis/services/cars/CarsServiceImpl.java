package ru.itis.services.cars;

import ru.itis.dto.CarDto;
import ru.itis.repositories.cars.CarsRepository;

import java.util.List;
import java.util.Optional;

public class CarsServiceImpl implements CarsService {

    private CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<CarDto> getAll() {
        return carsRepository.getAll();
    }

    @Override
    public CarDto getById(Integer id) {
        return carsRepository.getById(id);
    }

    @Override
    public Optional<List<CarDto>> getFitCars(String example) {
        return carsRepository.getFitCars(example);
    }
}
