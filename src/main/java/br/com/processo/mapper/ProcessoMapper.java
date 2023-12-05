package br.com.processo.mapper;

import org.springframework.stereotype.Component;

import br.com.processo.dto.ProcessoDto;
import br.com.processo.entity.Processo;

@Component
public class ProcessoMapper {
    public Processo toEntity(ProcessoDto processoDomainObj) {
        return new Processo(processoDomainObj.getId(), processoDomainObj.getCodigoProcesso(), processoDomainObj.getDescricao());
    }

    public ProcessoDto toDomainObj(Processo userEntity) {
        return new ProcessoDto(userEntity);
    }
    
}
