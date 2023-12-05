package br.com.processo.mapper;

import org.springframework.stereotype.Component;

import br.com.processo.dto.ReuDto;
import br.com.processo.entity.Reu;

@Component
public class ReuMapper {
  public Reu toEntity(ReuDto dto) {
    return new Reu(dto.getId(), dto.getNome(), dto.getEndereco(), dto.getCpf());
  }

  public ReuDto toDomainObj(Reu entity) {
    return new ReuDto(entity);
  }

}
