package br.com.processo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.processo.entity.Processo;
import br.com.processo.entity.Reu;
import br.com.processo.exception.BadRequestException;
import br.com.processo.repository.ProcessoRepository;
import br.com.processo.repository.ReuRepository;

@Service
public class ProcessoService {
    private ProcessoRepository processRepository;
    private ReuRepository reuRepository;

    public ProcessoService(ProcessoRepository processRepository,
        ReuRepository reuRepository) {
        this.processRepository = processRepository;
        this.reuRepository = reuRepository;
    }

    public Page<Processo> findAll(Pageable pageable) {
        if (processRepository.findAll(pageable).isEmpty()) {
            throw new BadRequestException("Não existem processos");
        }
        return processRepository.findAll(pageable);
    }

    public List<Processo> list() {
        List<Processo> allProcessos = processRepository.findAll();
        if (allProcessos.isEmpty()) {
            throw new BadRequestException("Não existem processos");
        }
        return allProcessos;
    }

    @Transactional
    public List<Processo> create(Processo processo) {
        processRepository.findByCodigoProcesso(processo.getCodigoProcesso())
        .ifPresentOrElse((process) -> {
            throw new BadRequestException("Processo %s já cadastrado! ".formatted(processo.getCodigoProcesso()));
        }, () -> {
            processRepository.save(processo);
        });
        return list();
    }

    @Transactional
    public void update(Long id, Processo processo) {
        processRepository.findById(id).ifPresentOrElse((existingprocess) -> {
            processo.setId(id);
            processRepository.save(processo);
        }, () -> {
            throw new BadRequestException("Processo %d não existe! ".formatted(id));
        });

    }

    public Processo getById(Long id) {
        Optional<Processo> processo = processRepository.findById(id);
        if (!processo.isPresent()) {
            throw new BadRequestException("Processo %d não existe! ".formatted(id));
        }
        return processo.get();
    }

    @Transactional
    public void delete(Long id) {
        processRepository.findById(id)
        .ifPresentOrElse(
            (existingProcess) -> { 
                reuRepository.deleteAllById(existingProcess.getReus().stream().map((r) -> r.getId()).toList());
                processRepository.deleteById(id);
            }
            , () -> {
            throw new BadRequestException("Processo %d não existe! ".formatted(id));
        });
    }

    public Processo addReu(Long id, Reu reuEntity) {
        processRepository.findById(id).ifPresentOrElse((existingprocess) -> {
            saveReu(existingprocess, reuEntity);            
        }, () -> {
            throw new BadRequestException("Processo %d não existe! ".formatted(id));
        });
        
       return getById(id);
    }

    @Transactional
    private void saveReu(Processo processo, Reu reuEntity) {
        Optional<Reu> reu = processo.getReus().stream().filter((x) -> x.getCpf().equals(reuEntity.getCpf())).findFirst();
        if (reu.isPresent()) {
            throw new BadRequestException("Réu \'%s\' já adicionado ao processo %d! ".formatted(reu.get().getNome(), processo.getId()));
        }
        reuEntity.getProcessos().add(processo);
        reuRepository.save(reuEntity);
    }
}
