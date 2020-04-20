/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.ExpedicaoDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class ExpedicaoDAO {
    
    //listar item pedido de acordo com n_pedido
            public List<ExpedicaoDTO> getItemPedido(String n_pedido) {
                ArrayList<ExpedicaoDTO> expedicoes = new ArrayList<ExpedicaoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select pe.status, ip.codigo, p.descricao, ip.cor, sum(ip.t1) as total_t1, sum(ip.t2) as total_t2, sum(ip.t3) as total_t3, sum(ip.t4) as total_t4, sum(ip.total) as total_ref \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN produtos p \n" +
                                            "	ON ip.codigo = p.codigo \n" +
                                            "INNER JOIN pedidos pe\n" +
                                            "   ON ip.n_pedido = pe.n_pedido\n" +
                                            "	group by ip.codigo, p.descricao, ip.cor, ip.n_pedido, pe.status having ip.n_pedido = "+n_pedido+"\n" +
                                            "	order by ip.codigo, ip.cor;");
                    
                    System.out.println("select pe.status, ip.codigo, p.descricao, ip.cor, sum(ip.t1) as total_t1, sum(ip.t2) as total_t2, sum(ip.t3) as total_t3, sum(ip.t4) as total_t4, sum(ip.total) as total_ref \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN produtos p \n" +
                                            "	ON ip.codigo = p.codigo \n" +
                                            "INNER JOIN pedidos pe\n" +
                                            "   ON ip.n_pedido = pe.n_pedido\n" +
                                            "	group by ip.codigo, p.descricao, ip.cor, ip.n_pedido, pe.status having ip.n_pedido = "+n_pedido+"\n" +
                                            "	order by ip.codigo, ip.cor;");
		
                        while (rs.next()) {
				ExpedicaoDTO expedicao = new ExpedicaoDTO();
                                  
				expedicao.setStatus(rs.getString("status"));
				expedicao.setCodigo(rs.getString("codigo"));
                                expedicao.setDescricao(rs.getString("descricao"));
                                expedicao.setCor(rs.getString("cor"));
				expedicao.setTotal_t1(rs.getInt("total_t1"));
                                expedicao.setTotal_t2(rs.getInt("total_t2"));
                                expedicao.setTotal_t3(rs.getInt("total_t3"));
                                expedicao.setTotal_t4(rs.getInt("total_t4"));
                                expedicao.setTotal_ref(rs.getInt("total_ref"));
                               
				expedicoes.add(expedicao);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar item pedido");
                        System.err.println(e.getMessage());
		}
		return expedicoes;
	}//fim listar getItem Pedido
    
    
        //baixar Item no Estoque
public boolean baixaEstoque(ExpedicaoDTO expedicaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                                    
                    String comando = "insert into estoque (data_saida, codigo, cor, t1, t2, t3, t4, total, n_pedido) values " +
					"("+				    		
				    "'" + expedicaoDTO.getData_saida()+ "'," +
				    "'" + expedicaoDTO.getCodigo()+ "'," +
				    "'" + expedicaoDTO.getCor()+ "'," +
				    "'" + expedicaoDTO.getTotal_t1()+ "'," +
				    "'" + expedicaoDTO.getTotal_t2()+ "'," +
                                    "'" + expedicaoDTO.getTotal_t3()+ "'," +
                                    "'" + expedicaoDTO.getTotal_t4()+ "'," +
                                    "'" + expedicaoDTO.getTotal_ref()+ "'," +
                                    "'" + expedicaoDTO.getN_pedido()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao baixar item no estoque (expedicao)");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim baixa estoque
 
//mudar status pedido
 public boolean mudarStatus(Integer n_pedido){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "update pedidos set status = 'EXPEDIDO' where n_pedido ="+n_pedido+";";
				   
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao mudar status pedido (expedicao)");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim mudar status  



}
