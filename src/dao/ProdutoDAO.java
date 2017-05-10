/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author PeDr0_HuG0
 */
import factory.Conexao;
import DTO.ProdutoDTO;
import factory.Conexao_TDG_SIS;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO{
    
  private Connection connection;        
  Integer id;
  String codigo;
  String descricao;
  Integer qtd_cores;
  Integer qtd_tamanho;
  Integer bordado;
  Integer estampa;
  Integer bolso;
  String tipo_tamanho;
  Integer costas;
  Integer manga;
  Integer especial;
  

    public boolean adiciona(ProdutoDTO produtoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    String comando = "insert into produtos(codigo, descricao, qtd_cores, QTD_TAMANHO, bordado, estampa, bolso, tipo_tamanho, costas, manga, rendimento, especial) VALUES " +
					"("+				    		
				    "'" + produtoDTO.getCodigo()+ "'," +
				    "'" + produtoDTO.getDescricao()+ "'," +
				    "'" + produtoDTO.getQtd_cores()+ "'," +
				    "'" + produtoDTO.getQtd_tamanho()+ "'," +
				    "'" + produtoDTO.getBordado()+ "'," +
                                    "'" + produtoDTO.getEstampa()+ "'," +
                                    "'" + produtoDTO.getBolso()+ "'," +
                                    "'" + produtoDTO.getTipo_tamanho()+ "'," +
                                    "'" + produtoDTO.getCostas()+ "'," +
                                    "'" + produtoDTO.getManga()+ "'," +
                                    "'" + produtoDTO.getRendimento()+ "'," +
                                    "'" + produtoDTO.getEspecial()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('produtos_id_seq')");
                        while (rs.next()) {
                        produtoDTO.setId(rs.getInt("currval"));
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
    } //fim adiciona
    
 
    
    
    public boolean alterar(ProdutoDTO produtoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "update produtos set "+		    		
				    "codigo = '" + produtoDTO.getCodigo()+ "'," +
				    "descricao = '" + produtoDTO.getDescricao()+ "'," +
				    "qtd_cores = " + produtoDTO.getQtd_cores()+ "," +
				    "qtd_tamanho = " + produtoDTO.getQtd_tamanho()+ "," +
				    "bordado = " + produtoDTO.getBordado()+ "," +
                                    "estampa = " + produtoDTO.getEstampa()+ "," +
                                    "bolso = " + produtoDTO.getBolso()+ "," +
                                    "tipo_tamanho = '" + produtoDTO.getTipo_tamanho()+ "', " +
                                    "costas = " + produtoDTO.getCostas()+ "," +
                                    "manga = " + produtoDTO.getManga()+ "," +
                                    "rendimento = " + produtoDTO.getRendimento()+ "," +
                                    "especial = " + produtoDTO.getEspecial()+ " " +
				    "where id = '"+produtoDTO.getId()+ "';";
                  
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
  
  
        //listar todos
            public List<ProdutoDTO> getProdutos() {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM produtos order by descricao, codigo");
                    
                    System.out.println("SELECT * FROM produtos order by descricao, codigo");
		
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
                                produto.setDescricao(rs.getString("descricao"));
				produto.setQtd_cores(rs.getInt("qtd_cores"));
                                produto.setQtd_tamanho(rs.getInt("qtd_tamanho"));
                                produto.setBordado(rs.getInt("bordado"));
                                produto.setEstampa(rs.getInt("estampa"));
                                produto.setBolso(rs.getInt("bolso"));
                                produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                produto.setCostas(rs.getInt("costas"));
                                produto.setManga(rs.getInt("manga"));
                                produto.setRendimento(rs.getDouble("rendimento"));
                                produto.setEspecial(rs.getInt("especial"));
				produtos.add(produto);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar Produto");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim getProdutos
            
            
        //pesquisar por id
        public ProdutoDTO getProdutoById(Integer id) {
        
        ProdutoDTO produto = new ProdutoDTO();
        String listarbyid = "SELECT * FROM produtos where id = ?";
        try {
            Conexao.ConectDB();
            PreparedStatement pstmt = Conexao.con.prepareStatement(listarbyid);
            ResultSet rs;
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            System.out.println(rs);
            
            while (rs.next()) {
                produto.setId(rs.getInt("id"));
		produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
		produto.setQtd_cores(rs.getInt("qtd_cores"));
                produto.setQtd_tamanho(rs.getInt("qtd_tamanho"));
                produto.setBordado(rs.getInt("bordado"));
                produto.setEstampa(rs.getInt("estampa"));
                produto.setBolso(rs.getInt("bolso"));
                produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                produto.setCostas(rs.getInt("costas"));
                produto.setManga(rs.getInt("manga"));
                produto.setRendimento(rs.getDouble("rendimento"));
                produto.setEspecial(rs.getInt("especial"));
            }
            Conexao.CloseDB();
        } catch (Exception e) {
            System.out.println("Erro ao Listar Produto By ID");
            System.out.println(e.getMessage());
        }
        return produto;
    }//fim buscar por id

   
        
        //pesquisar por ref
        public List<ProdutoDTO> getProdutosRef(String ref) {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM produtos where codigo like '"+ref+"%' order by codigo");
                    
                    System.out.println("SELECT * FROM produtos where codigo like '"+ref+"%' order by codigo");
		
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
                                produto.setDescricao(rs.getString("descricao"));
				produto.setQtd_cores(rs.getInt("qtd_cores"));
                                produto.setQtd_tamanho(rs.getInt("qtd_tamanho"));
                                produto.setBordado(rs.getInt("bordado"));
                                produto.setEstampa(rs.getInt("estampa"));
                                produto.setBolso(rs.getInt("bolso"));
                                produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                produto.setCostas(rs.getInt("costas"));
                                produto.setManga(rs.getInt("manga"));
                                produto.setRendimento(rs.getDouble("rendimento"));
                                produto.setEspecial(rs.getInt("especial"));
				produtos.add(produto);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao listar produto por ref");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim pesquisar por ref
        
        
        
        
  //Atualizar Produto após clicar em Editar na lista
 
 public void atualizar(ProdutoDTO produto) {
     String sql = "update produtos set codigo = ?,descricao = ?, qtd_cores = ?, qtd_tamanho = ?, bordado = ?, estampa = ?, bolso = ?, tipo_tamanho = ?, costas = ?, manga = ?, especial = ? where id = ?;";
     if (produto != null) {
            Connection con = null;
            try {
            Conexao.ConectDB();
            PreparedStatement pstmt = Conexao.con.prepareStatement(sql);
            
                pstmt.setInt(1, id);
                pstmt.setString(2, codigo);
                pstmt.setString(3, descricao);
		pstmt.setInt(4, qtd_cores);
                pstmt.setInt(5, qtd_tamanho);
                pstmt.setInt(6, bordado);
                pstmt.setInt(7, estampa);
                pstmt.setInt(8, bolso);
                pstmt.setString(9, tipo_tamanho);
                pstmt.setInt(10, costas);
                pstmt.setInt(11, manga);
                pstmt.setInt(12, especial);
            
                pstmt.execute();
                System.out.println("Alterado com sucesso");
                System.out.println(pstmt.execute());
                
                Conexao.CloseDB();

            } catch (Exception e) {
               System.out.println("Erro ao alterar, erro: " + e.getMessage());

            }
        } else {
            System.out.println("Erro ao alterar, parametro vazio");
        }


    }//fim atualizar
   


 
  public boolean excluir(ProdutoDTO produtoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from produtos where "+		    		
				    "id = " + produtoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim excluir
   
  
  //listar produtos DESCRICOES
            public List<ProdutoDTO> ListarDescricao() {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs = null;
                    String sql;
                   
                    sql = "select distinct descricao from produtos order by descricao;";
                    rs = stmt.executeQuery(sql);
                    System.out.println(sql);
                    
                    while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();
                                produto.setDescricao(rs.getString("descricao"));
                                produtos.add(produto);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar descricoes");
                        System.err.println(e.getMessage());
		}
		return produtos;
	}//fim listar descricao produtos
    
  
 //adiciona categorai em TDG_SIS 
 public boolean adiciona_categoria(ProdutoDTO produtoDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    String comando = "insert into produto_categoria(categoria, rendimento, tipo_tamanho, grade) VALUES " +
					"("+				    		
				    "'" + produtoDTO.getCategoria()+ "'," +
				    "'" + produtoDTO.getRendimento()+ "'," +
                                    "'" + produtoDTO.getTipo_tamanho()+ "'," +
                                     "'" + produtoDTO.getGrade()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('produto_categoria_id_seq')");
                        while (rs.next()) {
                        produtoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar categoria");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona Categoria 
  
  
            
   //listar Categorias Cadastradas
            public List<ProdutoDTO> listarCategorias() {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM produto_categoria order by categoria");
                    
                    System.out.println("SELECT * FROM produto_categoria order by categoria");
		
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

				produto.setId(rs.getInt("id"));
				produto.setCategoria(rs.getString("categoria"));
                                produto.setRendimento(rs.getDouble("rendimento"));
				produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                produto.setGrade(rs.getString("grade"));
                                produtos.add(produto);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar Categorias Produto");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim Listar Categorias      
        
            
        public boolean excluir_categoria(ProdutoDTO produtoDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from produto_categoria where "+		    		
				    "id = " + produtoDTO.getId()+ ";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim excluir
        
    //get Dados Categoria
            public List<ProdutoDTO> getDadosCategorias(String categoria) {
                ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM produto_categoria where categoria = '"+categoria+"';");
                    
                    System.out.println("SELECT * FROM produto_categoria where categoria = '"+categoria+"';");
		
                        while (rs.next()) {
				ProdutoDTO produto = new ProdutoDTO();

				produto.setId(rs.getInt("id"));
				produto.setCategoria(rs.getString("categoria"));
                                produto.setRendimento(rs.getDouble("rendimento"));
				produto.setTipo_tamanho(rs.getString("tipo_tamanho"));
                                produto.setGrade(rs.getString("grade"));
                                produtos.add(produto);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao pegar dados categoria");
                        System.out.println(e.getMessage());
		}
		return produtos;
	}//fim get Dados Categoria    
        
            
   /*exportar para excel
  public void relatorioProdutos() {
                try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    stmt.execute("copy (select * from produtos order by codigo) to 'D:/TDG/Relatorios/produtos.csv' with csv header");
		
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao criar relatorio dos Produto");
                        System.out.println(e.getMessage());
		}
		
	}//fim getProdutos 
  
  */
  
  
  
    
  } //fim dao

    

    
