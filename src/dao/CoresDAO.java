/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.CoresDTO;
import factory.Conexao_TDG_SIS;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class CoresDAO {
    
    public boolean adicionaCor(CoresDTO coresDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                                    
                    String comando = "insert into cores (cor, categoria) values " +
                                    "("+				    		
				    "'" + coresDTO.getCor()+ "'," +
				    "'" + coresDTO.getCategoria()+ "');";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('cores_id_seq')");
                        while (rs.next()) {
                        coresDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar cor");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona cor
    
    
    //listar cores
            public List<CoresDTO> ListarCores() {
                ArrayList<CoresDTO> cores = new ArrayList<CoresDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                   
                    sql = "select id, cor, categoria from cores order by categoria;";
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
                   
                    
		
                        while (rs.next()) {
				CoresDTO cor = new CoresDTO();
                                
                                cor.setId(rs.getInt("id"));
                                cor.setCor(rs.getString("cor"));
                                cor.setCategoria(rs.getString("categoria"));
                                cores.add(cor);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar cores");
                        System.err.println(e.getMessage());
		}
		return cores;
	}//fim listar cores
    
     public boolean excluir(CoresDTO coresDTO){
		try{
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from cores where "+		    		
				    "id = " + coresDTO.getId()+";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir");
                        System.err.println(e.getMessage());
			return false;
		}
            } //fim excluir
 
     //listar categorias
            public List<CoresDTO> ListarCategoria() {
                ArrayList<CoresDTO> cores = new ArrayList<CoresDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                   
                    sql = "select distinct categoria from cores order by categoria desc;";
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
                   
                    
		
                        while (rs.next()) {
				CoresDTO cor = new CoresDTO();
                                cor.setCategoria(rs.getString("categoria"));
                                cores.add(cor);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar categoria");
                        System.err.println(e.getMessage());
		}
		return cores;
	}//fim listar categoria
     
     //listar cores da categorias
            public List<CoresDTO> ListarCoresCategoria(String categoria) {
                ArrayList<CoresDTO> cores = new ArrayList<CoresDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                   
                    //sql = "select id, cor, categoria from cores where categoria = '"+categoria+"';";
                    sql = "select id, cor, categoria from cores where categoria = '"+categoria+"' ORDER BY COR;";
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
                   
                    
		
                        while (rs.next()) {
				CoresDTO cor = new CoresDTO();
                                cor.setId(rs.getInt("id"));
                                cor.setCor(rs.getString("cor"));
                                cor.setCategoria(rs.getString("categoria"));
                                cores.add(cor);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar cores da categoria");
                        System.err.println(e.getMessage());
		}
		return cores;
	}//fim listar cores da categoria
     
     
     //listar cor do ID_COR
            public List<CoresDTO> getCor_By_ID(String id_cor) {
                ArrayList<CoresDTO> cores = new ArrayList<CoresDTO>();
                try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    String sql;
                    sql = "select cor from cores where id = "+id_cor;
                    
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
                   
                    
		
                        while (rs.next()) {
				CoresDTO cor = new CoresDTO();
                                cor.setCor(rs.getString("cor"));
                                cores.add(cor);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar cor by ID_COR");
                        System.err.println(e.getMessage());
		}
		return cores;
	}//fim listar cor by ID_COR
    
}
