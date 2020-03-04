/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.ProducaoDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class ProducaoDAO {
    
        //adicionar Produção (corte)
public boolean adicionaProducao(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    
                    
                    
                    
                    // corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd
                    String comando = "insert into producao (id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, qtd, tipo_pedido, etiqueta) values " +
					"("+				    		
				    "'" + producaoDTO.getId()+ "'," +
                                    "'" + producaoDTO.getCorte()+ "'," +
                                    "'" + producaoDTO.getCodigo()+ "'," +
				    "'" + producaoDTO.getCor()+ "'," +
				    "'" + producaoDTO.getFrente()+ "'," +
                                    "'" + producaoDTO.getCostas()+ "'," +
                                    "'" + producaoDTO.getManga()+ "'," +
                                    "'" + producaoDTO.getStatus()+ "'," +
                                    "'" + producaoDTO.getData_alteracao()+ "'," +
                                    "'" + producaoDTO.getQtd()+ "'," +
                                    "'" + producaoDTO.getTipo_pedido()+ "'," +
				    "'" + producaoDTO.getEtiqueta()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        /*
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('producao_id_seq')");
                        while (rs.next()) {
                        producaoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        */
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar item em producao");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona producao


  
    //listar producao 
            public List<ProducaoDTO> getProducao(String status, String tipo_pedido) {
                ArrayList<ProducaoDTO> producoes = new ArrayList<ProducaoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    //corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd
                    if ("TODOS".equals(status)){
                        rs = stmt.executeQuery("select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd, qtd_montado from producao where tipo_pedido like '"+tipo_pedido+"' order by status, id;");
                        System.out.println("select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd from producao tipo_pedido like '"+tipo_pedido+"' order by status, id;");
                    }
                    else{
                        rs = stmt.executeQuery("select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd, qtd_montado from producao where tipo_pedido like '"+tipo_pedido+"' and status = '"+status+"' order by status, id;");
                        System.out.println("select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd from producao where tipo_pedido like '"+tipo_pedido+"' status = '"+status+"' order by status, id;");
                    }
                    
		
                        while (rs.next()) {
				ProducaoDTO producao = new ProducaoDTO();
                       //corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd      
				producao.setId(rs.getInt("id"));
                                producao.setCorte(rs.getString("corte"));
                                producao.setCodigo(rs.getString("codigo"));
                                producao.setCor(rs.getString("cor"));
                                producao.setFrente(rs.getString("frente"));
                                producao.setCostas(rs.getString("costas"));
                                producao.setManga(rs.getString("manga"));
                                producao.setStatus(rs.getString("status"));
                                //producao.setData_alteracao(rs.getDate("data_alteracao"));
                                producao.setData_alteracao_str(rs.getString("data_alteracao"));
                                producao.setData_inicio(rs.getString("data_inicio"));
                                producao.setQtd(rs.getInt("qtd"));
                                producao.setQtd_montado(rs.getInt("qtd_montado"));
				producoes.add(producao);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar producao");
                        System.err.println(e.getMessage());
		}
		return producoes;
	}//fim listar producao  
    
        //listar producao REF
            public List<ProducaoDTO> getProducaoRef(String status, String codigo, String tipo_pedido) {
                ArrayList<ProducaoDTO> producoes = new ArrayList<ProducaoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql = null;
                    //corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd
                    if ("TODOS".equals(status)){
                        sql = "select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd, qtd_montado from producao where codigo like '"+codigo+"%' and tipo_pedido like '"+tipo_pedido+"' order by status, id;";
                        rs = stmt.executeQuery(sql.toUpperCase());
                        System.out.println(sql.toUpperCase());
                    }
                    else{
                        sql = "select id, corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd, qtd_montado from producao where status = '"+status+"' and codigo like '"+codigo+"%' and tipo_pedido like '"+tipo_pedido+"' order by status, id;";
                        rs = stmt.executeQuery(sql.toUpperCase());
                        System.out.println(sql.toUpperCase());
                    }
                    
		
                        while (rs.next()) {
				ProducaoDTO producao = new ProducaoDTO();
                       //corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd      
				producao.setId(rs.getInt("id"));
                                producao.setCorte(rs.getString("corte"));
                                producao.setCodigo(rs.getString("codigo"));
                                producao.setCor(rs.getString("cor"));
                                producao.setFrente(rs.getString("frente"));
                                producao.setCostas(rs.getString("costas"));
                                producao.setManga(rs.getString("manga"));
                                producao.setStatus(rs.getString("status"));
                                //producao.setData_alteracao(rs.getDate("data_alteracao"));
                                producao.setData_alteracao_str(rs.getString("data_alteracao"));
                                producao.setData_inicio(rs.getString("data_inicio"));
                                producao.setQtd(rs.getInt("qtd"));
                                producao.setQtd_montado(rs.getInt("qtd_montado"));
				producoes.add(producao);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar producao");
                        System.err.println(e.getMessage());
		}
		return producoes;
	}//fim listar producao ref     
            
            public boolean atualizar(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update producao set "+		    		
				    "data_alteracao = CURRENT_TIMESTAMP," +
				    "frente = '" + producaoDTO.getFrente()+ "'," +
                                    "costas = '" + producaoDTO.getCostas()+ "'," +
                                    "manga = '" + producaoDTO.getManga()+ "'," +
                                    "qtd_montado = " + producaoDTO.getQtd_montado()+ ", " +
                                    "status = '" + producaoDTO.getStatus()+ "', " +
                                    "perda = " + producaoDTO.getPerda()+ ", " +
                                    "perdeu = '" + producaoDTO.getPerdeu()+ "' " +
				    "where id = "+producaoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar/atualizar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar/atualizar
            
            public boolean montar_parcial(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update producao set "+		    		
				    "data_alteracao = CURRENT_TIMESTAMP," +
				    "qtd_montado = " + producaoDTO.getQtd_montado()+ " " +
                                    "where id = "+producaoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar/atualizar");
                        System.out.println(e.getMessage());
			return false;
		}
            }
            
            
             public boolean excluir(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from producao where "+		    		
				    "id = " + producaoDTO.getId()+";";
                  
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
            
            
             //listar PERDAS
            public List<ProducaoDTO> getPerdas(String tipo_perdeu, String codigo) {
                ArrayList<ProducaoDTO> producoes = new ArrayList<ProducaoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    if (codigo == null || codigo.isEmpty()){
                        codigo = "%";
                    }
                        String sql = "select id, corte, codigo, cor, qtd, qtd_montado, perda, perdeu, data_alteracao, data_inicio from producao where perdeu like '"+tipo_perdeu+"' and codigo like '"+codigo+"%' order by data_alteracao desc, id;";
                        rs = stmt.executeQuery(sql.toUpperCase());
                        System.out.println(sql.toUpperCase());
                   
                        while (rs.next()) {
				ProducaoDTO producao = new ProducaoDTO();
                       //corte, codigo, cor, frente, costas, manga, status, data_alteracao, data_inicio, qtd      
				producao.setId(rs.getInt("id"));
                                producao.setCorte(rs.getString("corte"));
                                producao.setCodigo(rs.getString("codigo"));
                                producao.setCor(rs.getString("cor"));
                                producao.setQtd(rs.getInt("qtd"));
                                producao.setQtd_montado(rs.getInt("qtd_montado"));
                                producao.setPerda(rs.getInt("perda"));
                                producao.setPerdeu(rs.getString("perdeu"));
                                producao.setData_alteracao_str(rs.getString("data_alteracao"));
                                producao.setData_inicio(rs.getString("data_inicio"));
                                
				producoes.add(producao);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar perda producao");
                        System.err.println(e.getMessage());
		}
		return producoes;
	}//fim listar perda producao  
        
            
        //ATUALIZAR PERDEU DE PERDAS    
        public boolean atualizarPerdeu(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update producao set "+		    		
				    "perdeu = '" + producaoDTO.getPerdeu()+ "' " +
				    "where id = "+producaoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar/atualizarPerdeu");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar/atualizar 
        
    public boolean repor(ProducaoDTO producaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update producao set "+		    		
				    "qtd_montado = " + producaoDTO.getQtd_montado()+ ", " +
                                    "perda = " + producaoDTO.getPerda()+ ", " +
                                    "perdeu = '" + producaoDTO.getPerdeu()+ "' " +
				    "where id = "+producaoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao repor ref");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim repor
    
    
    
}//FIM DAO
