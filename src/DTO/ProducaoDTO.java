/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author PeDr0_HuG0
 */
public class ProducaoDTO {
    
    private String codigo, cor, processo, status, frente, costas, manga, data_alteracao_str, data_inicio, corte, tipo_pedido, etiqueta, perdeu;
    private Integer id, qtd, qtd_montado, perda;
    private Date data_alteracao, data_finalizado;
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the processo
     */
    public String getProcesso() {
        return processo;
    }

    /**
     * @param processo the processo to set
     */
    public void setProcesso(String processo) {
        this.processo = processo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the data_alteracao
     */
    public Date getData_alteracao() {
        return data_alteracao;
    }

    /**
     * @param data_alteracao the data_alteracao to set
     */
    public void setData_alteracao(Date data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    /**
     * @return the frente
     */
    public String getFrente() {
        return frente;
    }

    /**
     * @param frente the frente to set
     */
    public void setFrente(String frente) {
        this.frente = frente;
    }

    /**
     * @return the costas
     */
    public String getCostas() {
        return costas;
    }

    /**
     * @param costas the costas to set
     */
    public void setCostas(String costas) {
        this.costas = costas;
    }

    /**
     * @return the manga
     */
    public String getManga() {
        return manga;
    }

    /**
     * @param manga the manga to set
     */
    public void setManga(String manga) {
        this.manga = manga;
    }

    /**
     * @return the data_alteracao_str
     */
    public String getData_alteracao_str() {
        return data_alteracao_str;
    }

    /**
     * @param data_alteracao_str the data_alteracao_str to set
     */
    public void setData_alteracao_str(String data_alteracao_str) {
        this.data_alteracao_str = data_alteracao_str;
    }

    /**
     * @return the data_inicio
     */
    public String getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the corte
     */
    public String getCorte() {
        return corte;
    }

    /**
     * @param corte the corte to set
     */
    public void setCorte(String corte) {
        this.corte = corte;
    }

    /**
     * @return the qtd
     */
    public Integer getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the tipo_pedido
     */
    public String getTipo_pedido() {
        return tipo_pedido;
    }

    /**
     * @param tipo_pedido the tipo_pedido to set
     */
    public void setTipo_pedido(String tipo_pedido) {
        this.tipo_pedido = tipo_pedido;
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the qtd_montado
     */
    public Integer getQtd_montado() {
        return qtd_montado;
    }

    /**
     * @param qtd_montado the qtd_montado to set
     */
    public void setQtd_montado(Integer qtd_montado) {
        this.qtd_montado = qtd_montado;
    }

    /**
     * @return the data_finalizado
     */
    public Date getData_finalizado() {
        return data_finalizado;
    }

    /**
     * @param data_finalizado the data_finalizado to set
     */
    public void setData_finalizado(Date data_finalizado) {
        this.data_finalizado = data_finalizado;
    }

   
    /**
     * @return the perdeu
     */
    public String getPerdeu() {
        return perdeu;
    }

    /**
     * @param perdeu the perdeu to set
     */
    public void setPerdeu(String perdeu) {
        this.perdeu = perdeu;
    }

    /**
     * @return the perda
     */
    public Integer getPerda() {
        return perda;
    }

    /**
     * @param perda the perda to set
     */
    public void setPerda(Integer perda) {
        this.perda = perda;
    }

  
}
