/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.ColecaoDTO;
import factory.Conexao_TDG_SIS;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class ColecaoDAO {
    
    //*********FAZER CREATE IF NOT EXIST BANCO TDG_SIS
    
    //listar colecoes
            public List<ColecaoDTO> getColecao() {
                ArrayList<ColecaoDTO> colecoes = new ArrayList<ColecaoDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    
                    
                        rs = stmt.executeQuery("SELECT id, nome, data, padrao FROM colecao order by padrao, id desc;");
                    
                        System.out.println("select * from colecao order by padrao, id desc;");
                    
                    
		
                        while (rs.next()) {
				ColecaoDTO colecao = new ColecaoDTO();
                                  
				colecao.setId(rs.getInt("id"));
                                colecao.setColecao(rs.getString("nome"));
                                colecao.setData(rs.getString("data"));
                                colecao.setPadrao(rs.getString("padrao"));
                                colecoes.add(colecao);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar colecao");
                        System.err.println(e.getMessage());
		}
		return colecoes;
	}//fim listar colecao
    


 public boolean adiciona_banco(ColecaoDTO colecaoDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "insert into colecao (nome) VALUES " +
					"("+
                                    "'" + colecaoDTO.getColecao()+ "');";
                           	
			System.out.println(comando);
			stmt.execute(comando);
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('colecao_id_seq')");
                        while (rs.next()) {
                        colecaoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar colecao");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona colecao
 
                public boolean excluir(ColecaoDTO colecaoDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from colecao where "+		    		
				    "id = " + colecaoDTO.getId()+";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir colecao de TDG_SIS - table colecao");
                        System.err.println(e.getMessage());
			return false;
		}
            } //fim excluir
                
                
            public boolean setarPadrao(ColecaoDTO colecaoDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando1 = "update colecao set "+		    		
				    "padrao = null;";
                    
                    String comando2 = "update colecao set "+		    		
				    "padrao = '" + colecaoDTO.getPadrao()+ "'" +
				    "where id = '"+colecaoDTO.getId()+ "';";
                  
			System.out.println(comando1);
			stmt.execute(comando1.toUpperCase());
                        System.out.println(comando2);
			stmt.execute(comando2.toUpperCase());
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar
                
                
 
}//fim dao 