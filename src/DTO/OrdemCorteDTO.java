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
public class OrdemCorteDTO {
    private String codigo;
    private String cor;
    private String data_entrega;
    private Integer qtd_pedido;
    private Integer total_vendido;
    private Integer prioridade;
    private Integer qtd_cortado, t1, t2, t3, t4, total;

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
     * @return the data_entrega
     */
    public String getData_entrega() {
        return data_entrega;
    }

    /**
     * @param data_entrega the data_entrega to set
     */
    public void setData_entrega(String data_entrega) {
        this.data_entrega = data_entrega;
    }

    /**
     * @return the qtd_pedido
     */
    public Integer getQtd_pedido() {
        return qtd_pedido;
    }

    /**
     * @param qtd_pedido the qtd_pedido to set
     */
    public void setQtd_pedido(Integer qtd_pedido) {
        this.qtd_pedido = qtd_pedido;
    }

    /**
     * @return the total_vendido
     */
    public Integer getTotal_vendido() {
        return total_vendido;
    }

    /**
     * @param total_vendido the total_vendido to set
     */
    public void setTotal_vendido(Integer total_vendido) {
        this.total_vendido = total_vendido;
    }

    /**
     * @return the prioridade
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * @param prioridade the prioridade to set
     */
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * @return the qtd_cortado
     */
    public Integer getQtd_cortado() {
        return qtd_cortado;
    }

    /**
     * @param qtd_cortado the qtd_cortado to set
     */
    public void setQtd_cortado(Integer qtd_cortado) {
        this.qtd_cortado = qtd_cortado;
    }

    /**
     * @return the t1
     */
    public Integer getT1() {
        return t1;
    }

    /**
     * @param t1 the t1 to set
     */
    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    /**
     * @return the t2
     */
    public Integer getT2() {
        return t2;
    }

    /**
     * @param t2 the t2 to set
     */
    public void setT2(Integer t2) {
        this.t2 = t2;
    }

    /**
     * @return the t3
     */
    public Integer getT3() {
        return t3;
    }

    /**
     * @param t3 the t3 to set
     */
    public void setT3(Integer t3) {
        this.t3 = t3;
    }

    /**
     * @return the t4
     */
    public Integer getT4() {
        return t4;
    }

    /**
     * @param t4 the t4 to set
     */
    public void setT4(Integer t4) {
        this.t4 = t4;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
}
