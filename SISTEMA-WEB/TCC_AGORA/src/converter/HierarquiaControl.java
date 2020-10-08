package converter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbHierarquia;

public class HierarquiaControl{

	Conexao con; 	
 
	public List<TbHierarquia> getListaHierarquia() throws Exception {
		TbHierarquia h = new TbHierarquia();
		
		List<TbHierarquia> hierarquia = new ArrayList<TbHierarquia>();
		 
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_HIERARQUIA"); 
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {  
			h.setIdHierarquia(Integer.parseInt(rs.getString("ID_HIERARQUIA")));
			h.setCargo(rs.getString("CARGO")); 	
			hierarquia.add(h);
			System.out.println("Arraylist " + hierarquia);
		} 				 
		return hierarquia;
	} 
}	