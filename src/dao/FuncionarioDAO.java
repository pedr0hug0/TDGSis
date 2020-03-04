/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.FuncionarioDTO;
import factory.Conexao_TDG_SIS;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedr0
 */
public class FuncionarioDAO {
    
    //adicionar funcionario
    public boolean adiciona_funcionario(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "insert into funcionarios (nome, cargo, area) VALUES " +
					"("+				    		
				    "'" + funcionarioDTO.getNome()+ "'," +
				    "'" + funcionarioDTO.getCargo()+ "'," +
				    "'" + funcionarioDTO.getArea()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('funcionarios_id_seq')");
                        while (rs.next()) {
                        funcionarioDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar funcionario");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona funcionario
    
    //listar funcionario de acordo com Nome
            public List<FuncionarioDTO> getFuncionarioPorNome(String nome) {
                ArrayList<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM funcionarios where nome like '%"+nome.toUpperCase()+"%'");
                    
                    System.out.println("SELECT * FROM funcionarios where nome like '"+nome.toUpperCase()+"%'");
		
                        while (rs.next()) {
				FuncionarioDTO funcionario = new FuncionarioDTO();
                                  
				funcionario.setId(rs.getInt("id"));
                                funcionario.setNome(rs.getString("nome"));
				funcionario.setCargo(rs.getString("cargo"));
                                funcionario.setArea(rs.getString("area"));
				funcionarios.add(funcionario);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar funcionario");
                        System.err.println(e.getMessage());
		}
		return funcionarios;
	}//fim listar funcionario especifico    
    
    public boolean excluir_funcionario(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from funcionarios where "+		    		
				    "id = " + funcionarioDTO.getId()+";";
                  
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
    
 
    
    public boolean atualizar_func(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    String comando = "update funcionarios set "+		    		
				    "cargo = '" + funcionarioDTO.getCargo()+ "'," +
                                    "area = '" + funcionarioDTO.getArea()+ "' " +
                                    "where id = "+funcionarioDTO.getId()+ ";";
                  
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
    
    
    
    //**********************BANCO DE HORAS **************
    
    //adicionar funcionario
    public boolean adiciona_hora(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "insert into banco_horas (data_evento, tipo, horas, motivo, assinado, funcionario) VALUES " +
					"("+				    		
				    "'" + funcionarioDTO.getData_evento()+ "'," +
                                    "'" + funcionarioDTO.getTipo()+ "'," +
				    "'" + funcionarioDTO.getHoras()+ "'," +
                                    "'" + funcionarioDTO.getMotivo()+ "'," +
                                    "'" + funcionarioDTO.getAssinado()+ "'," +
				    "'" + funcionarioDTO.getNome()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('banco_horas_id_seq')");
                        while (rs.next()) {
                        funcionarioDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao_TDG_SIS.con.commit();
			stmt.close();
			Conexao_TDG_SIS.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar funcionario");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona funcionario
    
            public boolean excluir_hora(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    
                    String comando = "delete from banco_horas where "+		    		
				    "id = " + funcionarioDTO.getId()+";";
                  
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
    
    
    //listar funcionario de acordo com Nome
            public List<FuncionarioDTO> getBancoHorasPorNome(String nome, String assinado) {
                ArrayList<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    
                    String sql = ("SELECT id, data_evento, tipo, horas, motivo, assinado, funcionario FROM banco_horas "
                                    + "where funcionario like '"+nome.toUpperCase()+"' AND assinado like '"+assinado.toUpperCase()+"';");
                    //removi % do inicio e fim dos likes (erro trazendo varios funcionarios com mesmo inicio de nome.
                    rs = stmt.executeQuery(sql.toUpperCase());
                    
                    System.out.println(sql);
		
                        while (rs.next()) {
				FuncionarioDTO funcionario = new FuncionarioDTO();
                                
				funcionario.setId(rs.getInt("id"));
                                funcionario.setData_evento(rs.getString("data_evento"));
				funcionario.setTipo(rs.getString("tipo"));
                                funcionario.setHoras(rs.getString("horas"));
                                funcionario.setMotivo(rs.getString("motivo"));
                                funcionario.setAssinado(rs.getString("assinado"));
                                funcionario.setNome(rs.getString("funcionario"));
				funcionarios.add(funcionario);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar horas funcionario");
                        System.err.println(e.getMessage());
		}
		return funcionarios;
	}//fim listar HORAS funcionario especifico    
    
    
            
            
        //listar SALDO HORAS funcionario de acordo com Nome
            public List<FuncionarioDTO> getSaldoFuncionario(String nome) {
                ArrayList<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
		try {
                    
                    
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    ResultSet rs;
                    String sql = ("SELECT rel.funcionario, f.area, SUM(rel.Total_Horas_Entrada) as total_entrada , SUM(rel.Total_Horas_Saida) AS total_saida, (SUM(rel.Total_Horas_Entrada)-SUM(rel.Total_Horas_Saida)) as SALDO FROM (\n" +
                                "	SELECT funcionario, SUM(horas) AS Total_Horas_Entrada, '00:00' as Total_Horas_Saida\n" +
                                "	from banco_horas\n" +
                                "	WHERE tipo = 'ENTRADA'\n" +
                                "	group by funcionario\n" +
                                "	UNION\n" +
                                "	select funcionario, '00:00'  AS Total_Horas_Entrada, sum(horas) as Total_Horas_Saida\n" +
                                "	from banco_horas\n" +
                                "	WHERE tipo = 'SAIDA'\n" +
                                "	group by funcionario\n" +
                                "	order by funcionario)\n" +
                                "AS REL, funcionarios f\n" +
                                "where f.nome = rel.funcionario and rel.funcionario like '"+nome.toUpperCase()+"%'\n" +
                                "GROUP BY rel.funcionario, f.area \n" +
                                "ORDER BY rel.funcionario;");
                    
                    
                    rs = stmt.executeQuery(sql.toUpperCase());
                    
                    System.out.println(sql);
		
                        while (rs.next()) {
				FuncionarioDTO funcionario = new FuncionarioDTO();
                                
				funcionario.setNome(rs.getString("funcionario"));
                                funcionario.setArea(rs.getString("area"));
				funcionario.setTotal_entrada(rs.getString("total_entrada"));
                                funcionario.setTotal_saida(rs.getString("total_saida"));
                                funcionario.setSaldo(rs.getString("saldo"));
                                funcionarios.add(funcionario);
                                                            
			}
			Conexao_TDG_SIS.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar saldo funcionarios");
                        System.err.println(e.getMessage());
		}
		return funcionarios;
	}//fim listar HORAS funcionario especifico        
            
            
        //ATUALIZAR CAMPO ASSINADO.
        public boolean atualizarAssinado(FuncionarioDTO funcionarioDTO){
		try{
		
                    Conexao_TDG_SIS.ConectDB();
                    Statement stmt = Conexao_TDG_SIS.con.createStatement();
                    String comando = "update banco_horas set "+		    		
				    "assinado = '" + funcionarioDTO.getAssinado()+ "' " +
				    "where id = "+funcionarioDTO.getId()+ ";";
                  
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
