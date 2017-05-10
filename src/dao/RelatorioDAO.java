/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author PeDr0_HuG0
 */
public class RelatorioDAO {
    
    
    public ResultSet listarPedido(String n_pedido) {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select ip.codigo, ip.cor, ip.t1, ip.t2, ip.t3, ip.t4, ip.total, pro.descricao, ip.n_pedido, ip.loja from item_pedido ip\n" +
                                                "inner join produtos pro\n" +
                                                "on ip.codigo = pro.codigo\n" +
                                                "where n_pedido = "+n_pedido+"\n" +
                                                "order by pro.descricao");
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar pedidos");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar pedidos   
    


public ResultSet relatorio_vendas(String tipo_pedido) {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    rs = stmt.executeQuery("SELECT PRO.DESCRICAO, relatorio_vendas.CODIGO, COR, SUM(total_venda) AS Vendido, sum(total_Corte) as Cortado, sum(total_Montado) as Montado, sum(corte_count) as qtd_corte FROM (\n" +
                                                "	select ip.codigo, ip.cor, sum(total) as total_VENDA, null as total_corte, null as total_montado, null as corte_count\n" +
                                                "	from item_pedido ip\n" +
                                                "	INNER JOIN pedidos p\n" +
                                                "		ON ip.n_pedido = p.n_pedido\n" +
                                                "		WHERE p.tipo_pedido like '"+tipo_pedido+"'\n" +
                                                "		group by ip.codigo, ip.cor\n" +
                                                "	UNION\n" +
                                                "	select c.codigo, c.cor, null as total_VENDA, sum(c.qtd) as total_Corte, sum(pr.qtd_montado) as total_montado, count(distinct c.corte)as corte_count \n" +
                                                "	from corte c\n" +
                                                "	inner join producao pr\n" +
                                                "			on c.id = pr.id\n" +
                                                "	WHERE c.tipo_pedido like '"+tipo_pedido+"'\n" +
                                                "	group by c.codigo, c.cor\n" +
                                                "	order by codigo\n" +
                                                ")\n" +
                                                "AS RELATORIO_VENDAS, produtos pro\n" +
                                                "where relatorio_vendas.codigo = pro.codigo\n" +
                                                "GROUP BY pro.descricao, relatorio_vendas.CODIGO, COR ORDER BY  CODIGO, COR;");
                    /*
                            rs = stmt.executeQuery("SELECT PRO.DESCRICAO, relatorio_vendas.CODIGO, COR, SUM(total_venda) AS Vendido, sum(total_Corte) as Cortado FROM (select ip.codigo, ip.cor, sum(total) as total_VENDA, null as total_corte\n" +
                                                    "from item_pedido ip\n" +
                                                    "INNER JOIN pedidos p\n" +
                                                    "	ON ip.n_pedido = p.n_pedido\n" +
                                                    "	WHERE p.tipo_pedido LIKE '"+tipo_pedido+"'\n" +
                                                    "	group by ip.codigo, ip.cor\n" +
                                                    "UNION\n" +
                                                    "select c.codigo, c.cor, null as total_VENDA, sum(qtd) as total_Corte\n" +
                                                    "from corte c\n" +
                                                    "WHERE c.tipo_pedido LIKE '"+tipo_pedido+"' \n" +
                                                    "group by c.codigo, c.cor\n" +
                                                    "order by codigo)\n" +
                                                    "AS RELATORIO_VENDAS, produtos pro\n" +
                                                    "where relatorio_vendas.codigo = pro.codigo\n" +
                                                    "GROUP BY pro.descricao, relatorio_vendas.CODIGO, COR ORDER BY CODIGO, COR;");
                            */
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar relatorio vendas");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar vendas   
    

        public ResultSet relatorio_vendas_DATA(String tipo_pedido, String data_entrega) {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    rs = stmt.executeQuery("SELECT PRO.DESCRICAO, relatorio_vendas.CODIGO, COR, SUM(total_venda) AS Vendido, sum(total_Corte) as Cortado, sum(total_Montado) as Montado, sum(corte_count) as qtd_corte FROM (\n" +
                                                "	select ip.codigo, ip.cor, sum(total) as total_VENDA, null as total_corte, null as total_montado, null as corte_count\n" +
                                                "	from item_pedido ip\n" +
                                                "	INNER JOIN pedidos p\n" +
                                                "		ON ip.n_pedido = p.n_pedido\n" +
                                                "		WHERE p.tipo_pedido like '"+tipo_pedido+"' and data_entrega <= '"+data_entrega+"'\n" +
                                                "		group by ip.codigo, ip.cor\n" +
                                                "	UNION\n" +
                                                "	select c.codigo, c.cor, null as total_VENDA, sum(c.qtd) as total_Corte, sum(pr.qtd_montado) as total_montado, count(distinct c.corte)as corte_count \n" +
                                                "	from corte c\n" +
                                                "	inner join producao pr\n" +
                                                "			on c.id = pr.id\n" +
                                                "	WHERE c.tipo_pedido like '"+tipo_pedido+"'\n" +
                                                "	group by c.codigo, c.cor\n" +
                                                "	order by codigo\n" +
                                                ")\n" +
                                                "AS RELATORIO_VENDAS, produtos pro\n" +
                                                "where relatorio_vendas.codigo = pro.codigo\n" +
                                                "GROUP BY pro.descricao, relatorio_vendas.CODIGO, COR ORDER BY  CODIGO, COR;");
                   
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar relatorio vendas");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar vendas   


//listar vendas dos n_pedidos selecionados
public ResultSet relatorio_vendas_n_pedidos(String tipo_pedido, String todos_n_pedidos) {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    
                    rs = stmt.executeQuery("SELECT PRO.DESCRICAO, relatorio_vendas.CODIGO, COR, SUM(total_venda) AS Vendido, sum(total_Corte) as Cortado FROM (select ip.codigo, ip.cor, sum(total) as total_VENDA, null as total_corte\n" +
                                                "from item_pedido ip\n" +
                                                "INNER JOIN pedidos p\n" +
                                                "	ON ip.n_pedido = p.n_pedido\n" +
                                                "	WHERE p.tipo_pedido = '"+tipo_pedido+"' and p.n_pedido in ("+todos_n_pedidos+")\n" +
                                                "	group by ip.codigo, ip.cor\n" +
                                                "UNION\n" +
                                                "select c.codigo, c.cor, null as total_VENDA, sum(qtd) as total_Corte\n" +
                                                "from corte c\n" +
                                                "WHERE c.tipo_pedido = '"+tipo_pedido+"'\n" +
                                                "group by c.codigo, c.cor\n" +
                                                "order by codigo)\n" +
                                                "AS RELATORIO_VENDAS, produtos pro\n" +
                                                "where relatorio_vendas.codigo = pro.codigo\n" +
                                                "GROUP BY pro.descricao, relatorio_vendas.CODIGO, COR ORDER BY  CODIGO, COR;");
                    
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar relatorio vendas dos pedidos selecionados");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar vendas dos n_pedidos selecionados











        //usa no fechamento e no produtos
        public ResultSet relatorio_produtos() {
                try {
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select pro.descricao, pro.codigo from produtos pro\n" +
                                            "order by pro.descricao, pro.codigo");
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar relatorio produtos");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar pedidos   
        
        
        //usa no fechamento e no produtos / Produto Ficha
        public ResultSet ficha_corte(String codigo, String corte) {
                try {
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("select data_corte, codigo, cor, qtd, corte from corte where codigo = '"+codigo+"' and corte = '"+corte+"';");
                    Conexao.CloseDB();
                    return rs;
		} catch (Exception e) {
			System.out.println("Erro ao Listar relatorio produtos");
                        System.err.println(e.getMessage());
		}
                ResultSet rs = null;
		return rs;
	}//fim listar pedidos   
        
       
        
        
        
}