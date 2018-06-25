package dataReader;

import java.sql.*;

import base.PreAndPost;

public class DbProvider extends AbstractDataProvider{


	public static Object[][] getDataFromDb(String sql){

		// JDBC driver name and database URL
		final String JDBC_DRIVER = PreAndPost.config.getProperty("DB_Pkg");  
		final String DB_URL = PreAndPost.config.getProperty("DB_Url");

		//  Database credentials
		final String USER = PreAndPost.config.getProperty("DB_User");
		final String PASS = PreAndPost.config.getProperty("DB_Pwd");

		Connection conn = null;
		Statement stmt = null;

		Object[][] data = null;

		try{

			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			stmt = conn.createStatement();

			String count = "Select count(*) from ("+sql+") AS T";
			ResultSet rs = stmt.executeQuery(count);
			rs.next();
			int rowCount = rs.getInt(1);

			// Now run the query
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd=rs.getMetaData();

			// get the column count
			int columnCount = rsmd.getColumnCount();
			
			data = new Object[rowCount][columnCount]; // assign to the data provider array
			int i = 0;
			
			//STEP 5: Extract data from result set
			while(rs.next()){

				for (int j = 1; j <= columnCount; j++) {
					switch (rsmd.getColumnType(j)) {
					case Types.VARCHAR:
						data[i][j-1] = rs.getString(j);
						break;
					case Types.NULL:
						data[i][j-1] = "";
						break;
					case Types.CHAR:
						data[i][j-1] = rs.getString(j);
						break;
					case Types.TIMESTAMP:
						data[i][j-1] = rs.getDate(j);
						break;
					case Types.DOUBLE:
						data[i][j-1] = rs.getDouble(j);
						break;
					case Types.INTEGER:
						data[i][j-1] = rs.getInt(j);
						break;
					case Types.SMALLINT:
						data[i][j-1] = rs.getInt(j);
						break;

					}
				}
				i++;
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException se){

			//Handle errors for JDBC
			se.printStackTrace();

		}catch(Exception e){

			//Handle errors for Class.forName
			e.printStackTrace();

		}finally{

			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se){
				se.printStackTrace();
			}

			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return data;


	}



}