package ru.itis.services.addCar;

import ru.itis.dto.AddCarDto;
import ru.itis.models.Car;
import ru.itis.repositories.cars.CarsRepository;

public class AddCarServiceImpl implements AddCarService{

    private CarsRepository carsRepository;

    public AddCarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public void addCar(AddCarDto addCarDto) {
        Car car = Car.builder()
                .title(addCarDto.getTitle())
                .model(addCarDto.getModel())
                .description(addCarDto.getDescription())
                .price(addCarDto.getPrice())
                .country(addCarDto.getCountry())
                .build();

        carsRepository.save(car);
    }
}
