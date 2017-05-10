/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.OrdemCorteDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class OrdemCorteDAO {
    
    //listar Mais Pedidos TOTAL
            public List<OrdemCorteDTO> getMaisPedidos(String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        sql = "select ip.codigo, count(*) as QTD_PEDIDO \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE'\n" +
                                            "group by ip.codigo\n" +
                                            "order by qtd_pedido desc, ip.codigo;";
                        
                        
                    }else {//SE FOR DIV OU ESPECIAL
                        sql = "select ip.codigo, count(*) as QTD_PEDIDO \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' and p.tipo_pedido = '"+tipo_pedido+"'\n" +
                                            "group by ip.codigo\n" +
                                            "order by qtd_pedido desc, ip.codigo;";
                    }
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_pedido(rs.getInt("qtd_pedido"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte
    
            
        //listar ordemcorte com DATA
            public List<OrdemCorteDTO> getMaisPedidosData(String data_entrega, String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        
                        sql = ("select ip.codigo, count(*) as QTD_PEDIDO \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                            "group by ip.codigo\n" +
                                            "order by qtd_pedido desc, ip.codigo;");
                    }else{//se div ou especial
                        sql = ("select ip.codigo, count(*) as QTD_PEDIDO \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' and p.tipo_pedido = '"+tipo_pedido+"' and data_entrega <= '"+data_entrega+"'\n" +
                                            "group by ip.codigo\n" +
                                            "order by qtd_pedido desc, ip.codigo;");
                    }
                    
                    rs = stmt.executeQuery(sql);
                    
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_pedido(rs.getInt("qtd_pedido"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte     
    
            
//********PRIORIDADE MAIS PEDIDOS
            
            //listar ordemcorte TOTAL
            public List<OrdemCorteDTO> getMaisPedidosP() {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select ip.codigo, count(*) as QTD_PEDIDO, p.prioridade \n" +
                                            "from item_pedido ip \n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' \n" +
                                            "group by ip.codigo, p.prioridade \n" +
                                            "order by p.prioridade desc, qtd_pedido desc, ip.codigo;");
                    
                    System.out.println("select ip.codigo, count(*) as QTD_PEDIDO, p.prioridade \n" +
                                            "from item_pedido ip \n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' \n" +
                                            "group by ip.codigo, p.prioridade \n" +
                                            "order by p.prioridade desc, qtd_pedido desc, ip.codigo;");
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_pedido(rs.getInt("qtd_pedido"));
                                ordemcorte.setPrioridade(rs.getInt("prioridade"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte prioridade");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte prioridade
            
        //listar ordemcorte com DATA
            public List<OrdemCorteDTO> getMaisPedidosDataP(String data_entrega) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select ip.codigo, count(*) as QTD_PEDIDO, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, qtd_pedido desc, ip.codigo;");
                    
                    System.out.println("select ip.codigo, count(*) as QTD_PEDIDO, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, qtd_pedido desc, ip.codigo;");;
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_pedido(rs.getInt("qtd_pedido"));
                                ordemcorte.setPrioridade(rs.getInt("prioridade"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem mais pedidos data prioridade");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte  data prioridade       
            
//************************************MAIS VENDEU*********************            
    
    //listar ordemcorte Mais Vendeu TOTAL
            public List<OrdemCorteDTO> getMaisVendeu(String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        sql = ("select ip.codigo, sum(ip.total) as total_ref \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' \n" +
                                            "group by ip.codigo\n" +
                                            "order by total_ref desc, ip.codigo;");
                    }else{
                        sql = ("select ip.codigo, sum(ip.total) as total_ref \n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos p \n" +
                                            "               ON ip.n_pedido = p.n_pedido \n" +
                                            "where p.status = 'PENDENTE' and p.tipo_pedido = '"+tipo_pedido+"' \n" +
                                            "group by ip.codigo\n" +
                                            "order by total_ref desc, ip.codigo;");
                    }
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setTotal_vendido(rs.getInt("total_ref"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte
    
    
      //listar ordemcorte com DATA
            public List<OrdemCorteDTO> getMaisVendeuData(String data_entrega, String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        sql = ("select ip.codigo, sum(ip.total) as total_ref \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo\n" +
                                                "order by total_ref desc, ip.codigo;");
                    }else{//se for div ou especial
                        sql = ("select ip.codigo, sum(ip.total) as total_ref \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' and p.tipo_pedido = '"+tipo_pedido+"' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo\n" +
                                                "order by total_ref desc, ip.codigo;");
                    }
                    rs = stmt.executeQuery(sql);
                    
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setTotal_vendido(rs.getInt("total_ref"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte  
            
            
//************PRIORIDADE
            
            
    //listar ordemcorte Mais Vendeu TOTAL
            public List<OrdemCorteDTO> getMaisVendeuP() {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select ip.codigo, sum(ip.total) as total_ref, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, total_ref desc, ip.codigo;");
                    System.out.println("select ip.codigo, sum(ip.total) as total_ref, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, total_ref desc, ip.codigo;");
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setTotal_vendido(rs.getInt("total_ref"));
                                ordemcorte.setPrioridade(rs.getInt("prioridade"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte mais vendeu prioridade");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte
    
        //****MAIS VENDEU PRIORIRADADE COM DATA
            
         //listar ordemcorte com DATA
            public List<OrdemCorteDTO> getMaisVendeuDataP(String data_entrega) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select ip.codigo, sum(ip.total) as total_ref, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, total_ref desc, ip.codigo;");
                    
                    System.out.println("select ip.codigo, sum(ip.total) as total_ref, p.prioridade \n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p \n" +
                                                "               ON ip.n_pedido = p.n_pedido \n" +
                                                "where p.status = 'PENDENTE' AND data_entrega <= '"+data_entrega+"'\n" +
                                                "group by ip.codigo, p.prioridade\n" +
                                                "order by p.prioridade desc, total_ref desc, ip.codigo;");
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setTotal_vendido(rs.getInt("total_ref"));
                                ordemcorte.setPrioridade(rs.getInt("prioridade"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte mais vendeu data prioridade");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte     
            
            
            
        
            //listar ordemcorte JA CORTADOS TOTAL
            public List<OrdemCorteDTO> getCortado(String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        
                        sql = ("select c.codigo, sum(c.qtd) as qtd_cortado\n" +
                                            "from corte c\n" +
                                            "group by c.codigo\n" +
                                            "order by qtd_cortado desc, c.codigo;");
                    
                    }else{
                        sql = ("select c.codigo, sum(c.qtd) as qtd_cortado\n" +
                                            "from corte c\n" +
                                            "where tipo_pedido = '"+tipo_pedido+"' \n" +
                                            "group by c.codigo\n" +
                                            "order by qtd_cortado desc, c.codigo;");
                    }            
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_cortado(rs.getInt("qtd_cortado"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte, getCortado");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte
            
            
        //listar ordemcorte JA CORTADOS TOTAL ***COM DATA*****
            public List<OrdemCorteDTO> getCortado(String data_entrega, String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        
                        sql = ("select c.codigo, sum(c.qtd) as qtd_cortado\n" +
                                            "from corte c\n" +
                                            "where data_corte <= '"+data_entrega+"'\n" +
                                            "group by c.codigo\n" +
                                            "order by qtd_cortado desc, c.codigo;");
                    
                    }else{
                        sql = ("select c.codigo, sum(c.qtd) as qtd_cortado\n" +
                                            "from corte c\n" +
                                            "where tipo_pedido = '"+tipo_pedido+"' AND data_corte <= '"+data_entrega+"'\n" +
                                            "group by c.codigo\n" +
                                            "order by qtd_cortado desc, c.codigo;");
                    }            
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
		
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				ordemcorte.setQtd_cortado(rs.getInt("qtd_cortado"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ordem corte, getCortado");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar ordemcorte    
        
            
            //listar SALDO VENDA - CORTE
            public List<OrdemCorteDTO> getSaldoVendaCorte(String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        
                    
                            String comando = "CREATE TEMPORARY TABLE ORDEM_SALDO_CORTE AS\n" +
            "                                        select ip.codigo, sum(total)*-1 as total_vendido\n" +
            "                                        from item_pedido ip \n" +
            "                                        INNER JOIN pedidos p\n" +
            "                                        	ON ip.n_pedido = p.n_pedido\n" +
            "                                        	 group by codigo\n" +
            "                                        UNION all\n" +
            "                                        select codigo, sum(qtd) as total_cortado\n" +
            "                                        from corte group by codigo;";
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");

                            System.out.println("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
//CASO NÃO FOR TODOS, PASSA POR PARAMETRO O TIPO_PEDIDO (ESPECIAL OU DIVERSOS)            
                    }else { 
                    String comando = "CREATE TEMPORARY TABLE ORDEM_SALDO_CORTE AS\n" +
                                        "select ip.codigo, sum(total)*-1 as total_vendido\n" +
                                        "                                        from item_pedido ip \n" +
                                        "                                        INNER JOIN pedidos p\n" +
                                        "                                        	ON ip.n_pedido = p.n_pedido\n" +
                                        "                                        	 WHERE tipo_pedido = '"+tipo_pedido+"' group by codigo\n" +
                                        "                                        UNION all\n" +
                                        "                                        select codigo, sum(qtd) as total_cortado\n" +
                                        "                                        from corte where tipo_pedido = '"+tipo_pedido+"' group by codigo;";
                    stmt.execute(comando.toUpperCase());
                    
                    System.out.println(comando.toUpperCase());
                    //criou tabela temporaria
                    
                    rs = stmt.executeQuery("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
                    System.out.println("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
                    } 
                    
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				//ordemcorte.setT1(rs.getInt("t1"));
                                //ordemcorte.setT2(rs.getInt("t2"));
                                //ordemcorte.setT3(rs.getInt("t3"));
                                //ordemcorte.setT4(rs.getInt("t4"));
                                ordemcorte.setTotal(rs.getInt("total"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
                        stmt.execute("drop table ordem_saldo_corte;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar SALDO ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar saldo venda - corte por referencia  
            
        //***** data_entrega    
            
        //listar SALDO VENDA - CORTE com DATAA
            public List<OrdemCorteDTO> getSaldoVendaCorte(String data_entrega, String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        
                    
                            String comando = "CREATE TEMPORARY TABLE ORDEM_SALDO_CORTE AS\n" +
            "                                        select ip.codigo, sum(total)*-1 as total_vendido\n" +
            "                                        from item_pedido ip \n" +
            "                                        INNER JOIN pedidos p\n" +
            "                                        	ON ip.n_pedido = p.n_pedido\n" +
            "                                        	 where data_entrega  <= '"+data_entrega+"' group by codigo\n" +
            "                                        UNION all\n" +
            "                                        select codigo, sum(qtd) as total_cortado\n" +
            "                                        from corte where data_corte  <= '"+data_entrega+"' group by codigo;";
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");

                            System.out.println("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
//CASO NÃO FOR TODOS, PASSA POR PARAMETRO O TIPO_PEDIDO (ESPECIAL OU DIVERSOS)            
                    }else { 
                    String comando = "CREATE TEMPORARY TABLE ORDEM_SALDO_CORTE AS\n" +
                                        "select ip.codigo, sum(total)*-1 as total_vendido\n" +
                                        "                                        from item_pedido ip \n" +
                                        "                                        INNER JOIN pedidos p\n" +
                                        "                                        	ON ip.n_pedido = p.n_pedido\n" +
                                        "                                        	 WHERE tipo_pedido = '"+tipo_pedido+"' and data_entrega  <= '"+data_entrega+"' group by codigo\n" +
                                        "                                        UNION all\n" +
                                        "                                        select codigo, sum(qtd) as total_cortado\n" +
                                        "                                        from corte where tipo_pedido = '"+tipo_pedido+"' and data_corte  <= '"+data_entrega+"' group by codigo;";
                    stmt.execute(comando.toUpperCase());
                    
                    System.out.println(comando.toUpperCase());
                    //criou tabela temporaria
                    
                    rs = stmt.executeQuery("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
                    System.out.println("select codigo, sum(total_vendido) as Total from ordem_saldo_corte group by codigo order by total;");
                    
                    } 
                    
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				//ordemcorte.setT1(rs.getInt("t1"));
                                //ordemcorte.setT2(rs.getInt("t2"));
                                //ordemcorte.setT3(rs.getInt("t3"));
                                //ordemcorte.setT4(rs.getInt("t4"));
                                ordemcorte.setTotal(rs.getInt("total"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
                        stmt.execute("drop table ordem_saldo_corte;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar SALDO ordem corte com data");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar saldo venda - corte por referencia      
            
            
            
            
            
       //**********************************saldo venda e montado
            
            //listar SALDO VENDA - montado
            public List<OrdemCorteDTO> getSaldoVendaMontado(String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        tipo_pedido = "%";
                    }
                    
                    String comando = "SELECT codigo, sum(total_vendido) as total FROM \n" +
                                        "(\n" +
                                        "select ip.codigo, sum(total)*-1 as total_vendido\n" +
                                        "            from item_pedido ip \n" +
                                        "            INNER JOIN pedidos p\n" +
                                        "		ON ip.n_pedido = p.n_pedido\n" +
                                        "		where TIPO_PEDIDO like '"+tipo_pedido+"'\n" +
                                        "            group by codigo\n" +
                                        "            UNION all\n" +
                                        "            select c.codigo, SUM(pr.qtd_montado) as total_cortado\n" +
                                        "            from corte c\n" +
                                        "	    inner join producao pr\n" +
                                        "			on c.id = pr.id\n" +
                                        "			where pr.TIPO_PEDIDO like '"+tipo_pedido+"'\n" +
                                        "			group by c.codigo\n" +
                                        ")\n" +
                                        "AS ORDEM_CORTE\n" +
                                        "group by ordem_corte.codigo order by total;";
                    
                    System.out.println(comando.toUpperCase());
                    rs = stmt.executeQuery(comando.toUpperCase());
                    
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				//ordemcorte.setT1(rs.getInt("t1"));
                                //ordemcorte.setT2(rs.getInt("t2"));
                                //ordemcorte.setT3(rs.getInt("t3"));
                                //ordemcorte.setT4(rs.getInt("t4"));
                                ordemcorte.setTotal(rs.getInt("total"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
                        
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar SALDO MONTADO ordem corte");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar saldo venda - MONTADO por referencia  
            
            
            
        // por DATA listar SALDO VENDA - montado por DATA
            public List<OrdemCorteDTO> getSaldoVendaMontadoPorData(String data_entrega, String tipo_pedido) {
                ArrayList<OrdemCorteDTO> ordemcortes = new ArrayList<OrdemCorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        tipo_pedido = "%";
                    }
                    
                    String comando = "SELECT codigo, sum(total_vendido) as total FROM \n" +
                                        "(\n" +
                                        "select ip.codigo, sum(total)*-1 as total_vendido\n" +
                                        "            from item_pedido ip \n" +
                                        "            INNER JOIN pedidos p\n" +
                                        "		ON ip.n_pedido = p.n_pedido\n" +
                                        "		where data_entrega  <= '"+data_entrega+"' AND TIPO_PEDIDO like '"+tipo_pedido+"'\n" +
                                        "            group by codigo\n" +
                                        "            UNION all\n" +
                                        "            select c.codigo, SUM(pr.qtd_montado) as total_cortado\n" +
                                        "            from corte c\n" +
                                        "	    inner join producao pr\n" +
                                        "			on c.id = pr.id\n" +
                                        "			where pr.TIPO_PEDIDO like '"+tipo_pedido+"'\n" +
                                        "			group by c.codigo\n" +
                                        ")\n" +
                                        "AS ORDEM_CORTE\n" +
                                        "group by ordem_corte.codigo order by total;";
                    
                    System.out.println(comando.toUpperCase());
                    rs = stmt.executeQuery(comando.toUpperCase());
                    
                        while (rs.next()) {
				OrdemCorteDTO ordemcorte = new OrdemCorteDTO();
                                  
				
                                ordemcorte.setCodigo(rs.getString("codigo"));
                                //ordemcorte.setCor(rs.getString("cor"));
				//ordemcorte.setT1(rs.getInt("t1"));
                                //ordemcorte.setT2(rs.getInt("t2"));
                                //ordemcorte.setT3(rs.getInt("t3"));
                                //ordemcorte.setT4(rs.getInt("t4"));
                                ordemcorte.setTotal(rs.getInt("total"));
                                ordemcortes.add(ordemcorte);
                                                            
			}
                        
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar SALDO MONTADO ordem corte por DATA");
                        System.err.println(e.getMessage());
		}
		return ordemcortes;
	}//fim listar saldo venda - MONTADO por referencia por DATA    
}
