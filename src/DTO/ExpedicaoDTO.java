/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author PeDr0_HuG0
 */
public class ExpedicaoDTO {
    
     
  private Integer id;
  private Integer n_pedido, total_t1, total_t2, total_t3, total_t4, total_ref;
  private String codigo, descricao, cor, status, data_saida;  

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
     * @return the n_pedido
     */
    public Integer getN_pedido() {
        return n_pedido;
    }

    /**
     * @param n_pedido the n_pedido to set
     */
    public void setN_pedido(Integer n_pedido) {
        this.n_pedido = n_pedido;
    }

    /**
     * @return the total_t1
     */
    public Integer getTotal_t1() {
        return total_t1;
    }

    /**
     * @param total_t1 the total_t1 to set
     */
    public void setTotal_t1(Integer total_t1) {
        this.total_t1 = total_t1;
    }

    /**
     * @return the total_t2
     */
    public Integer getTotal_t2() {
        return total_t2;
    }

    /**
     * @param total_t2 the total_t2 to set
     */
    public void setTotal_t2(Integer total_t2) {
        this.total_t2 = total_t2;
    }

    /**
     * @return the total_t3
     */
    public Integer getTotal_t3() {
        return total_t3;
    }

    /**
     * @param total_t3 the total_t3 to set
     */
    public void setTotal_t3(Integer total_t3) {
        this.total_t3 = total_t3;
    }

    /**
     * @return the total_t4
     */
    public Integer getTotal_t4() {
        return total_t4;
    }

    /**
     * @param total_t4 the total_t4 to set
     */
    public void setTotal_t4(Integer total_t4) {
        this.total_t4 = total_t4;
    }

    /**
     * @return the total_ref
     */
    public Integer getTotal_ref() {
        return total_ref;
    }

    /**
     * @param total_ref the total_ref to set
     */
    public void setTotal_ref(Integer total_ref) {
        this.total_ref = total_ref;
    }

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the data_saida
     */
    public String getData_saida() {
        return data_saida;
    }

    /**
     * @param data_saida the data_saida to set
     */
    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }
    
}
