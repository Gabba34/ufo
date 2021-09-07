package it.polito.tdp.ufo.db;

import java.util.List;

public class TestDB {

	public static void main(String[] args) {

		/********************************************************************************************************
		// 1. MySQL Connection URL format
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root";
		
		// 2. Establish the connection
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL); // Creo un oggetto senza new. Connection è un'interfaccia.
			
			// 3. Create a Statement object
			
			// Statement statement = connection.createStatement(); Anche se si usa PreparedStatement
			
			String sql ="SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC ";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// 4. Eseguire una Query (SELECT)
			
			ResultSet result = statement.executeQuery();
			
			// (INSERT/UPDATE/CREATETABLE/SQL statement that doesn't return a resultset) int executeUpdate(String sql)
			// (For general SQL statements) boolean execute(String sql)
			
			/* while( resultSet.next() )
			 * {
			 * 		out.println(
			 * 			resultSet.getInt("ID") + " - " + 
			 * 			resultSet.getString("name") ) ;
			 *//*
			
			// 5. Scandire e trasformare i risultati
			
			List<String> formeUFO = new ArrayList<>(); 
			while( result.next() ) {
				String forma = result.getString("shape");
				formeUFO.add(forma);
			}
			
			statement.close();
			
			System.out.println(formeUFO);
			
			String sql2 = "SELECT COUNT(*) AS cnt "
					+ "FROM sighting "
					+ "WHERE shape = ? ";
			String scelta = "circle";
			
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, scelta);
			ResultSet result2 = statement2.executeQuery();
			result2.first();
			int count = result2.getInt("cnt");
			statement2.close();
			System.out.println("UFO di forma "+scelta+" sono: "+count);
			
			
			// 6. Chiusura connessione
			
			connection.close(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 **********************************************************************************************************************/
		// Tutto questo si trasforma secondo il pattern DAO
		
		
		SightingDAO dao = new SightingDAO();
		
		List<String> shapes = dao.readShapes();
		
		for(String forma: shapes) {
			int count = dao.countByShape(forma);
			System.out.println("UFO di forma "+forma+" sono: "+count);
		}
		
		
		
		
		
	}

}
