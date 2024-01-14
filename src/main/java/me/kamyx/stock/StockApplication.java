package me.kamyx.stock;

import me.kamyx.stock.model.Stock;
import me.kamyx.stock.repository.StockRepository;
import me.kamyx.stock.service.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(StockRepository repository, StockService service) {
		return args -> {
			List<Stock> stocks = new ArrayList<>();

			Stock stock1 = new Stock();
			stocks = service.allStocks();
			System.out.println(service.allStocks());

			//System.out.println(repository.getCurrentPrice("AAPL"));
			System.out.println(service.singleStock("AAPL"));
			//service.addPrice("AAPL", 100.00);
			//System.out.println(service.singleStock("AAPL"));
			//System.out.println(service.getPriceHistory("AAPL"));
			System.out.println(service.getPriceHistory("AAPL"));
			//System.out.println(service.updatePrice("AAPL",1,1,1));

//			System.out.println(service.updatePrice("AAPL"));
//			System.out.println(service.updatePrice("AAPL"));
//			System.out.println(service.updatePrice("AAPL"));

			System.out.println(service.updatePrice("AAPL"));
			service.allStocksUpdatePrice();




		};
	}

}
