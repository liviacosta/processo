package br.com.processo.dto;

import br.com.processo.entity.Reu;

public class ReuDto {
    private Long id;
	private String nome;
	private String endereco;
	private String cpf;
    
    public ReuDto(Long id, String nome, String endereco, String cpf) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public ReuDto(Reu entity) {
		id = entity.getId();
		nome = entity.getNome();
		endereco = entity.getEndereco();
		cpf = entity.getCpf();
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

    
}
