package com.example.arleson.myactivities;

/**
 * Created by arleson on 01/06/2018.
 */

public class Atividade {

    int id;
    String descricao;
    String tipo;
    String organizacaoId;
    String pessoaId;
    String negocioId;
    String dataAtividade;
    String hora;

    public Atividade() {

    }

    public Atividade(String descricao, String tipo, String organizacaoId, String pessoaId, String negocioId, String dataAtividade, String hora) {
        this.descricao = descricao;
        this.organizacaoId = organizacaoId;
        this.pessoaId = pessoaId;
        this.negocioId = negocioId;
        this.dataAtividade = dataAtividade;
        this.hora = hora;
    }

    public Atividade(int id, String descricao, String tipo, String organizacaoId, String pessoaId, String negocioId, String dataAtividade, String hora) {
        this.id = id;
        this.descricao = descricao;
        this.organizacaoId = organizacaoId;
        this.pessoaId = pessoaId;
        this.negocioId = negocioId;
        this.dataAtividade = dataAtividade;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrganizacaoId() {
        return organizacaoId;
    }

    public void setOrganizacaoId(String organizacaoId) {
        this.organizacaoId = organizacaoId;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNegocioId() {
        return negocioId;
    }

    public void setNegocioId(String negocioId) {
        this.negocioId = negocioId;
    }

    public String getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(String dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
