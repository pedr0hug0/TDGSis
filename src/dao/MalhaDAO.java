/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.MalhaDTO;
import factory.Conexao_TDG_SIS;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class MalhaDAO {
    
    
    public boolean excluir(MalhaDTO malhaDTO){
		try{
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from "+malhaDTO.getPROCESSO()+" where "+		    		
				    "id = " + malhaDTO.getId()+";";
                  
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
    
    
    
    public boolean adicionaMalhaPedido(MalhaDTO malhaDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                                    
                    String comando = "insert into malha_pedido (data, processo, tipo_tecido, "
                            + "cor, qtd_malha, qtd_punho, qtd_gola, qtd_gola_juv, status) values " +
                                    "("+				    		
				    "'" + malhaDTO.getData()+ "'," +
				    "'" + malhaDTO.getPROCESSO()+ "'," +
				    "'" + malhaDTO.getTIPO_TECIDO()+ "'," +
				    "'" + malhaDTO.getCOR()+ "'," +
                                    "'" + malhaDTO.getQTD_MALHA()+ "'," +
                                    "'" + malhaDTO.getQTD_PUNHO()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA_JUV()+ "'," +
                                    "'" + malhaDTO.getSTATUS()+ "'" +
                                    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('malha_pedido_id_seq')");
                        while (rs.next()) {
                        malhaDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar malha pedido");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona malha pedido
    
    
    //listar malha pedido
            public List<MalhaDTO> getMalhaPedidosOuEntrega(String processo, String status) {
                ArrayList<MalhaDTO> malhas = new ArrayList<MalhaDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                    if(processo.equals("PEDIDO")){
                        sql = "select * from malha_pedido where status like '"+status+"' order by id desc, processo desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    }else if (processo.equals("PEDIDO/ENTREGA")) {//PEDIDOS/ENTREGA
                        sql = "select * from malha_pedido where status like '"+status+"' "
                                + "union "
                                + "select * from malha_entrega where status like '"+status+"'"
                                + " order by id desc, processo desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    }else if (processo.equals("ENTREGA")) {//ENTREGA
                        sql = "select * from malha_entrega where status like '"+status+"' order by id desc, processo desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    }
                    
		
                        while (rs.next()) {
				MalhaDTO malha = new MalhaDTO();
                                
                                malha.setId(rs.getInt("id"));
                                malha.setData(rs.getString("data"));
                                malha.setPROCESSO(rs.getString("processo"));
                                malha.setTIPO_TECIDO(rs.getString("tipo_tecido"));
                                malha.setCOR(rs.getString("cor"));
                                malha.setQTD_MALHA(rs.getInt("qtd_malha"));
                                malha.setQTD_PUNHO(rs.getInt("qtd_punho"));
                                malha.setQTD_GOLA(rs.getInt("qtd_gola"));
                                malha.setQTD_GOLA_JUV(rs.getInt("qtd_gola_juv"));
                                malha.setSTATUS(rs.getString("status"));
                                
                                malhas.add(malha);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar malha pedido");
                        System.err.println(e.getMessage());
		}
		return malhas;
	}//fim listar malha  
    
    
    
        public boolean adicionaMalhaCru(MalhaDTO malhaDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                                    
                    String comando = "insert into malha_cru (data, processo, tipo_tecido, "
                            + "qtd_malha, qtd_punho, qtd_gola, qtd_gola_juv) values " +
                                    "("+				    		
				    "'" + malhaDTO.getData()+ "'," +
				    "'" + malhaDTO.getPROCESSO()+ "'," +
				    "'" + malhaDTO.getTIPO_TECIDO()+ "'," +
				    "'" + malhaDTO.getQTD_MALHA()+ "'," +
                                    "'" + malhaDTO.getQTD_PUNHO()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA_JUV()+ "'" +
                                    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('malha_cru_id_seq')");
                        while (rs.next()) {
                        malhaDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar malha CRU");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona malha CRU    
            
    //listar malha CRU
            public List<MalhaDTO> getMalhaCru() {
                ArrayList<MalhaDTO> malhas = new ArrayList<MalhaDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    String sql;
                   
                        sql = "select * from malha_cru order by id desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                      
                        while (rs.next()) {
				MalhaDTO malha = new MalhaDTO();
                                
                                malha.setId(rs.getInt("id"));
                                malha.setData(rs.getString("data"));
                                malha.setPROCESSO(rs.getString("processo"));
                                malha.setTIPO_TECIDO(rs.getString("tipo_tecido"));
                                malha.setQTD_MALHA(rs.getInt("qtd_malha"));
                                malha.setQTD_PUNHO(rs.getInt("qtd_punho"));
                                malha.setQTD_GOLA(rs.getInt("qtd_gola"));
                                malha.setQTD_GOLA_JUV(rs.getInt("qtd_gola_juv"));
                                
                                
                                malhas.add(malha);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar malha cru");
                        System.err.println(e.getMessage());
		}
		return malhas;
	}//fim listar malha  cru
        
        //listar SALDO malha CRU
            public List<MalhaDTO> getSaldoMalhaCru() {
                ArrayList<MalhaDTO> malhas = new ArrayList<MalhaDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    String sql;
                   
                        sql = "SELECT TIPO_TECIDO, SUM(TOTAL_MALHA) AS SALDO_MALHA, SUM(TOTAL_PUNHO) AS SALDO_PUNHO, SUM(TOTAL_GOLA) AS SALDO_GOLA, SUM(TOTAL_GOLA_JUV) AS SALDO_GOLA_JUV\n" +
                                    "FROM --SELECT UNION\n" +
                                    "(SELECT TIPO_TECIDO, SUM(QTD_MALHA) AS TOTAL_MALHA, SUM(QTD_PUNHO) AS TOTAL_PUNHO, SUM(QTD_GOLA) AS TOTAL_GOLA, SUM(QTD_GOLA_JUV) AS TOTAL_GOLA_JUV\n" +
                                    "	FROM MALHA_CRU\n" +
                                    "	GROUP BY malha_cru.tipo_tecido\n" +
                                    "UNION\n" +
                                    " SELECT TIPO_TECIDO, SUM(QTD_MALHA*-1) AS TOTAL_MALHA, SUM(QTD_PUNHO*-1) AS TOTAL_PUNHO, SUM(QTD_GOLA*-1) AS TOTAL_GOLA, SUM(QTD_GOLA_JUV*-1) AS TOTAL_GOLA_JUV\n" +
                                    "	FROM MALHA_ENTREGA\n" +
                                    "	GROUP BY malha_entrega.tipo_tecido\n" +
                                    ") AS SALDO_MALHA_CRU\n" +
                                    "GROUP BY tipo_tecido";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                      
                        while (rs.next()) {
				MalhaDTO malha = new MalhaDTO();
                                
                                
                                malha.setTIPO_TECIDO(rs.getString("tipo_tecido"));
                                malha.setQTD_MALHA(rs.getInt("saldo_malha"));
                                malha.setQTD_PUNHO(rs.getInt("saldo_punho"));
                                malha.setQTD_GOLA(rs.getInt("saldo_gola"));
                                malha.setQTD_GOLA_JUV(rs.getInt("saldo_gola_juv"));
                                
                                
                                malhas.add(malha);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar SALDO malha cru");
                        System.err.println(e.getMessage());
		}
		return malhas;
	}//fim listar SALDO malha  cru    
            
        
        public boolean adicionaMalhaEntrega(MalhaDTO malhaDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                                    
                    String comando = "insert into malha_entrega (id, data, processo, tipo_tecido, "
                            + "cor, qtd_malha, qtd_punho, qtd_gola, qtd_gola_juv, status) values " +
                                    "("+				    		
				    "'" + malhaDTO.getId()+ "'," +//id PEDIDO MALHA
                                    "'" + malhaDTO.getData()+ "'," +
				    "'" + malhaDTO.getPROCESSO()+ "'," +
				    "'" + malhaDTO.getTIPO_TECIDO()+ "'," +
				    "'" + malhaDTO.getCOR()+ "'," +
                                    "'" + malhaDTO.getQTD_MALHA()+ "'," +
                                    "'" + malhaDTO.getQTD_PUNHO()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA_JUV()+ "'," +
                                    "'" + malhaDTO.getSTATUS()+ "'" +
                                    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //update no pedido
                        String comando2 = "update malha_pedido set "+		    		
				    "status = '" + malhaDTO.getSTATUS()+ "' where id = "+malhaDTO.getId()+";";
                        stmt.execute(comando2.toUpperCase());
                        
                        
                        /*pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('malha_pedido_id_seq')");
                        while (rs.next()) {
                        malhaDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        */
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar malha entrega/update malha pedido");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona malha ENTREGA
            
            
    //adicionaMalhaItensParciais        
    public boolean adicionaMalhaItensParciais(MalhaDTO malhaDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                                    
                    String comando = "insert into malha_itens_parciais (id, data, processo, tipo_tecido, "
                            + "cor, qtd_malha, qtd_punho, qtd_gola, qtd_gola_juv, alerta) values " +
                                    "("+				    		
				    "'" + malhaDTO.getId()+ "'," +//id PEDIDO MALHA
                                    "'" + malhaDTO.getData()+ "'," +
                                    "'" + malhaDTO.getPROCESSO()+ "'," +
				    "'" + malhaDTO.getTIPO_TECIDO()+ "'," +
				    "'" + malhaDTO.getCOR()+ "'," +
                                    "'" + malhaDTO.getQTD_MALHA()+ "'," +
                                    "'" + malhaDTO.getQTD_PUNHO()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA()+ "'," +
                                    "'" + malhaDTO.getQTD_GOLA_JUV()+ "'," +
                                    "'" + malhaDTO.getALERTA()+ "'" +
                                    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar malha itens parciais");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona itens parciais    
            
    
   //listar malha pedido
            public List<MalhaDTO> getItensParciais(String alerta) {
                ArrayList<MalhaDTO> malhas = new ArrayList<MalhaDTO>();
		try {
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                    
                        sql = "select * from malha_itens_parciais where alerta like '"+alerta+"' order by id desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    
                        while (rs.next()) {
				MalhaDTO malha = new MalhaDTO();
                                
                                malha.setId(rs.getInt("id"));
                                malha.setData(rs.getString("data"));
                                malha.setPROCESSO(rs.getString("processo"));
                                malha.setTIPO_TECIDO(rs.getString("tipo_tecido"));
                                malha.setCOR(rs.getString("cor"));
                                malha.setQTD_MALHA(rs.getInt("qtd_malha"));
                                malha.setQTD_PUNHO(rs.getInt("qtd_punho"));
                                malha.setQTD_GOLA(rs.getInt("qtd_gola"));
                                malha.setQTD_GOLA_JUV(rs.getInt("qtd_gola_juv"));
                                malha.setALERTA(rs.getString("alerta"));
                                
                                malhas.add(malha);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar itens parciais");
                        System.err.println(e.getMessage());
		}
		return malhas;
	}//fim listar itens parciais
    
    public boolean atualizarAlerta(MalhaDTO malhaDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    String comando = "update malha_itens_parciais set "+		    		
				    "alerta = '" + malhaDTO.getALERTA()+ "' " +
				    "where id = "+malhaDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar/atualizar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar/atualizar   
    
}
