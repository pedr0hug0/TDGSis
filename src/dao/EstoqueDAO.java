/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.EstoqueDTO;
import DTO.ProdutoDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class EstoqueDAO {
    
    
    //adicionar Item no Estoque
public boolean adicionaEstoque(EstoqueDTO estoqueDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                                    
                    String comando = "insert into estoque (data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao) values " +
					"("+				    		
				    "'" + estoqueDTO.getData_Entrada()+ "'," +
				    "'" + estoqueDTO.getCodigo()+ "'," +
				    "'" + estoqueDTO.getCor()+ "'," +
				    "'" + estoqueDTO.getT1()+ "'," +
				    "'" + estoqueDTO.getT2()+ "'," +
                                    "'" + estoqueDTO.getT3()+ "'," +
                                    "'" + estoqueDTO.getT4()+ "'," +
                                    "'" + estoqueDTO.getTotal()+ "'," +
                                    "'" + estoqueDTO.getDescricao()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('estoque_id_seq')");
                        while (rs.next()) {
                        estoqueDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.err.println("Erro ao adicionar item no estoque");
                        System.err.println(e.getMessage());
			return false;
		}
    } //fim adiciona estoque
    
    //pegar cores ref
        public List<ProdutoDTO> getCoresRef(String ref) {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    rs = stmt.executeQuery("SELECT qtd_cores, qtd_tamanho, tipo_tamanho, costas, manga, descricao FROM produtos where codigo = '"+ref+"';");
                    
                    System.out.println("SELECT qtd_cores, qtd_tamanho, tipo_tamanho, costas, manga, descricao FROM produtos where codigo = '"+ref+"';");
                    
                    
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

                                produto.setQtd_cores(rs.getInt("qtd_cores"));
                                produto.setQtd_tamanho(rs.getInt("qtd_tamanho"));
                                produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                produto.setCostas(rs.getInt("costas"));
                                produto.setManga(rs.getInt("manga"));
                                produto.setDescricao(rs.getString("descricao"));
                                produtos.add(produto);
                                                            
			}
                          
			Conexao.CloseDB();
		
                        
                } catch (Exception e) {
			System.out.println("Erro ao listar cores por ref");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim pesquisar cores por ref
        
        //pegar cores do CORTE
        public List<ProdutoDTO> getCoresCorte(String ref) {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    rs = stmt.executeQuery("SELECT distinct c.cor, p.descricao FROM corte c\n" +
                                            "INNER JOIN produtos p\n" +
                                            "	on c.codigo = p.codigo\n" +
                                            "	where p.codigo = '"+ref+"';");
                    /*select c.id, c.corte, c.codigo, c.cor, c.t1, c.t2, c.t3, c.t4, c.qtd, p.qtd_montado, p.etiqueta, p.data_alteracao, po.descricao
                                    from corte c
                                    INNER JOIN producao p
                                    	ON c.id = p.id
                                    INNER JOIN produtos po
					ON c.codigo = po.codigo
                                    where c.codigo like '%'
                                    order by c.id
                    */ 
                    
                    System.out.println("SELECT distinct c.cor, p.descricao FROM corte c\n" +
                                            "INNER JOIN produtos p\n" +
                                            "	on c.codigo = p.codigo\n" +
                                            "	where p.codigo = '"+ref+"';");
                    
                    
                    
                
                
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

                                produto.setCor(rs.getString("cor"));
				produto.setDescricao(rs.getString("descricao"));
                                produtos.add(produto);
                                                            
			}
                          
			Conexao.CloseDB();
		
                        
                } catch (Exception e) {
			System.out.println("Erro ao listar cores do corte por ref");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim cores do corte da ref x
        
        
        //listar estoque entrada total
            public List<EstoqueDTO> getEstoqueEntrada() {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where n_pedido is null order by id desc");
                    
                    System.out.println("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where n_pedido is null order by id desc");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				estoque.setId(rs.getInt("id"));
                                estoque.setData_Entrada(rs.getString("data_entrada"));
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoque.setDescricao(rs.getString("descricao"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar estoque entrada total");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque entrada total
        
        //listar entrada estoque por data
            public List<EstoqueDTO> getEstoqueDia(String data_pesquisar) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where data_entrada = '"+data_pesquisar+"' order by id desc");
                    
                    System.out.println("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where data_entrada = '"+data_pesquisar+"' order by id desc");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				estoque.setId(rs.getInt("id"));
                                estoque.setData_Entrada(rs.getString("data_entrada"));
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoque.setDescricao(rs.getString("descricao"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar estoque entrada");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque especifico
            
            
            //listar entrada estoque por ref
            public List<EstoqueDTO> getEstoqueEntradaRefID(String codigo) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where codigo = '"+codigo+"' and n_pedido is null order by id desc");
                    
                    System.out.println("select id, data_entrada, codigo, cor, t1, t2, t3, t4, total, descricao from estoque where codigo = '"+codigo+"' and n_pedido is null order by id desc");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				estoque.setId(rs.getInt("id"));
                                estoque.setData_Entrada(rs.getString("data_entrada"));
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoque.setDescricao(rs.getString("descricao"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar estoque entrada");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque entrada por ref
            
            
            public boolean excluir(EstoqueDTO estoqueDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from estoque where "+		    		
				    "id = " + estoqueDTO.getId()+";";
                  
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

            
            //listar estoque atual por ref
            public List<EstoqueDTO> getEstoqueRef(String ref_pesquisar) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque\n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque\n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar estoque");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque atual por ref
        
            
            
            
            //listar estoque ENTRADA por ref
            public List<EstoqueDTO> getEstoqueEntradaRef(String ref_pesquisar) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque where n_pedido is null \n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque where n_pedido is null \n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar ENTRADA estoque");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque entrada por ref
            
            
         //listar estoque SAIDA por ref
            public List<EstoqueDTO> getEstoqueSaidaRef(String ref_pesquisar) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque where n_pedido is not null \n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total\n" +
                                            "from estoque where n_pedido is not null \n" +
                                            "group by codigo, cor\n" +
                                            "HAVING codigo = '"+ref_pesquisar+"'");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar SAIDA estoque");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque SAIDA por ref    
            
            
            
            
        //listar vendas por ref
            public List<EstoqueDTO> getVendaRef(String ref_pesquisar, String tipo_pedido) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    if (tipo_pedido.equals("TODOS")){
                        rs = stmt.executeQuery("select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, sum(total) as total "+
                            "from item_pedido where CODIGO = '"+ref_pesquisar+"' group by codigo, cor order by codigo, cor;");
                    
                        System.out.println("select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, sum(total) as total "+
                            "from item_pedido where CODIGO = '"+ref_pesquisar+"' group by codigo, cor order by codigo, cor;");
		
                    }
                    else{//se for ESPECIAL / DIV
                        rs = stmt.executeQuery("select ip.codigo, ip.cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, sum(total) as total "+
                            "from item_pedido ip "
                            + "INNER JOIN pedidos p ON ip.n_pedido = p.n_pedido where CODIGO = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' group by codigo, cor order by codigo, cor;");
                        
                        System.out.println("select ip.codigo, ip.cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4, sum(total) as total "+
                            "from item_pedido ip "
                            + "INNER JOIN pedidos p ON ip.n_pedido = p.n_pedido where CODIGO = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' group by codigo, cor order by codigo, cor;");
                    }
                    
                    
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar vendas por referencia");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar venda por referencia
            
            
            //listar Saldo Vendas por ref
            public List<EstoqueDTO> getSaldoVendaCorteRef(String ref_pesquisar, String tipo_pedido) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    if (tipo_pedido.equals("TODOS")){
                        
                    
                            String comando = "CREATE TEMPORARY TABLE SALDO_CORTE AS\n" +
                                                        "select codigo, cor, sum(t1)*-1 as t1, sum(t2)*-1 as t2, sum(t3)*-1 as t3, sum(t4)*-1 as t4\n" +
                                                        "from item_pedido where codigo = '"+ref_pesquisar+"' group by codigo, cor\n" +
                                                        "UNION all\n" +
                                                        "select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4\n" +
                                                        "from corte where codigo = '"+ref_pesquisar+"' group by codigo, cor;";
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");

                            System.out.println("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
//CASO NÃO FOR TODOS, PASSA POR PARAMETRO O TIPO_PEDIDO (ESPECIAL OU DIVERSOS)            
                    }else { 
                    String comando = "CREATE TEMPORARY TABLE SALDO_CORTE AS\n" +
                                        "select ip.codigo, ip.cor, sum(t1)*-1 as t1, sum(t2)*-1 as t2, sum(t3)*-1 as t3, sum(t4)*-1 as t4\n" +
                                        "from item_pedido ip \n" +
                                        "INNER JOIN pedidos p \n" +
                                        "	ON ip.n_pedido = p.n_pedido \n" +
                                        "	 WHERE CODIGO = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' group by codigo, cor\n" +
                                        "UNION all\n" +
                                        "select codigo, cor, sum(t1) as t1, sum(t2) as t2, sum(t3) as t3, sum(t4) as t4\n" +
                                        "from corte where codigo = '"+ref_pesquisar+"' and tipo_pedido = '"+tipo_pedido+"' group by codigo, cor;";
                    stmt.execute(comando.toUpperCase());
                    
                    System.out.println(comando.toUpperCase());
                    //criou tabela temporaria
                    
                    rs = stmt.executeQuery("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
                    System.out.println("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_CORTE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
                    } 
                    
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                //estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
                        stmt.execute("drop table saldo_corte;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar vendas por referencia");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar saldo venda - corte por referencia   
            
        
            //listar Saldo Vendas por ref
            public List<EstoqueDTO> getSaldoVendaEstoqueRef(String ref_pesquisar, String tipo_pedido) {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                            String comando = "CREATE TEMPORARY TABLE SALDO_ESTOQUE AS\n" +
                                            "select ip.codigo, ip.cor, sum(ip.t1)*-1 as T1, sum(ip.t2)*-1 as T2, sum(ip.t3)*-1 as T3, sum(ip.t4)*-1 as T4\n" +
                                            "from item_pedido ip\n" +
                                            "INNER JOIN pedidos pe\n" +
                                            "	on ip.n_pedido = pe.n_pedido\n" +
                                            "	where codigo = '"+ref_pesquisar+"'\n" +
                                            "	group by ip.codigo, ip.cor\n" +
                                            " UNION ALL\n" +
                                            "	select e.codigo, e.cor, Sum(e.t1)    AS t1, Sum(e.t2)    AS t2, Sum(e.t3)    AS t3, Sum(e.t4)    AS t4\n" +
                                            "	from estoque e where e.n_pedido is null and e.codigo = '"+ref_pesquisar+"'\n" +
                                            "	group by e.codigo, e.cor;";
                            
                            stmt.execute(comando.toUpperCase());

                            System.out.println(comando.toUpperCase());
                            //criou tabela temporaria

                            rs = stmt.executeQuery("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_ESTOQUE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");

                            System.out.println("SELECT CODIGO, COR, SUM(T1) AS T1, SUM(T2) AS T2, SUM(T3) AS T3, SUM(T4) AS T4 FROM SALDO_ESTOQUE GROUP BY CODIGO, COR ORDER BY CODIGO, COR;");
                    
                    
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                //estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
                        stmt.execute("drop table saldo_estoque;");
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar venda - estoque por referencia");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar saldo venda - estoque por referencia  
            
            
            
            
            
        //listar estoque atual TOTAL
            public List<EstoqueDTO> getEstoqueTotal() {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar estoque TOTAL ATUAL");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque ATUAL TOTAL 
            
            
            
       //listar estoque ENTRADA TOTAL
            public List<EstoqueDTO> getEstoqueEntradaTotal() {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque where n_pedido is null  \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque where n_pedido is null \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar estoque TOTAL ENTRADA");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque ENTRADA TOTAL       
            
            
        //listar estoque SAIDA TOTAL
            public List<EstoqueDTO> getEstoqueSaidaTotal() {
                ArrayList<EstoqueDTO> estoques = new ArrayList<EstoqueDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select codigo, cor,\n" +
                                            "       Sum(t1)    AS t1, \n" +
                                            "       Sum(t2)    AS t2, \n" +
                                            "       Sum(t3)    AS t3, \n" +
                                            "       Sum(t4)    AS t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque where n_pedido is not null  \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
                    
                    System.out.println("select codigo, cor,\n" +
                                            "       Sum(t1)    AS total_t1, \n" +
                                            "       Sum(t2)    AS total_t2, \n" +
                                            "       Sum(t3)    AS total_t3, \n" +
                                            "       Sum(t4)    AS total_t4, \n" +
                                            "       Sum(total) AS total \n" +
                                            "from estoque where n_pedido is not null \n" +
                                            "group by codigo, cor \n" +
                                            "order by codigo");
		
                        while (rs.next()) {
				EstoqueDTO estoque = new EstoqueDTO();
                                  
				
                                estoque.setCodigo(rs.getString("codigo"));
                                estoque.setCor(rs.getString("cor"));
				estoque.setT1(rs.getInt("t1"));
                                estoque.setT2(rs.getInt("t2"));
                                estoque.setT3(rs.getInt("t3"));
                                estoque.setT4(rs.getInt("t4"));
                                estoque.setTotal(rs.getInt("total"));
                                estoques.add(estoque);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar consultar estoque TOTAL SAIDA");
                        System.err.println(e.getMessage());
		}
		return estoques;
	}//fim listar estoque SAIDA TOTAL       
}
