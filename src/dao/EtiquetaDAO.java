/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.EtiquetaDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class EtiquetaDAO {
    
    //listar producao 
            public List<EtiquetaDTO> getEtiqueta(String status) {
                ArrayList<EtiquetaDTO> etiquetas = new ArrayList<EtiquetaDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if ("TODOS".equals(status)){
                        sql = "select c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, qtd_montado, etiqueta, data_alteracao, po.tipo_tamanho, po.descricao \n" +
                                    "from corte c\n" +
                                    "INNER JOIN producao p\n" +
                                    "	ON c.id = p.id \n" +
                                    "INNER JOIN produtos po\n"+
                                    "   ON c.codigo = po.codigo\n"+
                                    "        order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    }
                    else{
                        sql = "select c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, qtd_montado, etiqueta, data_alteracao, po.tipo_tamanho, po.descricao \n" +
                                    "from corte c\n" +
                                    "INNER JOIN producao p\n" +
                                    "	ON c.id = p.id \n" +
                                    "INNER JOIN produtos po\n"+
                                    "   ON c.codigo = po.codigo\n"+
                                    "        where p.etiqueta = '"+status+"' \n" +
                                    "        order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    }
                    
		
                        while (rs.next()) {
				EtiquetaDTO etiqueta = new EtiquetaDTO();
                       //c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, etiqueta
				etiqueta.setId(rs.getInt("id"));
                                etiqueta.setCorte(rs.getString("corte"));
                                etiqueta.setCodigo(rs.getString("codigo"));
                                etiqueta.setCor(rs.getString("cor"));
                                etiqueta.setT1(rs.getInt("t1"));
                                etiqueta.setT2(rs.getInt("t2"));
                                etiqueta.setT3(rs.getInt("t3"));
                                etiqueta.setT4(rs.getInt("t4"));
                                etiqueta.setQtd(rs.getInt("qtd"));
                                etiqueta.setQtd_montado(rs.getInt("qtd_montado"));
                                etiqueta.setEtiqueta(rs.getString("etiqueta"));
                                etiqueta.setData_alteracao(rs.getString("data_alteracao"));
                                etiqueta.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                etiqueta.setDescricao(rs.getString("descricao"));
                                
				etiquetas.add(etiqueta);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar etiqueta");
                        System.err.println(e.getMessage());
		}
		return etiquetas;
	}//fim listar etiqueta  
    
        //listar etiqueta REF
            public List<EtiquetaDTO> getEtiquetaRef(String status, String codigo) {
                ArrayList<EtiquetaDTO> etiquetas = new ArrayList<EtiquetaDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if ("TODOS".equals(status)){
                        sql = "select c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, qtd_montado, etiqueta, data_alteracao, po.tipo_tamanho, po.descricao \n" +
                                    "from corte c\n" +
                                    "INNER JOIN producao p\n" +
                                    "	ON c.id = p.id \n" +
                                    "INNER JOIN produtos po\n"+
                                    "   ON c.codigo = po.codigo\n"+
                                    "        where c.codigo like '"+codigo+"'\n" +
                                    "        order by c.id";
                        rs = stmt.executeQuery(sql.toUpperCase());
                        System.out.println(sql);
                    }
                    else{
                        sql = "select c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, qtd_montado, etiqueta, data_alteracao, po.tipo_tamanho, po.descricao \n" +
                                    "from corte c\n" +
                                    "INNER JOIN producao p\n" +
                                    "	ON c.id = p.id \n" +
                                    "INNER JOIN produtos po\n"+
                                    "   ON c.codigo = po.codigo\n"+
                                    "        where p.etiqueta = '"+status+"' and c.codigo like '"+codigo+"'\n" +
                                    "        order by c.id";
                        rs = stmt.executeQuery(sql.toUpperCase());
                        System.out.println(sql);
                    }
                    
		
                        while (rs.next()) {
				EtiquetaDTO etiqueta = new EtiquetaDTO();
                       //c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, etiqueta
				etiqueta.setId(rs.getInt("id"));
                                etiqueta.setCorte(rs.getString("corte"));
                                etiqueta.setCodigo(rs.getString("codigo"));
                                etiqueta.setCor(rs.getString("cor"));
                                etiqueta.setT1(rs.getInt("t1"));
                                etiqueta.setT2(rs.getInt("t2"));
                                etiqueta.setT3(rs.getInt("t3"));
                                etiqueta.setT4(rs.getInt("t4"));
                                etiqueta.setQtd(rs.getInt("qtd"));
                                etiqueta.setQtd_montado(rs.getInt("qtd_montado"));
                                etiqueta.setEtiqueta(rs.getString("etiqueta"));
                                etiqueta.setData_alteracao(rs.getString("data_alteracao"));
                                etiqueta.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                etiqueta.setDescricao(rs.getString("descricao"));
                                
				etiquetas.add(etiqueta);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar etiquetas por ref");
                        System.err.println(e.getMessage());
		}
		return etiquetas;
	}//fim listar etiquetas ref    
    
            
        public boolean atualizar(EtiquetaDTO etiquetaDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update producao set "+		    		
				    "etiqueta = '" + etiquetaDTO.getEtiqueta()+ "' " +
				    "where id = "+etiquetaDTO.getId()+ ";";
                  
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
            
            
    public ResultSet getId_Cor(String codigo, String cor) {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    /*
                            rs = stmt.executeQuery("select id_cor from produto_cores\n" +
                                                "where codigo = '"+codigo+"'\n" +
                                                "and cor = '"+cor+"'");
                    */
                    rs = stmt.executeQuery("select to_char(id_cor, 'fm000') as id_cor from produto_cores\n" +
                                                "where codigo = '"+codigo+"'\n" +
                                                "and cor = '"+cor+"'");
                    
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedidos");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar id_cor     
            
            
}
