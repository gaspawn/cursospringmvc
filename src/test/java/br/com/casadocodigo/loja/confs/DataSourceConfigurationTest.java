package br.com.casadocodigo.loja.confs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_teste");
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUsername("fagasparelo");
		dataSource.setPassword("");
		return dataSource;
	}
}
