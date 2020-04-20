/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.CoresDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class ProdutoCoresDAO {
    
    public boolean adicionaProdutoCor(CoresDTO coresDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                                    
                    String comando = "insert into produto_cores (codigo, cor, id_cor) values " +
                                    "("+				    		
				    "'" + coresDTO.getCodigo()+ "'," +
                                    "'" + coresDTO.getCor()+ "'," +
				    "" + coresDTO.getId_cor()+ ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('produto_cores_id_seq')");
                        while (rs.next()) {
                        coresDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar cor");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona cor
    
    //listar produto cores
            public List<CoresDTO> ListarProdutoCores(String codigo) {
                ArrayList<CoresDTO> produto_cores = new ArrayList<CoresDTO>();
		try {
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                    
                    sql = "select id, codigo, cor, id_cor from produto_cores where codigo like '"+codigo+"';";
                    rs = stmt.executeQuery(sql.toUpperCase());
                    System.out.println(sql.toUpperCase());
                   
                        while (rs.next()) {
				CoresDTO produto_cor = new CoresDTO();
                                
                                produto_cor.setId(rs.getInt("id"));
                                produto_cor.setCodigo(rs.getString("codigo"));
                                produto_cor.setCor(rs.getString("cor"));
                                produto_cor.setId_cor(rs.getInt("id_cor"));
                                produto_cores.add(produto_cor);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar produto cores");
                        System.err.println(e.getMessage());
		}
		return produto_cores;
	}//fim listar produto cores
    
       
            
            
            
    public boolean excluir(CoresDTO coresDTO){
		try{
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from produto_cores where "+		    		
				    "id = " + coresDTO.getId()+";";
                  
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
    
}
