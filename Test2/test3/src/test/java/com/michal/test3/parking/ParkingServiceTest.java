package com.michal.test3.parking;

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
class ParkingServiceTest {

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingService parkingService;

    @Test
    void shouldReturnAllParkingsFromRepository() {

        Parking parking1 = Parking.builder()
                .id(1)
                .name("Parking")
                .city("Warszawa")
                .capacity(4)
                .build();

        Parking parking2 = Parking.builder()
                .id(2)
                .name("Parking")
                .city("Gdańsk")
                .capacity(2)
                .build();

        List<Parking> parkingsFromRepo = List.of(parking1, parking2);
        when(parkingRepository.findAll()).thenReturn(parkingsFromRepo);

        List<Parking> foundParkings = parkingService.findAll();
        assertEquals(parkingsFromRepo, foundParkings);

    }

    @Test
    void shouldReturnEntityWhenParkingFoundById() {
        int parkingId = 1;
        Parking parking = Parking.builder()
                .id(1)
                .name("Parking")
                .city("Warszawa")
                .capacity(4)
                .build();

        when(parkingRepository.findById(parkingId)).thenReturn(Optional.of(parking));
        Parking parkingFound = parkingService.findById(parkingId);

        assertEquals(parking.getId(), parkingFound.getId());
        assertEquals(parking.getName(), parkingFound.getName());
        assertEquals(parking.getCity(), parkingFound.getCity());
        assertEquals(parking.getCapacity(), parkingFound.getCapacity());

    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenStudentNotFoundById() {

        int parkingId = 1;
        String excMsg = "Parking id=" + parkingId + " not found";
        when(parkingRepository.findById(parkingId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class, () -> parkingService.findById(parkingId));
        assertEquals(excMsg, exception.getMessage());
        verify(parkingRepository).findById(parkingId);

    }

    @Test
    void save() {
        Parking parking = Parking.builder()
                .id(1)
                .name("Parking")
                .city("Warszawa")
                .capacity(4)
                .build();

        when(parkingRepository.save(parking)).thenReturn(parking);

        Parking savedParking = parkingService.save(parking);

        assertEquals(parking.getId(), savedParking.getId());
        assertEquals(parking.getName(), savedParking.getName());
        assertEquals(parking.getCity(), savedParking.getCity());
        assertEquals(parking.getCapacity(), savedParking.getCapacity());
    }
}