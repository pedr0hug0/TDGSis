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
public class CorteDTO {
    
    private String data_corte;
    private String codigo;
    private String cor;
    private String corte, tipo_pedido, programado;
    private Integer id;
    private Integer qtd;
    private Integer total;
    private Integer t1, t2, t3, t4;
    private Double consumo;
    private String infesto;
    private String grade;


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
     * @return the data_corte
     */
    public String getData_corte() {
        return data_corte;
    }

    /**
     * @param data_corte the data_corte to set
     */
    public void setData_corte(String data_corte) {
        this.data_corte = data_corte;
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
     * @return the programado
     */
    public String getProgramado() {
        return programado;
    }

    /**
     * @param programado the programado to set
     */
    public void setProgramado(String programado) {
        this.programado = programado;
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
     * @return the infesto
     */
    public String getInfesto() {
        return infesto;
    }

    /**
     * @param infesto the infesto to set
     */
    public void setInfesto(String infesto) {
        this.infesto = infesto;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}
