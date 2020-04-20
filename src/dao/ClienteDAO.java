/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.ClienteDTO;
import factory.Conexao_TDG_SIS;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class ClienteDAO {
    
    //adicionar cliente
    public boolean adiciona_cliente(ClienteDTO clienteDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "insert into clientes (codigo, razao, fantasia) VALUES " +
					"("+				    		
				    "'" + clienteDTO.getCodigo()+ "'," +
				    "'" + clienteDTO.getRazao()+ "'," +
				    "'" + clienteDTO.getFantasia()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('clientes_id_seq')");
                        while (rs.next()) {
                        clienteDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar cliente");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona cliente
    
    //listar cliente de acordo com codigo
            public List<ClienteDTO> getClientePorCodigo(String codigo) {
                ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM clientes where codigo = "+codigo+" order by id desc");
                    
                    System.out.println("SELECT * FROM clientes where codigo = "+codigo+" order by id desc");
		
                        while (rs.next()) {
				ClienteDTO cliente = new ClienteDTO();
                                  
				cliente.setId(rs.getInt("id"));
                                cliente.setCodigo(rs.getInt("codigo"));
				cliente.setRazao(rs.getString("razao"));
                                cliente.setFantasia(rs.getString("fantasia"));
				clientes.add(cliente);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar cliente");
                        System.err.println(e.getMessage());
		}
		return clientes;
	}//fim listar cliente especifico
    
            
            
        //listar cliente de acordo com RAZAO
            public List<ClienteDTO> getClientePorRazao(String razao) {
                ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM clientes where razao like '%"+razao.toUpperCase()+"%'");
                    
                    System.out.println("SELECT * FROM clientes where razao like '"+razao.toUpperCase()+"%'");
		
                        while (rs.next()) {
				ClienteDTO cliente = new ClienteDTO();
                                  
				cliente.setId(rs.getInt("id"));
                                cliente.setCodigo(rs.getInt("codigo"));
				cliente.setRazao(rs.getString("razao"));
                                cliente.setFantasia(rs.getString("fantasia"));
				clientes.add(cliente);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar cliente");
                        System.err.println(e.getMessage());
		}
		return clientes;
	}//fim listar cliente especifico    
            
            
    
            
            //listar todos clientes
            public List<ClienteDTO> listarClientes() {
                ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM clientes order by id desc");
                    
                    System.out.println("SELECT * FROM clientes order by id desc");
		
                        while (rs.next()) {
				ClienteDTO cliente = new ClienteDTO();

				cliente.setId(rs.getInt("id"));
                                cliente.setCodigo(rs.getInt("codigo"));
				cliente.setRazao(rs.getString("razao"));
                                cliente.setFantasia(rs.getString("fantasia"));
				clientes.add(cliente);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar clientes");
                        System.err.println(e.getMessage());
		}
		return clientes;
	}//fim listar clientes
    
    
            
            public boolean excluir(ClienteDTO clienteDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from clientes where "+		    		
				    "id = " + clienteDTO.getId()+";";
                  
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
            
            
}//fim dao
