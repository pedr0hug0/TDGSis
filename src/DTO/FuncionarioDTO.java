/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import org.joda.time.DateTime;

/**
 *
 * @author pedr0
 */
public class FuncionarioDTO {
    
    private String nome;
    
    private String cargo;
    
    private String area;
  
    private String tipo;
   
    private String motivo;
   
    private String assinado;
    private Integer id;
    private String data_evento;
    private Date data_evento_data;
   private String horas;
    private String total_entrada;
    private String total_saida;
    private String saldo;
    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the assinado
     */
    public String getAssinado() {
        return assinado;
    }

    /**
     * @param assinado the assinado to set
     */
    public void setAssinado(String assinado) {
        this.assinado = assinado;
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
     * @return the data_evento
     */
    public String getData_evento() {
        return data_evento;
    }

    /**
     * @param data_evento the data_evento to set
     */
    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }

    /**
     * @return the horas
     */
    public String getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(String horas) {
        this.horas = horas;
    }

    /**
     * @return the total_entrada
     */
    public String getTotal_entrada() {
        return total_entrada;
    }

    /**
     * @param total_entrada the total_entrada to set
     */
    public void setTotal_entrada(String total_entrada) {
        this.total_entrada = total_entrada;
    }

    /**
     * @return the total_saida
     */
    public String getTotal_saida() {
        return total_saida;
    }

    /**
     * @param total_saida the total_saida to set
     */
    public void setTotal_saida(String total_saida) {
        this.total_saida = total_saida;
    }

    /**
     * @return the saldo
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the data_evento_data
     */
    public Date getData_evento_data() {
        return data_evento_data;
    }

    /**
     * @param data_evento_data the data_evento_data to set
     */
    public void setData_evento_data(Date data_evento_data) {
        this.data_evento_data = data_evento_data;
    }
    
    
}
