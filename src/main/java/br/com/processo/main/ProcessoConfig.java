package br.com.processo.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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