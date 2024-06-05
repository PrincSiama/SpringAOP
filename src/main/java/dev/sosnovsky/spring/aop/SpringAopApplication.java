package dev.sosnovsky.spring.aop;

import dev.sosnovsky.spring.aop.model.Car;
import dev.sosnovsky.spring.aop.model.TypeCar;
import dev.sosnovsky.spring.aop.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class SpringAopApplication {

    private final CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        carService.addCar(new Car("BMW", "M5", TypeCar.SPORT, "WBSJF01090G965232"));
        carService.addCars(List.of(
                new Car("Mercedes-Benz", "E63 AMG", TypeCar.SPORT, "WDDZF8KB6KA553726"),
                new Car("Jeep", "Wrangler", TypeCar.OFFROAD, "1C4HJXDG1LW285812"),
                new Car("Cadillac", "Deville", TypeCar.CLASSIC, "1G6KD52BSSU"))
        );

        carService.getCarByVinNumber("1G6KD52BSSU");
        carService.getCarByVinNumber("WBSJF01090G965232");
        carService.getAllCars();
    }
}
