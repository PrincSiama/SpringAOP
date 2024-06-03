package dev.sosnovsky.SpringAOP;

import dev.sosnovsky.SpringAOP.model.Car;
import dev.sosnovsky.SpringAOP.model.TypeCar;
import dev.sosnovsky.SpringAOP.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@AllArgsConstructor
public class SpringAopApplication {

	private final CarService carService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		carService.addCar(new Car("BMW", "M5", TypeCar.SPORT));
		carService.addCars(List.of(
				new Car("Mercedes-Benz", "E63 AMG", TypeCar.SPORT),
				new Car("Jeep", "Wrangler", TypeCar.OFFROAD),
				new Car("Cadillac", "Deville", TypeCar.CLASSIC))
		);

		carService.getCarByModel("Deville");
		carService.getCarsByType(TypeCar.SPORT);
		carService.getAllCars();
	}
}
