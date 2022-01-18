package br.com.luizlmc.DashboardFinanceiro;

import br.com.luizlmc.DashboardFinanceiro.config.property.DashboardFinanceiroApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DashboardFinanceiroApiProperty.class)
public class DashboardFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardFinanceiroApplication.class, args);
	}

}
