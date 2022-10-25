package com.michal.test3.car;

import com.michal.test3.car.model.Car;
import com.michal.test3.parking.model.Parking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldReturnAllCarsFromRepository() {

        Car car1 = Car.builder()
                .id(1)
                .brand("BMW")
                .model("3")
                .build();

        Car car2 = Car.builder()
                .id(1)
                .brand("BMW")
                .model("5")
                .build();

        List<Car> carsFromRepo = List.of(car1, car2);
        when(carRepository.findAll()).thenReturn(carsFromRepo);

        List<Car> foundCars = carService.findAll();
        assertEquals(carsFromRepo, foundCars);

    }

    @Test
    void shouldReturnEntityWhenParkingFoundById() {
        int carId = 1;
        Car car = Car.builder()
                .id(1)
                .brand("BMW")
                .model("3")
                .build();

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        Car carFound = carService.findById(carId);

        assertEquals(car.getId(), carFound.getId());
        assertEquals(car.getBrand(), carFound.getBrand());
        assertEquals(car.getModel(), carFound.getModel());

    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenStudentNotFoundById() {

        int carId = 1;
        String excMsg = "Parking id=" + carId + " not found";
        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class, () -> carService.findById(carId));
        assertEquals(excMsg, exception.getMessage());
        verify(carRepository).findById(carId);

    }

    @Test
    void save() {
        Car car = Car.builder()
                .id(1)
                .brand("BMW")
                .model("3")
                .build();

        when(carRepository.save(car)).thenReturn(car);

        Car savedCar = carService.save(car);

        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getBrand(), savedCar.getBrand());
        assertEquals(car.getModel(), savedCar.getModel());
    }


}