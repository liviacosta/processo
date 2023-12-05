package br.com.processo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo.dto.ProcessoDto;
import br.com.processo.dto.ReuDto;
import br.com.processo.entity.Processo;
import br.com.processo.entity.Reu;
import br.com.processo.mapper.ProcessoMapper;
import br.com.processo.mapper.ReuMapper;
import br.com.processo.service.ProcessoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/processos")
public class ProcessController {
    private final ProcessoService processService;
    private final ProcessoMapper processoMapper = new ProcessoMapper();
    private final ReuMapper reuMapper = new ReuMapper();
  
    public ProcessController(ProcessoService processService, 
        ProcessoMapper processoMapper,
        ReuMapper reuMapper) {
        this.processService = processService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<List<ProcessoDto>> create(@Valid @RequestBody ProcessoDto processoDto) {
        Processo processoEntity = processoMapper.toEntity(processoDto);
        List<ProcessoDto> listProcessos = processService.create(processoEntity).stream().map(p -> processoMapper.toDomainObj(p)).toList();
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(listProcessos);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProcessoDto> list() {
        List<ProcessoDto> listProcessos = processService.list().stream().map(p -> processoMapper.toDomainObj(p)).toList();
        return listProcessos;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProcessoDto update(@PathVariable Long id, @RequestBody ProcessoDto processoDto) {
        Processo processoEntity = this.processoMapper.toEntity(processoDto);
        processService.update(id, processoEntity);
        ProcessoDto processoUpdated = processoMapper.toDomainObj(processService.getById(id));
        return processoUpdated;
    }

    @PutMapping("{id}/add-reu")
    ProcessoDto addReu(@PathVariable Long id, @RequestBody ReuDto reuDto) {
        Reu reuEntity = reuMapper.toEntity(reuDto);        
        ProcessoDto processoUpdated = processoMapper.toDomainObj(processService.addReu(id, reuEntity));
        return processoUpdated;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long id) {
        processService.delete(id);
    }
}