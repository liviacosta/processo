package br.com.processo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;

@Entity
@Table(name = "processos")
public class Processo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigoProcesso;
    private String descricao;
    
	@ManyToMany(mappedBy = "processos", 
        fetch = FetchType.LAZY, 
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})    
    private Set<Reu> reus = new HashSet<>();

    public Processo() {
    }

    public Processo(Long id, String codigoProcesso, String descricao) {
        this.id = id;
        this.codigoProcesso = codigoProcesso;
        this.descricao = descricao;
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

    public Set<Reu> getReus() {
        return reus;
    }

    public void setReus(Set<Reu> reus) {
        this.reus = reus;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @PreRemove
    private void removeReuAssociations() {
        for (Reu reu : this.reus) {
            reu.getProcessos().remove(this);
        }
    }

}
