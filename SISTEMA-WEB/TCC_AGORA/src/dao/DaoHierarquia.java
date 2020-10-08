package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import factory.Conexao;

public class DaoHierarquia{
 
	Conexao con; 	 
	public List<SelectItem> getListaHierarquia() throws Exception { 
		List<SelectItem> hierarquia = new ArrayList<SelectItem>();
		 
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_HIERARQUIA"); 
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {   
			hierarquia.add(new SelectItem(Integer.parseInt(rs.getString("ID_HIERARQUIA")),rs.getString("CARGO")));
		} 				 
		return hierarquia;
	} 
}	