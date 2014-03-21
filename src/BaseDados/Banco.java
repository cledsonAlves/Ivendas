package BaseDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// 127.0.0.1:1521:XE;

/*	
 * Classe cria a conexão com o banco de Dados 
 * 
 */
public class Banco {
	private String driver = "oracle.jdbc.driver.OracleDriver"; // DRIVER ORACLE
	private String url = "jdbc:oracle:thin:@192.92.1.150:1521:WINT";
	private final String USUARIO = "fibra"; // USUARIO DO BANCO
	private final String SENHA = "fibra"; // SENHA DO BANCO
	
	

	// método cria conexão ...
	public Connection criaConexao() {
		  try{
			// conecta
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
			System.out.println("Conectado ao banco !!");
			return conn;
		  } catch(ClassNotFoundException e){
			  
		  }catch( SQLException err){
			  
		  }
			return null;
		
	
	
	}
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	new Banco().criaConexao();
	
}




}
