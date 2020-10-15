package factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

 private static Connection conexao; 
	 
     public Conexao() throws Exception{
    	 
    	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	 String url="jdbc:sqlserver://localhost:1433;databaseName=AGORA_V7";
    	 conexao = DriverManager.getConnection(url,"sa","123456");
     }
     public Connection getConexao() {
    	 if(conexao != null) {  
    		 System.out.println("CONECTADO COM SUCESSO");
    		 return conexao;
    	 }else {
    		 System.out.println("FALHA NA CONEXÃO");
    		 return null; 
    	 }
     }
}