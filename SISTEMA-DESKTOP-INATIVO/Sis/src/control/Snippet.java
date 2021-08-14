package control;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class Snippet {
	Conexao con ;
 
	public boolean crudClientes() throws Exception {
			con = new Conexao();
			String sql = "EXEC PROC_CRUD_CLIENTE ?,?,?,?,?,?,?";
			PreparedStatement ps = con.getConexao().prepareStatement(sql);
			ps.setString(1, "I");
			ps.setString(2, "Lucas");
			ps.setString(3, "Avila");
			ps.setString(4, "01650");
			ps.setString(5, "08880");
	//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	//		String a = "06/02/2002";
	//		Date da = formato.parse(a);
		 
			ps.setString(6, "06/07/2002");
			ps.setString(7,"M"); 
			
			if(ps.executeUpdate()>0) {
				ps.close();
				return true;
				
			}else {
				return false;
			}
	}
}

