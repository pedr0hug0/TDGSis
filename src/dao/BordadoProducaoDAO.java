/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DTO.BordadoProducaoDTO;
import factory.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PeDr0_HuG0
 */
public class BordadoProducaoDAO {
    
    
    //adicionar bordado
    public boolean adiciona_bordado_producao(BordadoProducaoDTO bordadoproducaoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "insert into bordado_producao (turno, maquina, nome, data_produzido, codigo, cor, pontos, corte, t1, t2, t3, t4, total, defeitos, obs) VALUES " +
					"("+
                                    "'" + bordadoproducaoDTO.getTurno()+ "'," +
                            "'" + bordadoproducaoDTO.getMaquina()+ "'," +
                            "'" + bordadoproducaoDTO.getNome()+ "'," +
                            "'" + bordadoproducaoDTO.getData()+ "'," +
                            "'" + bordadoproducaoDTO.getCodigo()+ "'," +
                            "'" + bordadoproducaoDTO.getCor()+ "'," +
                            "'" + bordadoproducaoDTO.getPontos()+ "'," +
                            "'" + bordadoproducaoDTO.getCorte()+ "'," +
                            "'" + bordadoproducaoDTO.getT1()+ "'," +
                            "'" + bordadoproducaoDTO.getT2()+ "'," +
                            "'" + bordadoproducaoDTO.getT3()+ "'," +
                            "'" + bordadoproducaoDTO.getT4()+ "'," +
                            "'" + bordadoproducaoDTO.getTotal()+ "'," +
                            "'" + bordadoproducaoDTO.getDefeitos()+ "'," +
                            "'" + bordadoproducaoDTO.getObs()+ "'" +
				    ");";		
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
                        
                        //pegando id
                        ResultSet rs = null;
                        rs = stmt.executeQuery("SELECT currval('bordado_producao_id_seq')");
                        while (rs.next()) {
                        bordadoproducaoDTO.setId(rs.getInt("currval"));
                        }//fim pegar id
                        
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao adicionar producao bordado");
                        System.out.println(e.getMessage());
			return false;
		}
    } //fim adiciona producao bordado
    
    
     //listar toda producao
            public List<BordadoProducaoDTO> listarProducaoBordado() {
                ArrayList<BordadoProducaoDTO> bordados = new ArrayList<BordadoProducaoDTO>();
		try {
                    
                    
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    ResultSet rs;
                    rs = stmt.executeQuery("SELECT * FROM bordado_producao order by id desc");
                    
                    System.out.println("SELECT * FROM bordado_producao order by id desc");
		
                        while (rs.next()) {
				BordadoProducaoDTO bordado = new BordadoProducaoDTO();
//turno, maquina, nome, data_produzido, codigo, cor, pontos, corte, t1, t2, t3, t4, total, defeitos, obs
				bordado.setId(rs.getInt("id"));
                                bordado.setTurno(rs.getString("turno"));
                                bordado.setMaquina(rs.getString("maquina"));
                                bordado.setNome(rs.getString("nome"));
                                bordado.setData(rs.getString("data_produzido"));
				bordado.setCodigo(rs.getString("codigo"));
                                bordado.setCor(rs.getString("cor"));
                                bordado.setPontos(rs.getInt("pontos"));
                                bordado.setCorte(rs.getString("corte"));
                                bordado.setT1(rs.getInt("t1"));
                                bordado.setT2(rs.getInt("t2"));
                                bordado.setT3(rs.getInt("t3"));
                                bordado.setT4(rs.getInt("t4"));
                                bordado.setTotal(rs.getInt("total"));
                                bordado.setDefeitos(rs.getInt("defeitos"));
                                bordado.setObs(rs.getString("obs"));
                                
				bordados.add(bordado);
                                                            
			}
			Conexao.CloseDB();
		} catch (Exception e) {
			System.out.println("Erro ao Listar bordados");
                        System.err.println(e.getMessage());
		}
		return bordados;
	}//fim listar bordados
    
    
            
            public boolean excluir(BordadoProducaoDTO bordadoDTO){
		try{
		
                    Conexao.ConectDB();
                    Statement stmt = Conexao.con.createStatement();
                    
                    String comando = "delete from bordado_producao where "+		    		
				    "id = " + bordadoDTO.getId()+";";
                  
			System.out.println(comando);
			stmt.execute(comando.toUpperCase());
			Conexao.con.commit();
			stmt.close();
			Conexao.CloseDB();
                        return true;		
                    
		}
		
		catch (Exception e){
			System.out.println("Erro ao excluir producao bordado");
                        System.err.println(e.getMessage());
			return false;
		}
            } //fim excluir
    
    
}
