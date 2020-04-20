/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.PedidoDTO;
import factory.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class PedidoDAO {
    
    //pegar ultimo pedido cadastrado
            public PedidoDTO gerar_n_pedido() {
        
                PedidoDTO pedido = new PedidoDTO();
                
                try {
                             Conexao.ConectDB();
                            Statement stmt = Conexao.con.createStatement();
                            ResultSet rs = null;
                            rs = stmt.executeQuery("SELECT nextval('gera_n_pedido');");

                            System.out.println("SELECT nextval('gera_n_pedido');");

                    while (rs.next()) {
                        pedido.setN_pedido(rs.getInt("nextval"));
                        

                    }
                    Conexao.CloseDB();
                } catch (Exception e) {
                    System.out.println("Erro ao gerar n_pedido");
                    System.out.println(e.getMessage());
                }
                return pedido;
            }//fim gerar N_pedido
    
            
            
    //adicionar n_pedido
    public boolean adiciona_pedido(PedidoDTO pedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "insert into pedidos (n_pedido, codigo_cliente, data_entrega, razao, fantasia, prioridade, status, tipo_pedido) VALUES " +
					"("+				    		
				    "'" + pedidoDTO.getN_pedido()+ "'," +
				    "'" + pedidoDTO.getCodigo_cliente()+ "'," +
				    "'" + pedidoDTO.getData_entrega()+ "'," +
				    "'" + pedidoDTO.getRazao()+ "'," +
				    "'" + pedidoDTO.getFantasia()+ "'," +
                                    "'" + pedidoDTO.getPrioriadade()+ "'," +
                                    "'" + pedidoDTO.getStatus()+ "'," +
                                    "'" + pedidoDTO.getTipo_pedido()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                         //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('pedidos_id_seq')");
                        while (rs.next()) {
                        pedidoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona pedido
            
    
    //listar pedido de acordo com n_pedido
            public List<PedidoDTO> getPedidoX(String n_pedido, String status) {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    
                    
                     if (status == "PENDENTE"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where n_pedido = "+n_pedido+" and status = 'PENDENTE' order by data_entrega;");
                        System.out.println("SELECT * FROM pedidos where n_pedido = "+n_pedido+" and status = 'PENDENTE' order by data_entrega;");
                    }
                    if (status == "EXPEDIDO"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where n_pedido = "+n_pedido+" and status = 'EXPEDIDO' order by data_entrega;");
                        System.out.println("SELECT * FROM pedidos where n_pedido = "+n_pedido+" and status = 'EXPEDIDO' order by data_entrega;");
                    }
                    if (status == "TODOS"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where n_pedido = "+n_pedido+" order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where n_pedido = "+n_pedido+" order by data_entrega");
                    }
                        while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();

				pedido.setId(rs.getInt("id"));
                                pedido.setData_cadastro(rs.getString("data_cadastro"));
                                pedido.setN_pedido(rs.getInt("n_pedido"));
				pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                                pedido.setData_entrega(rs.getString("data_entrega"));
                                pedido.setRazao(rs.getString("razao"));
                                pedido.setFantasia(rs.getString("fantasia"));
				pedido.setPrioriadade(rs.getInt("prioridade"));
                                pedido.setStatus(rs.getString("status"));
                                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
                                pedidos.add(pedido);
                                                            
			}
                        
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedido");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar getPedidoX
            
        //listar pedido de acordo com RAZAO
            public List<PedidoDTO> getPedidoRazao(String razao, String status) {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    
                    if (status == "PENDENTE"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' and status = 'PENDENTE' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' and status = 'PENDENTE' order by data_entrega");
                    }
                    if (status == "EXPEDIDO"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' and status = 'EXPEDIDO' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' and status = 'EXPEDIDO' order by data_entrega");
                    }
                    if (status == "TODOS"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where razao like '"+razao.toUpperCase()+"%' order by data_entrega");
                    }
                     while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();

				pedido.setId(rs.getInt("id"));
                                pedido.setData_cadastro(rs.getString("data_cadastro"));
                                pedido.setN_pedido(rs.getInt("n_pedido"));
				pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                                pedido.setData_entrega(rs.getString("data_entrega"));
                                pedido.setRazao(rs.getString("razao"));
                                pedido.setFantasia(rs.getString("fantasia"));
				pedido.setPrioriadade(rs.getInt("prioridade"));
                                pedido.setStatus(rs.getString("status"));
                                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
                                pedidos.add(pedido);
                                                            
			}
                        
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedido por razao");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar getPedidoX    
            
         //listar todos pedidos
            public List<PedidoDTO> listarPedidos() {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM pedidos order by id desc");
                    
                    System.out.println("SELECT * FROM pedidos order by id desc");
		
                        while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();

				pedido.setId(rs.getInt("id"));
                                pedido.setData_cadastro(rs.getString("data_cadastro"));
                                pedido.setN_pedido(rs.getInt("n_pedido"));
				pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                                pedido.setData_entrega(rs.getString("data_entrega"));
                                pedido.setRazao(rs.getString("razao"));
                                pedido.setFantasia(rs.getString("fantasia"));
				pedido.setPrioriadade(rs.getInt("prioridade"));
                                pedido.setStatus(rs.getString("status"));
                                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
                                pedidos.add(pedido);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedidos");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar pedidos   
    
        //listar todos pedidos
            public List<PedidoDTO> listarPedidosStatus(String status) {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    
                    if (status == "PENDENTE"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where status = 'PENDENTE' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where status = 'PENDENTE' order by data_entrega");
                    }
                    if (status == "EXPEDIDO"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where status = 'EXPEDIDO' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where status = 'EXPEDIDO' order by data_entrega");
                    }
                    if (status == "TODOS"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos order by data_entrega");
                        System.out.println("SELECT * FROM pedidos order by data_entrega");
                    }
		
                        while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();

				pedido.setId(rs.getInt("id"));
                                pedido.setData_cadastro(rs.getString("data_cadastro"));
                                pedido.setN_pedido(rs.getInt("n_pedido"));
				pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                                pedido.setData_entrega(rs.getString("data_entrega"));
                                pedido.setRazao(rs.getString("razao"));
                                pedido.setFantasia(rs.getString("fantasia"));
				pedido.setPrioriadade(rs.getInt("prioridade"));
                                pedido.setStatus(rs.getString("status"));
                                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
                                pedidos.add(pedido);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedidos com status");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar pedidos com status
            
        
        //listar pedido de acordo com COD CLIENTE
            public List<PedidoDTO> getPedidoCliente(String cod_cliente, String status) {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    
                   
                    if (status == "TODOS"){
                        rs = stmt.executeQuery("SELECT * FROM pedidos where codigo_cliente = '"+cod_cliente.toUpperCase()+"' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where codigo_cliente = '"+cod_cliente.toUpperCase()+"' order by data_entrega");
                    }else{
                        rs = stmt.executeQuery("SELECT * FROM pedidos where codigo_cliente = '"+cod_cliente.toUpperCase()+"' and status = '"+status+"' order by data_entrega");
                        System.out.println("SELECT * FROM pedidos where codigo_cliente = '"+cod_cliente.toUpperCase()+"' and status = '"+status+"' order by data_entrega");
                    }
                     while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();

				pedido.setId(rs.getInt("id"));
                                pedido.setData_cadastro(rs.getString("data_cadastro"));
                                pedido.setN_pedido(rs.getInt("n_pedido"));
				pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                                pedido.setData_entrega(rs.getString("data_entrega"));
                                pedido.setRazao(rs.getString("razao"));
                                pedido.setFantasia(rs.getString("fantasia"));
				pedido.setPrioriadade(rs.getInt("prioridade"));
                                pedido.setStatus(rs.getString("status"));
                                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
                                pedidos.add(pedido);
                                                            
			}
                        
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedido por cod cliente");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar getPedido Por Codigo Cliente     
            
            
            
        public boolean excluir(PedidoDTO pedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from pedidos where "+		    		
				    "id = " + pedidoDTO.getId()+";";
                  
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
    
        
    //verificar se n_pedido existe    
    public boolean temPedido(String n_pedido) throws SQLException{  
        boolean result = false;   
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM pedidos where n_pedido = "+n_pedido+";");  
        if(rs.next()){  
            result = true;
        }  
    stmt.close();
    Conexao.CloseDB(); 
    return result;  
    }  
      
    
    //pesquisar por id (para alterar)
        public PedidoDTO getPedidoById(Integer id) {
        
        PedidoDTO pedido = new PedidoDTO();
        String listarbyid = "SELECT * FROM pedidos where id = ?";
        try {
            Conexao.ConectDB();
            PreparedStatement pstmt = Conexao.con.prepareStatement(listarbyid);
            ResultSet rs;
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            System.out.println(rs);
            
            while (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setN_pedido(rs.getInt("n_pedido"));
		pedido.setCodigo_cliente(rs.getInt("codigo_cliente"));
                pedido.setRazao(rs.getString("razao"));
                pedido.setFantasia(rs.getString("fantasia"));
		pedido.setData(rs.getDate("data_entrega"));//mudei nome pq criei nova no DTO
                pedido.setPrioriadade(rs.getInt("prioridade"));
                pedido.setStatus(rs.getString("status"));
                pedido.setTipo_pedido(rs.getString("tipo_pedido"));
		
                
            }
            Conexao.CloseDB();
        } catch (Exception e) {
            System.out.println("Erro ao Listar Pedidos By ID (alterar)");
            System.out.println(e.getMessage());
        }
        return pedido;
    }//fim buscar pedido por id (alterar) 
        
    
        
        public boolean alterar(PedidoDTO pedidoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "update pedidos set "+		    		
				    "n_pedido = " + pedidoDTO.getN_pedido()+ "," +
				    "codigo_cliente = " + pedidoDTO.getCodigo_cliente()+ "," +
				    "data_entrega = '" + pedidoDTO.getData_entrega()+ "'," +
				    "razao = '" + pedidoDTO.getRazao()+ "'," +
				    "fantasia = '" + pedidoDTO.getFantasia()+ "'," +
                                    "prioridade = " + pedidoDTO.getPrioriadade()+ "," +
                                    "status = '" + pedidoDTO.getStatus()+ "'," +
                                    "tipo_pedido = '" + pedidoDTO.getTipo_pedido()+ "' " +
				    "where id = "+pedidoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar
        
    

//criar tabela temporaria com consumo dos pedidos
            public List<PedidoDTO> getConsumoPedidos(String tipo_pedido, String todos_n_pedidos) {
                ArrayList<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                                            
                            String comando = "CREATE TEMPORARY TABLE RELATORIO_VENDAS_SELEC AS\n" +
"				SELECT PRO.DESCRICAO, relatorio_vendas.CODIGO, COR, SUM(total_venda) AS vendido FROM (select ip.codigo, ip.cor, sum(total) as total_VENDA\n" +
"                                                from item_pedido ip\n" +
"                                                INNER JOIN pedidos p\n" +
"                                                	ON ip.n_pedido = p.n_pedido\n" +
"                                                	WHERE p.tipo_pedido like '"+tipo_pedido+"' and p.n_pedido in ("+todos_n_pedidos+")\n" +
"                                                	group by ip.codigo, ip.cor\n" +
"                                                order by codigo)\n" +
"                                                AS RELATORIO_VENDAS, produtos pro\n" +
"                                                where relatorio_vendas.codigo = pro.codigo\n" +
"                                                GROUP BY pro.descricao, relatorio_vendas.CODIGO, COR ORDER BY  CODIGO, COR;";
                            
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("SELECT r.descricao, r.codigo, r.cor, r.vendido, p.rendimento, (r.vendido/p.rendimento) as consumo \n" +
                            "from RELATORIO_VENDAS_SELEC r INNER JOIN produtos p on r.codigo = p.codigo;");

                            System.out.println("SELECT r.descricao, r.codigo, r.cor, r.vendido, p.rendimento, (r.vendido/p.rendimento) as consumo \n" +
"from RELATORIO_VENDAS_SELEC r INNER JOIN produtos p on r.codigo = p.codigo;");

                    
                        while (rs.next()) {
				PedidoDTO pedido = new PedidoDTO();
                                  
				
                                pedido.setDescricao(rs.getString("descricao"));
                                pedido.setCodigo(rs.getString("codigo"));
                                pedido.setCor(rs.getString("cor"));
                                pedido.setQtd(rs.getInt("vendido"));
                                pedido.setConsumo(rs.getDouble("consumo"));
                                pedidos.add(pedido);
                                                            
			}
                        stmt.execute("DROP TABLE RELATORIO_VENDAS_SELEC;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao criar consumo pedidos em pedidoDAO");
                        System.err.println(e.getMessage());
		}
		return pedidos;
	}//fim listar saldo venda - corte por referencia   

        
    
        
    

        
}//fim dao
