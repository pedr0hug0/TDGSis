/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.ItemPedidoDTO;
import DTO.ProdutoDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */

    
public class ItemPedidoDAO {
    
    /*
    Integer id_item_pedido;
    Integer n_pedido;
    String codigo;
    Integer qtd_total;
    String cor;
    Integer t1;
    Integer t2;
    Integer t3;
    Integer t4;
    String cor_original;
    Integer total;
  
  
  //produto
  
  
    Integer qtd_cores;
    Integer qtd_tamanho;
    String cor1;
    String cor2;
    String cor3;
    String tipo_tamanho;
    
    
    */
    
    //pesquisar ref
        public List<ProdutoDTO> getProdutosRef(String ref) {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    String sql = "SELECT * FROM produtos where codigo = '"+ref+"' order by codigo";
                    rs = stmt.executeQuery(sql.toUpperCase());
                    
                    System.out.println(sql.toUpperCase());
                    
                    
                    
                
                
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
                                produto.setDescricao(rs.getString("descricao"));
				produto.setQtd_cores(rs.getInt("qtd_cores"));
                                produto.setQtd_tamanho(rs.getInt("qtd_tamanho"));
                                //produto.setCor1(rs.getString("cor1"));
				//produto.setCor2(rs.getString("cor2"));
                                //produto.setCor3(rs.getString("cor3"));
                                produto.setBordado(rs.getInt("bordado"));
                                produto.setEstampa(rs.getInt("estampa"));
                                produto.setBolso(rs.getInt("bolso"));
                                produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
				produtos.add(produto);
                                                            
			}
                          
