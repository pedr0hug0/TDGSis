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
public class CoresDTO {
    
    private Integer id, id_cor;
    private String cor;
    private String categoria, codigo, nome_cor;
    

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
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the id_cor
     */
    public Integer getId_cor() {
        return id_cor;
    }

    /**
     * @param id_cor the id_cor to set
     */
    public void setId_cor(Integer id_cor) {
        this.id_cor = id_cor;
    }

    

    /**
     * @return the nome_cor
     */
    public String getNome_cor() {
        return nome_cor;
    }

    /**
     * @param nome_cor the nome_cor to set
     */
    public void setNome_cor(String nome_cor) {
        this.nome_cor = nome_cor;
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
