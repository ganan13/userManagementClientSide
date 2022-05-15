package resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.dbConnection;

public class user {
	//DBConnection object to connect to database
		DBConnection dbConnect = new DBConnection();
		String dbErrorMessage = "Error while connecting to the database for reading.";
		
		// ---Method to insert the consumption details---
		public String addUser(String userID, String month, String premonreading, String curmonreading){
			String output = "";
		
			try{

				Connection con = dbConnect.connect();
				if (con == null){
					return dbErrorMessage; 	
				}
				// create a prepared statement
				String query = " insert into users(userID, name, dob, nicNo, phoneNo,email,address,password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				preparedStmt.setString(1, userID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, dob);
				preparedStmt.setString(4, nicNo);
				preparedStmt.setString(5, phoneNo);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, address);
				preparedStmt.setString(8, password);
				
				
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newUser = readUser();
				output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
				
			}catch (Exception e){
				output = "{\"status\":\"error\", \"data\":\"Error while launching the user\"}";
				System.err.println(e.getMessage());
			}
				
			return output;
			
		}
		
		// ---Method to update a consumption details---
		public String updateUser(String userID, String name, String dob, String nicNo, String phoneNo, String email, String address, String password){
			
			String output = "";
			try{
				
				Connection con = dbConnect.connect();
				if (con == null){
					return dbErrorMessage; 	
				}
			
				
			
				String query = "UPDATE users SET  userID=?, name=?, dob=?, nicNo=?, phoneNo=?, email=?, address=?, password=? WHERE userID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
				preparedStmt.setString(1, userID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, dob);
				
				preparedStmt.setString(4, nicNo);
				preparedStmt.setString(5, phoneNo);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, address);
				preparedStmt.setString(8, password);
				
				
				
			
				// execute the statement
				preparedStmt.execute();
			
				con.close();
				
				String newUser = readUser();
				output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
			
			}catch (Exception e){
				output = "{\"status\":\"error\", \"data\":\"Error while updating the user\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		// ---Method to delete a consumption detail---
		public String deleteUser(String userId){
			String output = "";
			try{
				
				Connection con = dbConnect.connect();
				if (con == null){
					return dbErrorMessage; 	
				}
				// create a prepared statement
				String query = "DELETE FROM users WHERE userId=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, userId);
				
				// execute the statement
				preparedStmt.execute();

				con.close();
				
				String newUser = readUser();
				output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
				
			}catch (Exception e){
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the user\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		// ---Method to read all Consumption---
		public String readUser(){
			String output = "";
			try{
				Connection con = dbConnection.connect();
				if (con == null){
					return dbErrorMessage; 	
				}
				output = "<table border='1'><tr><th>User ID</th>" +
						"<th>Name</th>"+
						"<th>date of birth</th>" +
						"<th>Nic No</th>" +
						"<th>Phone number</th>"+
						"<th>mail addrress</th>"+
						"<th>Address</th>"+
						"<th>Password</th>"+
						"<th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from users";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()){
					String userID = rs.getString("userID");
					String name = rs.getString("name");
					String dob = rs.getString("dob");
					String nicNo = rs.getString("nicNo");
					String phoneNo = rs.getString("phoneNo");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String password = rs.getString("password");
				
					
					output += "<td>" + userID + "</td>";
					output += "<td>" + name + "</td>";
					output += "<td>" + dob + "</td>";
					output += "<td>" + nicNo + "</td>";
					output += "<td>" + phoneNo + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + password + "</td>";
				
					// button for updating a consumption
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-conID='" + userId + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-conID='" + userId + "'></td></tr>";
					
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
				
			}catch (Exception e){
				output = "Error while reading the user.";
				System.err.println(e.getMessage());	
			}
				
			return output;
				
		}
		

}
