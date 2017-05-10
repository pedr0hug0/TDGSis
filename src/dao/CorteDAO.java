/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.CorteDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PeDr0_HuG0
 */
public class CorteDAO {
    
    
    //adicionar Item no Estoque
public boolean adicionaCorte(CorteDTO corteDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                                    
                    String comando = "insert into corte (data_corte, codigo, cor, qtd, corte, t1, t2, t3, t4, tipo_pedido, programado, infesto, grade) values " +
					"("+				    		
				    "'" + corteDTO.getData_corte()+ "'," +
				    "'" + corteDTO.getCodigo()+ "'," +
				    "'" + corteDTO.getCor()+ "'," +
				    "'" + corteDTO.getQtd()+ "'," +
                                    "'" + corteDTO.getCorte()+ "'," +
                                    "'" + corteDTO.getT1()+ "'," +
                                    "'" + corteDTO.getT2()+ "'," +
                                    "'" + corteDTO.getT3()+ "'," +
                                    "'" + corteDTO.getT4()+ "'," +
                                    "'" + corteDTO.getTipo_pedido()+ "'," +
                                    "'" + corteDTO.getProgramado()+ "'," +
                                    "'" + corteDTO.getInfesto()+ "'," +
				    "'" + corteDTO.getGrade()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('corte_id_seq')");
                        while (rs.next()) {
                        corteDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar item no corte");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona corte

public boolean excluir(CorteDTO corteDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from corte where "+		    		
				    "id = " + corteDTO.getId()+";";
                  
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


 
public boolean atualizar(CorteDTO corteDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update corte set "+		    		
				    "data_corte = '" + corteDTO.getData_corte()+ "', " +
                                    "qtd = " + corteDTO.getQtd()+ ", " +
                                    "corte = '" + corteDTO.getCorte()+ "', " +
                                    "t1 = " + corteDTO.getT1()+ ", " +
                                    "t2 = " + corteDTO.getT2()+ ", " +
                                    "t3 = " + corteDTO.getT3()+ ", " +
                                    "t4 = " + corteDTO.getT4()+ ", " +
                                    "tipo_pedido = '" + corteDTO.getTipo_pedido()+ "' " +
                                    "where id = "+corteDTO.getId()+ ";";
                    String comando2 = "update producao set "+		    		
				    "data_inicio = '" + corteDTO.getData_corte()+ "', " +
                                    "corte = '" + corteDTO.getCorte()+ "', " +
                                    "qtd = " + corteDTO.getQtd()+ " " +
                                    "where id = "+corteDTO.getId()+ ";";
                    
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        System.out.println(comando2);
			stmt.execute(comando2.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
                        JOptionPane.showMessageDialog(null, e.getMessage(), null, 2);
			System.out.println("Erro ao alterar/atualizar");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar/atualizar







//listar corte por ref
            public List<CorteDTO> getCorteRef(String ref_pesquisar, String tipo_pedido) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    if(tipo_pedido.equals("TODOS")){
                        rs = stmt.executeQuery("select * from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' order by id desc");
                    
                    System.out.println("select * from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' order by id desc");
                    }else{
                        rs = stmt.executeQuery("select * from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' and tipo_pedido like '"+tipo_pedido+"' order by id desc");
                        System.out.println("select * from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' and tipo_pedido like '"+tipo_pedido+"' order by id desc");
                    }
                    
                    
                    while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setId(rs.getInt("id"));
                                corte.setData_corte(rs.getString("data_corte"));
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setQtd(rs.getInt("qtd"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setT1(rs.getInt("t1"));
                                corte.setT2(rs.getInt("t2"));
                                corte.setT3(rs.getInt("t3"));
                                corte.setT4(rs.getInt("t4"));
                                corte.setTipo_pedido(rs.getString("tipo_pedido"));
                                corte.setGrade(rs.getString("grade"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar corte por ref");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar corte especifico
            
            
          
            
            
 
     //listar corte por ref
            public List<CorteDTO> getCorte(String tipo_pedido) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    if(tipo_pedido.equals("TODOS")){
                        rs = stmt.executeQuery("select * from corte order by id desc;");
                    
                        System.out.println("select * from corte;");
                    }else{
                        rs = stmt.executeQuery("select * from corte where tipo_pedido = '"+tipo_pedido+"' order by id desc");
                        System.out.println("select * from corte where tipo_pedido = '"+tipo_pedido+"' order by id desc");
                    }
                    
		
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setId(rs.getInt("id"));
                                corte.setData_corte(rs.getString("data_corte"));
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setQtd(rs.getInt("qtd"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setT1(rs.getInt("t1"));
                                corte.setT2(rs.getInt("t2"));
                                corte.setT3(rs.getInt("t3"));
                                corte.setT4(rs.getInt("t4"));
                                corte.setTipo_pedido(rs.getString("tipo_pedido"));
                                corte.setGrade(rs.getString("grade"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar corte");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar corte  
        
            
       //listar vendas por ref com data_entrega
            public List<CorteDTO> getSaldoVendaCorteRef(String ref_pesquisar, String tipo_pedido, String data_entrega) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        
                            String comando = "CREATE TEMPORARY TABLE SALDO_CORTE AS\n" +
                                                    "select ip.codigo, ip.cor, sum(ip.t1)*-1 as T1, sum(ip.t2)*-1 as T2, sum(ip.t3)*-1 as T3, sum(ip.t4)*-1 as T4, pe.data_entrega\n" +
                                                    "from item_pedido ip\n" +
                                                    "INNER JOIN pedidos pe\n" +
                                                    "	on ip.n_pedido = pe.n_pedido\n" +
                                                    "	where codigo = '"+ref_pesquisar+"' and data_entrega <= '"+data_entrega+"'\n" +
                                                    "	group by ip.codigo, ip.cor, pe.data_entrega\n" +
                                                    " UNION ALL\n" +
                                                    "	select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, data_corte\n" +
                                                    "	from corte\n" +
                                                    "	where codigo = '"+ref_pesquisar+"' and data_corte <= '"+data_entrega+"'\n" +
                                                    "	group by codigo, cor, data_corte;";
                            
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");

                            System.out.println("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
//CASO NÃO FOR TODOS, PASSA POR PARAMETRO O TIPO_PEDIDO (ESPECIAL OU DIVERSOS)            
                    }else { 
                    String comando = "CREATE TEMPORARY TABLE SALDO_CORTE AS\n" +
                                                    "select ip.codigo, ip.cor, sum(ip.t1)*-1 as T1, sum(ip.t2)*-1 as T2, sum(ip.t3)*-1 as T3, sum(ip.t4)*-1 as T4, pe.data_entrega\n" +
                                                    "from item_pedido ip\n" +
                                                    "INNER JOIN pedidos pe\n" +
                                                    "	on ip.n_pedido = pe.n_pedido\n" +
                                                    "	where codigo = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' and data_entrega <= '"+data_entrega+"'\n" +
                                                    "	group by ip.codigo, ip.cor, pe.data_entrega\n" +
                                                    " UNION ALL\n" +
                                                    "	select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, data_corte\n" +
                                                    "	from corte\n" +
                                                    "	where codigo = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' and data_corte <= '"+data_entrega+"'\n" +
                                                    "	group by codigo, cor, data_corte;";
                    stmt.execute(comando.toUpperCase());
                    
                    System.out.println(comando.toUpperCase());
                    //criou tabela temporaria
                    
                    rs = stmt.executeQuery("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
                    System.out.println("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
                    } 
                    
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setT1(rs.getInt("t1"));
                                corte.setT2(rs.getInt("t2"));
                                corte.setT3(rs.getInt("t3"));
                                corte.setT4(rs.getInt("t4"));
                                //corte.setTotal(rs.getInt("total"));
                                cortes.add(corte);
                                                            
			}
                        stmt.execute("drop table saldo_corte;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar vendas por referencia");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar saldo venda - corte por referencia       
            
        //listar vendas por ref
            public List<CorteDTO> getVendaRef(String ref_pesquisar, String tipo_pedido, String data_entrega) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    String sql;
                    if (tipo_pedido.equals("TODOS")){
                        sql = "select ip.codigo, ip.cor, sum(ip.t1) as T1, sum(ip.t2) as T2, sum(ip.t3) as T3, sum(ip.t4) as T4, sum(ip.total) as total\n" +
                                    "from item_pedido ip\n" +
                                    "INNER JOIN pedidos pe\n" +
                                    "	on ip.n_pedido = pe.n_pedido\n" +
                                    "	where codigo = '"+ref_pesquisar+"' and data_entrega <= '"+data_entrega+"'\n" +
                                    "	group by ip.codigo, ip.cor";
                        rs = stmt.executeQuery(sql);
                    
                        System.out.println(sql);
		
                    }
                    else{//se for ESPECIAL / DIV
                        sql = "select ip.codigo, ip.cor, sum(ip.t1) as T1, sum(ip.t2) as T2, sum(ip.t3) as T3, sum(ip.t4) as T4, sum(ip.total) as total\n" +
                                    "from item_pedido ip\n" +
                                    "INNER JOIN pedidos pe\n" +
                                    "	on ip.n_pedido = pe.n_pedido\n" +
                                    "	where codigo = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' and data_entrega <= '"+data_entrega+"'\n" +
                                    "	group by ip.codigo, ip.cor";
                        rs = stmt.executeQuery(sql);
                    
                        System.out.println(sql);
                    }
                    
                    
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setT1(rs.getInt("t1"));
                                corte.setT2(rs.getInt("t2"));
                                corte.setT3(rs.getInt("t3"));
                                corte.setT4(rs.getInt("t4"));
                                corte.setTotal(rs.getInt("total"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar vendas por referencia com data_entrega");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar venda por referencia    
            
            
        
        //***************************************CONSUMO MALHA **********************
            
            /*
            //listar programacao
            public List<CorteDTO> getProgramacao(String programado,String tipo_produto, String codigo) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                    //if (programado == "TODOS"){
                    //    programado = "%";
                    //}
                    
                    if(tipo_produto.equals("PIQUET")){
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where (p.descricao like '%PIQUET%' or p.descricao like '%P001%') and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    }else if (tipo_produto.equals("POLO")){
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where p.descricao like '%POLO%' and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    } else if (tipo_produto.equals("CARECA_E_V")){
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where (p.descricao like '%CARECA%' or p.descricao like '% V%') and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                    }else if (tipo_produto.equals("ESPECIAL")){ //PRODUTO ESPECIAIS (TIPOS, MODELOS DIFERENTES.)
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where p.descricao like '%DIVERSOS%' and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    } else{//***TODOS MENOS PIQUET
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where p.descricao NOT LIKE '%PIQUET%' and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    }
                    
		
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setId(rs.getInt("id"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setQtd(rs.getInt("qtd"));
                                corte.setConsumo(rs.getDouble("consumo"));
                                corte.setProgramado(rs.getString("programado"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar programacao");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar programacao
        */    
 
        //***** LISTAR PROGRAMAÇÃO 2
        
        //listar corte por ref
            public List<CorteDTO> getProgramacao2(String programado,String tipo_produto, String codigo) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                    
                        sql = "SELECT c.id, c.corte, c.codigo, c.cor, c.qtd, (c.qtd/p.rendimento) as consumo, c.programado\n" +
                                                    "from corte c\n" +
                                                    "INNER JOIN produtos p\n" +
                                                    "	on c.codigo = p.codigo\n" +
                                                    "where p.descricao like '"+tipo_produto+"' and c.programado LIKE '"+programado+"' and c.codigo LIKE '"+codigo+"'\n" +
                                                    "group by c.id, c.corte, c.codigo, c.cor, c.qtd, p.rendimento order by c.id";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                        
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setId(rs.getInt("id"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setQtd(rs.getInt("qtd"));
                                corte.setConsumo(rs.getDouble("consumo"));
                                corte.setProgramado(rs.getString("programado"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar programacao");
                        System.err.println(e.getMessage());
		}
		return cortes;
        }//fim listar programacao    
            
            
            
        public boolean atualizarProgramado(CorteDTO corteDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update corte set "+		    		
				    "programado = '" + corteDTO.getProgramado()+ "' " +
				    "where id = "+corteDTO.getId()+ ";";
                  
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
    
    
        
    // ficha tecnica corte ProdutoFichaVIEW
            public List<CorteDTO> fichaCorte(String ref_pesquisar) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    
                    rs = stmt.executeQuery("select distinct codigo, corte, grade from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' order by corte desc");
                    
                    System.out.println("select distinct codigo, corte, grade from corte \n" +
                                            "where codigo = '"+ref_pesquisar+"' order by corte desc");
                    
                    
                    while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setCodigo(rs.getString("codigo"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setGrade(rs.getString("grade"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar ficha corte por ref");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar ficha corte especifico    
        
        
        
        //*** INFESTOOO
        
        public List<CorteDTO> getCorteInfesto(String descricao, String infesto) {
                ArrayList<CorteDTO> cortes = new ArrayList<CorteDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                        String sql = "select c.id, data_corte, c.corte, c.codigo, c.cor, c.qtd, c.t1, c.t2, c.t3, c.t4, c.infesto \n" +
                                    "from corte c\n" +
                                    "INNER JOIN produtos pro\n" +
                                    "on c.codigo = pro.codigo where pro.descricao = '"+descricao+"' and c.infesto = '"+infesto+"' order by id desc;";
                        rs = stmt.executeQuery(sql);
                        System.out.println(sql);
                    
		
                        while (rs.next()) {
				CorteDTO corte = new CorteDTO();
                                  
				corte.setId(rs.getInt("id"));
                                corte.setData_corte(rs.getString("data_corte"));
                                corte.setCorte(rs.getString("corte"));
                                corte.setCodigo(rs.getString("codigo"));
                                corte.setCor(rs.getString("cor"));
				corte.setQtd(rs.getInt("qtd"));
                                
                                corte.setT1(rs.getInt("t1"));
                                corte.setT2(rs.getInt("t2"));
                                corte.setT3(rs.getInt("t3"));
                                corte.setT4(rs.getInt("t4"));
                                corte.setInfesto(rs.getString("infesto"));
                                cortes.add(corte);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar corte infesto");
                        System.err.println(e.getMessage());
		}
		return cortes;
	}//fim listar corte infesto* 
        
        
        //ATUALIZAR INFESTO
        public boolean atualizarInfesto(CorteDTO corteDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "update corte set "+		    		
				    "infesto = '" + corteDTO.getInfesto()+ "' " +
				    "where id = "+corteDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao alterar/atualizar infesto");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim alterar/atualizar 
        
        
        
        
}
