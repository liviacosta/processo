package br.com.processo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reus")
public class Reu {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String endereco;
	private String cpf;
	
 	@ManyToMany(fetch = FetchType.LAZY, 
	 	cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
    @JoinTable(name = "processos_reus",
            joinColumns = {
                    @JoinColumn(name = "processo_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "reu_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Processo> processos = new HashSet<>();

    public Reu() {
    }
	
	public Reu(Long id, String nome, String endereco, String cpf) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<Processo> processos) {
		this.processos = processos;
	}

	public void removeProcesso(Processo processo) {
        this.processos.remove(processo);
        processo.getReus().remove(this);
    }

    public void addProcesso(Processo processo) {
        processos.add(processo);
        processo.getReus().add(this);
    }
	
}
