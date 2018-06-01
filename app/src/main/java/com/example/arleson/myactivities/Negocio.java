package com.example.arleson.myactivities;

/**
 * Created by arleson on 01/06/2018.
 */

public class Negocio {

    int id;
    String titulo;
    String descricao;
    String organizacaoId;
    String pessoaId;
    String valor;
    String dtEncerramento;
    String estado;

    public Negocio() {

    }

    public Negocio(String titulo, String descricao, String organizacaoId, String pessoaId, String valor, String dtEncerramento, String estado) {this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.organizacaoId = organizacaoId;
        this.pessoaId = pessoaId;
        this.valor = valor;
        this.dtEncerramento = dtEncerramento;
        this.estado = estado;
    }

    public Negocio(int id, String titulo, String descricao, String organizacaoId, String pessoaId, String valor, String dtEncerramento, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.organizacaoId = organizacaoId;
        this.pessoaId = pessoaId;
        this.valor = valor;
        this.dtEncerramento = dtEncerramento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDtEncerramento() {
        return dtEncerramento;
    }

    public void setDtEncerramento(String dtEncerramento) {
        this.dtEncerramento = dtEncerramento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
