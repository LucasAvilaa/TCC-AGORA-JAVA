package control;
 
import java.sql.*; 

public class Conexao {
	
	 private static Connection conexao; 
	 
     public Conexao() throws Exception{
    	 
    	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	 String url="jdbc:sqlserver://localhost:1433;databaseName=AGORA_V6";
    	 conexao = DriverManager.getConnection(url,"sa","123456");
     }
     public Connection getConexao() {
    	 if(conexao != null) { 
    		 return conexao;
    	 }else {
    		 System.out.println("Falha na conexão");
    		 return null; 
    	 }
     }
}    
     
 /*    
     public static void closeConexao(Connection con ) {
    	 try { 
         if(con != null) {        	
				con.close();
			}
         } catch (SQLException e) {				 
				Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,null, e);		
         }
     }
     
     public static void closeConexao(Connection con , PreparedStatement ps) {
    	 closeConexao(con);
    	 
    	 try { 
         if(ps != null) {        	
				ps.close();
			}
         } catch (SQLException e) {				 
				Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,null, e);		
         }
     }
     
     public static void closeConexao(Connection con, PreparedStatement ps, ResultSet rs) {
    	 closeConexao(con, ps);
    	 
    	 try { 
         if(rs != null) {        	
				rs.close();
			}
         } catch (SQLException e) {				 
				Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,null, e);		
         }
     }
     
  */  
