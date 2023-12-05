package br.com.processo.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.core.env.Environment;

import br.com.processo.mapper.ProcessoMapper;
import br.com.processo.mapper.ReuMapper;

@Configuration
public class ProcessoConfig {

  @Bean
  ProcessoMapper processoMapper() {
    return new ProcessoMapper();
  }
  
  @Bean
  ReuMapper reuMapper() {
    return new ReuMapper();
  }
}