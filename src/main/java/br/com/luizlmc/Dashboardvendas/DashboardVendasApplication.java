package br.com.luizlmc.Dashboardvendas;

import br.com.luizlmc.Dashboardvendas.config.property.DashboardVendasApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DashboardVendasApiProperty.class)
public class DashboardVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardVendasApplication.class, args);
	}

}
