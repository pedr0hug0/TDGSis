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
public class ItemPedidoDTO{
    
  private Integer id;
  private Integer n_pedido, n_pedido_original, n_pedido_novo;
  private String codigo; //tambem produto ou não
  private Integer qtd_total;
  private String cor;
  private Integer t1;
  private Integer t2;
  private Integer t3;
  private Integer t4;
  private String cor_original;
  private Integer total;
  private String loja, loja_original, loja_novo;
  
  //produto
  
  
  private Integer qtd_cores;
  private Integer qtd_tamanho;
  private String cor1;
  private String cor2;
  private String cor3;
  private String tipo_tamanho;

    

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
     * @return the qtd_total
     */
    public Integer getQtd_total() {
        return qtd_total;
    }

    /**
     * @param qtd_total the qtd_total to set
     */
    public void setQtd_total(Integer qtd_total) {
        this.qtd_total = qtd_total;
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
     * @return the qtd_cores
     */
    public Integer getQtd_cores() {
        return qtd_cores;
    }

    /**
     * @param qtd_cores the qtd_cores to set
     */
    public void setQtd_cores(Integer qtd_cores) {
        this.qtd_cores = qtd_cores;
    }

    /**
     * @return the qtd_tamanho
     */
    public Integer getQtd_tamanho() {
        return qtd_tamanho;
    }

    /**
     * @param qtd_tamanho the qtd_tamanho to set
     */
    public void setQtd_tamanho(Integer qtd_tamanho) {
        this.qtd_tamanho = qtd_tamanho;
    }

    /**
     * @return the cor1
     */
    public String getCor1() {
        return cor1;
    }

    /**
     * @param cor1 the cor1 to set
     */
    public void setCor1(String cor1) {
        this.cor1 = cor1;
    }

    /**
     * @return the cor2
     */
    public String getCor2() {
        return cor2;
    }

    /**
     * @param cor2 the cor2 to set
     */
    public void setCor2(String cor2) {
        this.cor2 = cor2;
    }

    /**
     * @return the cor3
     */
    public String getCor3() {
        return cor3;
    }

    /**
     * @param cor3 the cor3 to set
     */
    public void setCor3(String cor3) {
        this.cor3 = cor3;
    }

    /**
     * @return the tipo_tamanho
     */
    public String getTipo_tamanho() {
        return tipo_tamanho;
    }

    /**
     * @param tipo_tamanho the tipo_tamanho to set
     */
    public void setTipo_tamanho(String tipo_tamanho) {
        this.tipo_tamanho = tipo_tamanho;
    }

    /**
     * @return the cor_original
     */
    public String getCor_original() {
        return cor_original;
    }

    /**
     * @param cor_original the cor_original to set
     */
    public void setCor_original(String cor_original) {
        this.cor_original = cor_original;
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
     * @return the loja
     */
    public String getLoja() {
        return loja;
    }

    /**
     * @param loja the loja to set
     */
    public void setLoja(String loja) {
        this.loja = loja;
    }

    /**
     * @return the n_pedido_original
     */
    public Integer getN_pedido_original() {
        return n_pedido_original;
    }

    /**
     * @param n_pedido_original the n_pedido_original to set
     */
    public void setN_pedido_original(Integer n_pedido_original) {
        this.n_pedido_original = n_pedido_original;
    }

    /**
     * @return the n_pedido_novo
     */
    public Integer getN_pedido_novo() {
        return n_pedido_novo;
    }

    /**
     * @param n_pedido_novo the n_pedido_novo to set
     */
    public void setN_pedido_novo(Integer n_pedido_novo) {
        this.n_pedido_novo = n_pedido_novo;
    }

    /**
     * @return the loja_original
     */
    public String getLoja_original() {
        return loja_original;
    }

    /**
     * @param loja_original the loja_original to set
     */
    public void setLoja_original(String loja_original) {
        this.loja_original = loja_original;
    }

    /**
     * @return the loja_novo
     */
    public String getLoja_novo() {
        return loja_novo;
    }

    /**
     * @param loja_novo the loja_novo to set
     */
    public void setLoja_novo(String loja_novo) {
        this.loja_novo = loja_novo;
    }
    
}
