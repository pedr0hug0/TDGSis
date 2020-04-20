/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// situa em qual package ou “pacote” está a classe
package factory;

/**
 *
 * @author PeDr0_HuG0
 */
// faz as importações de classes necessárias para o funcionamento do programa
import VIEW.TDG_SIS_VIEW;
import java.sql.Connection; // conexão SQL para Java
import java.sql.DriverManager; // driver de conexão SQL para Java


public class Conexao_TDG_SIS {
    public static Connection con = null;
    
  //PODERIA SER UM ConectDB_TDG_SIS() e um ConectDB_Colecao();
    	public static void ConectDB() {
		try {
			String dsn = TDG_SIS_VIEW.banco_principal; //nome do banco de dados
                        //String dsn = "teste"; //nome do banco de dados
                        
                //BD LOCAL
                        //String dsn = "TDG"; //nome do banco de dados
			String user = TDG_SIS_VIEW.usuario_banco;//nome do usuário utilizado para conectar no banco
			String senha = TDG_SIS_VIEW.senha_banco;//senha do usuário acima informado
			DriverManager.registerDriver(new org.postgresql.Driver());
			String url = "jdbc:postgresql://"+TDG_SIS_VIEW.ip_servidor+":5432/"+dsn; 
			
                 //BD LOCAL
                        //String url = "jdbc:postgresql://localhost:5432/"+dsn; 
			con = DriverManager.getConnection(url,user,senha);
			
			con.setAutoCommit(false); //não deixa como padrão o autocommit no banco
			
			if (con == null) {  //pq se for igual a nulo, deu erro acima
				System.out.println("erro ao abrir o banco");
			}
			else {
				System.out.println("banco aberto");
			}
		} // fecha o try
		//caso ocorra problemas para abrir o banco de dados é emitido a mensagem o console
		catch (Exception e) {
			System.out.println("Problema na abertura de dados "+e.getMessage());
		} //fecha o catch
	} //fecha o método conectDB
	
	//método utilizado para fechar a conexão com o banco de dados
	public static void CloseDB() {
		try {
			System.out.println("banco fechado");
			con.close();
		} //fecha o try
		
		catch (Exception zen) {
			System.out.println("Problema no fechamento da base de dados!"+zen.getMessage());
		} //fecha o catch
	}// fecha o método closeDB	
    
}