			Conexao.CloseDB();
		
                        
                } catch (Exception e) {
			System.out.println("Erro ao listar produto por ref");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim pesquisar por ref
    

//adicionar Item no Item Pedido
public boolean adicionaItemPedido(ItemPedidoDTO itempedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                                    
                    String comando = "insert into item_pedido (n_pedido, codigo, cor, t1, t2, t3, t4, cor_original, total, loja) values " +
					"("+				    		
				    "'" + itempedidoDTO.getN_pedido()+ "'," +
				    "'" + itempedidoDTO.getCodigo()+ "'," +
				    "'" + itempedidoDTO.getCor()+ "'," +
				    "'" + itempedidoDTO.getT1()+ "'," +
				    "'" + itempedidoDTO.getT2()+ "'," +
                                    "'" + itempedidoDTO.getT3()+ "'," +
                                    "'" + itempedidoDTO.getT4()+ "'," +
                                    "'" + itempedidoDTO.getCor_original()+ "'," +
                                    "'" + itempedidoDTO.getTotal()+ "'," +
                                    "'" + itempedidoDTO.getLoja()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('item_pedido_id_seq')");
                        while (rs.next()) {
                        itempedidoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar item pedido");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona




        //listar item pedido de acordo com n_pedido
            public List<ItemPedidoDTO> getItemPedido(String n_pedido) {
                ArrayList<ItemPedidoDTO> itempedidos = new ArrayList<ItemPedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM item_pedido where n_pedido = "+n_pedido+" order by id");
                    
                    System.out.println("SELECT * FROM item_pedido where n_pedido = "+n_pedido+" order by id");
		
                        while (rs.next()) {
				ItemPedidoDTO itempedido = new ItemPedidoDTO();
                                  
				itempedido.setId(rs.getInt("id"));
                                itempedido.setN_pedido(rs.getInt("n_pedido"));
				itempedido.setCodigo(rs.getString("codigo"));
                                itempedido.setCor(rs.getString("cor"));
				itempedido.setT1(rs.getInt("t1"));
                                itempedido.setT2(rs.getInt("t2"));
                                itempedido.setT3(rs.getInt("t3"));
                                itempedido.setT4(rs.getInt("t4"));
                                itempedido.setCor_original(rs.getString("cor_original"));
                                itempedido.setTotal(rs.getInt("total"));
                                itempedido.setLoja(rs.getString("loja"));
				itempedidos.add(itempedido);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar item pedido");
                        System.err.println(e.getMessage());
		}
		return itempedidos;
	}//fim listar getItem Pedido


           
            
            public boolean excluir(ItemPedidoDTO itempedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from item_pedido where "+		    		
				    "id = " + itempedidoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir");
                        System.err.println(e.getMessage());
			return false;
		}
            } //fim excluir


            
            //pegar ultimo pedido cadastrado
            public ItemPedidoDTO getN_Pedido() {
        
                ItemPedidoDTO itempedido = new ItemPedidoDTO();
                
                try {
                             Conexao.ConectDB();
                            Statement stmt = Conexao.con.createStatement();
                            ResultSet rs = null;
                            rs = stmt.executeQuery("select n_pedido from pedidos where id = (select max(id) from pedidos)");

                            System.out.println("select n_pedido from pedidos where id = (select max(id) from pedidos)");

                    while (rs.next()) {
                        itempedido.setN_pedido(rs.getInt("n_pedido"));
                        

                    }
                    Conexao.CloseDB();
                } catch (Exception e) {
                    System.out.println("Erro ao Listar Produto By ID");
                    System.out.println(e.getMessage());
                }
                return itempedido;
            }//fim get N_pedido
            
            
            
           
//pegar refs por categoria
            public List<ItemPedidoDTO> getRefs_Categoria(String categoria) {
                ArrayList<ItemPedidoDTO> itempedidos = new ArrayList<ItemPedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT codigo, tipo_tamanho FROM produtos where descricao like '"+categoria+"%' and especial = 0 order by codigo");
                    
                    System.out.println("SELECT codigo, tipo_tamanho FROM produtos where descricao like '"+categoria+"%' and especial = 0 order by codigo");
		
                        while (rs.next()) {
				ItemPedidoDTO itempedido = new ItemPedidoDTO();
                                  
				itempedido.setCodigo(rs.getString("codigo"));
                                itempedido.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                itempedidos.add(itempedido);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar refs da categoria");
                        System.err.println(e.getMessage());
		}
		return itempedidos;
	}//fim listar getREF´s por categoria
        
            
        //pegar refs por categoria ****** DISPONIVEL ******
            public List<ItemPedidoDTO> getRefs_Categoria_Disponivel(String categoria, String dias, String saldo) {
                ArrayList<ItemPedidoDTO> itempedidos = new ArrayList<ItemPedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    //criando tabela temporaria com o saldo das refs
                    String sql = "CREATE TEMPORARY TABLE SALDO_REF AS\n" +
                        "SELECT CODIGO, SUM(total) AS Total FROM \n" +
                        "(select ip.codigo, sum(ip.total)*-1 as total\n" +
                        "from item_pedido ip\n" +
                        "INNER JOIN pedidos pe\n" +
                        "	on ip.n_pedido = pe.n_pedido\n" +
                        "	where pe.tipo_pedido = 'DIVERSOS' and data_entrega <= current_date+"+dias+"\n" +
                        "	group by ip.codigo\n" +
                        " UNION ALL\n" +
                        "	select codigo, sum(qtd) as total\n" +
                        "	from corte\n" +
                        "	where tipo_pedido = 'DIVERSOS' and data_corte <= current_date+"+dias+"\n" +
                        "	group by codigo) as saldo\n" +
                        "GROUP BY CODIGO ORDER BY CODIGO;";
                    System.out.println(sql.toUpperCase());
                    stmt.execute(sql.toUpperCase());
                    
                    
                    //TRAZER AS REF QUE ESTÃO POSITIVO E QUE TENHA DESCRICAO = '01 MACHAO'~
                    String sql2 = "SELECT s.codigo, p.tipo_tamanho FROM SALDO_REF s\n" +
                                    "	INNER JOIN produtos p\n" +
                                    "	on s.codigo = p.codigo\n" +
                                    "	where p.descricao like '"+categoria+"%' and s.total>"+saldo+" and p.especial = 0 order by s.total desc;";
                    
                    rs = stmt.executeQuery(sql2.toUpperCase());
                    
                    System.out.println(sql2.toUpperCase());
		
                        while (rs.next()) {
				ItemPedidoDTO itempedido = new ItemPedidoDTO();
                                  
				itempedido.setCodigo(rs.getString("codigo"));
                                itempedido.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                itempedidos.add(itempedido);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar refs da categoria disponivel");
                        System.err.println(e.getMessage());
		}
		return itempedidos;
	}//fim listar getREF´s por categoria    
            
        
            //adicionar Item no Item Pedido
    public boolean copiaItemPedido(ItemPedidoDTO itempedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    //insert into item_pedido (n_pedido, codigo, cor, t1, t2, t3, t4, cor_original, total, loja) 
                    //select item_pedido.getN_Pedido(), codigo, cor, t1, t2, t3, t4, cor_original, total, item_pediddo.getLoja() from item_pedido where n_pedido = 1 and loja = 'LOJA 01';                
                    String comando = "insert into item_pedido (n_pedido, codigo, cor, t1, t2, t3, t4, cor_original, total, loja) select " +
                                    "" + itempedidoDTO.getN_pedido_novo()+ "," +
				    " codigo, cor, t1, t2, t3, t4, cor_original, total, " +
				    "'" + itempedidoDTO.getLoja_novo()+ "' " +
				    "from item_pedido where n_pedido = "+itempedidoDTO.getN_pedido_original()+" and loja = '"+itempedidoDTO.getLoja_original()+"';";
				    
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('item_pedido_id_seq')");
                        while (rs.next()) {
                        itempedidoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao copiar pedido (item pedido)");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona
            
            
            
}//fim dao
