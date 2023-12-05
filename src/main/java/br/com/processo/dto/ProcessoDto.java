package br.com.processo.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.processo.entity.Processo;
import jakarta.validation.constraints.NotBlank;

public class ProcessoDto {
    private Long id;
    @NotBlank
    private String codigoProcesso;
    private String descricao;
    
    private List<ReuDto> reus = new ArrayList<>();

    public ProcessoDto(Long id, String codigoProcesso, String descricao, List<ReuDto> reus) {
        this.id = id;
        this.codigoProcesso = codigoProcesso;
        this.descricao = descricao;
        this.reus = reus;
    }

    public ProcessoDto(Processo entity) {
		id = entity.getId();
		codigoProcesso = entity.getCodigoProcesso();
        descricao = entity.getDescricao();
        this.reus = entity.getReus().stream().map((r) -> new ReuDto(r)).toList();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProcesso() {
        return codigoProcesso;
    }

    public void setCodigoProcesso(String codigoProcesso) {
        this.codigoProcesso = codigoProcesso;
    }

    public List<ReuDto> getReus() {
        return reus;
    }

    public void setReus(List<ReuDto> reus) {
        this.reus = reus;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
