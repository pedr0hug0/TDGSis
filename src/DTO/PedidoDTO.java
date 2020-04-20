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

public class PedidoDTO extends CorteDTO{
    
private Integer id, n_pedido, codigo_cliente, prioriadade;
private String razao, fantasia, status, data_entrega, data_cadastro, tipo_pedido;
private Date data;

//malha consumo pedidos
private String descricao, codigo;
private double consumo;
private Integer vendido;

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
     * @return the codigo_cliente
     */
    public Integer getCodigo_cliente() {
        return codigo_cliente;
    }

    /**
     * @param codigo_cliente the codigo_cliente to set
     */
    public void setCodigo_cliente(Integer codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    /**
     * @return the prioriadade
     */
    public Integer getPrioriadade() {
        return prioriadade;
    }

    /**
     * @param prioriadade the prioriadade to set
     */
    public void setPrioriadade(Integer prioriadade) {
        this.prioriadade = prioriadade;
    }

    /**
     * @return the razao
     */
    public String getRazao() {
        return razao;
    }

    /**
     * @param razao the razao to set
     */
    public void setRazao(String razao) {
        this.razao = razao;
    }

    /**
     * @return the nome_fantazia
     */

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
     * @return the fantasia
     */
    public String getFantasia() {
        return fantasia;
    }

    /**
     * @param fantasia the fantazia to set
     */
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
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
     * @return the data_cadastro
     */
    public String getData_cadastro() {
        return data_cadastro;
    }

    /**
     * @param data_cadastro the data_cadastro to set
     */
    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
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
     * @return the consumo
     */
    public Double getConsumo() {
        return consumo;
    }

    /**
     * @param consumo the consumo to set
     */
    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    /**
     * @return the vendido
     */
    public Integer getVendido() {
        return vendido;
    }

    /**
     * @param vendido the vendido to set
     */
    public void setVendido(Integer vendido) {
        this.vendido = vendido;
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

    

    
    
    
    
}
