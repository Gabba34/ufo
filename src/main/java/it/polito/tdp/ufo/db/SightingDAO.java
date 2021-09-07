package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
	/*
	String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root";
	
	La connessione la creeremo su una classe a parte, soprattutto quando si hanno più DAO
	*/
	public List<String> readShapes(){
		
		try{
			Connection connection = DBConnect.getConnection();
			
			String sql ="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC ";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<String> formeUFO = new ArrayList<>(); 
			
			while( result.next() ) {
				String forma = result.getString("shape");
				formeUFO.add(forma);
			}
			
			statement.close();
			connection.close(); 

			return formeUFO;

		} catch (SQLException e) {
			throw new RuntimeException("Database error in readShapes",e);
		}
	}
	
	public int countByShape(String shape) {
		
		try {
			Connection connection = DBConnect.getConnection();

			String sql2 = "SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape = ? ";
			String scelta = shape;
		
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, scelta);
			
			ResultSet result2 = statement2.executeQuery();
			result2.first();
			int count = result2.getInt("cnt");
			statement2.close();
			connection.close(); 

			return count;

		} catch (SQLException e) {
			throw new RuntimeException("Database error in countByShape",e);
		}
	}
	
	
	
}
