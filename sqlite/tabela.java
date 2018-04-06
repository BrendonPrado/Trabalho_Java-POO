/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import classes.Produto;
import java.sql.*;
import java.util.ArrayList;

public class tabela {
    public void cria() throws ClassNotFoundException{
    	 Connection c = null;
         Statement stmt = null;
        try {
        	Class.forName("org.sqlite.JDBC");
        	c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\tests.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Estoque" +
                        "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " NAME TEXT NOT NULL," + 
                        " Preco_u REAL NOT NULL," +
                        "qtd INT NOT NULL)"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
                    
        } catch (SQLException e) {
               System.out.println(""+e);
        }
    }
    
    public void insert(String name,double preço_u,int qtd) throws SQLException, ClassNotFoundException{
    	Connection c = null;
        Statement stmt = null;
        try {
        	Class.forName("org.sqlite.JDBC");
        	c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\tests.db");
            
        	c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println("Opened database successfully");
            ArrayList<Produto> p = new tabela().select();
            if(p.size()!=0) {
            	ResultSet rs = stmt.executeQuery("Select NAME from Estoque where NAME='"+name+"';");
            	rs.next();String s = rs.getString("NAME");
            	if(s.equals(name)) {
            		stmt.close();
            		c.commit();
            		c.close();
            		update(name,preço_u,qtd);
            	}else{
            		String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
         	                    "VALUES ('"+name+"',"+preço_u+","+qtd+");";
         	        stmt.executeUpdate(code);
         	        
         	        stmt.close();
         	        c.commit();
         	        c.close();
            		}	        
            }else {
	        String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
	                    "VALUES ('"+name+"',"+preço_u+","+qtd+");";
	        stmt.executeUpdate(code);
	        
	        stmt.close();
	        c.commit();
	        c.close();
            	}
            }           
        	catch (SQLException e) {
        		 String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
 	                    "VALUES ('"+name+"',"+preço_u+","+qtd+");";
 	        stmt.executeUpdate(code);
 	        
 	        stmt.close();
 	        c.commit();
 	        c.close();
        }
    }
    private void update(String name, double preço_u, int qtd) {
    	   Connection c = null;
    	   Statement stmt = null;  
    	   try {
    		  System.out.println("updando");
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\tests.db");
    	      System.out.println("Opened database successfully");
    	      stmt = c.createStatement();
    	      
    	      ResultSet rs = stmt.executeQuery("Select qtd from Estoque where NAME='"+name+"';");
    	      rs.next();
    	      int q_n = rs.getInt("qtd");
    	   
    	      
    	      String sql = "UPDATE Estoque set NAME ='"+name+"', Preco_u ="+preço_u+",qtd ="+(q_n+qtd)+" where NAME='"+name+"';";
    	      stmt.executeUpdate(sql);
    	      
    	   
    	      stmt.close();
    	      c.close();
    	   }
    	      catch ( Exception e ) {
    	    	  System.out.println(e);
    	      }
	}
  
	public ArrayList<Produto> select(){
        ArrayList<Produto> l = new ArrayList<>();
        Connection c =null;
        Statement stmt = null;
        try {
        	 Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\tests.db");
             System.out.println("Opened database successfully");

             stmt = c.createStatement();
             
            ResultSet rs = stmt.executeQuery("SELECT * FROM Estoque;" );
            
            while(rs.next()){
                int id = rs.getInt("ID");
                String nome = rs.getString("NAME");
                double preco = rs.getDouble("Preco_u");
                int qtd = rs.getInt("qtd");
                System.out.println(id+"  "+nome);
                Produto p = new Produto(id, nome, preco, qtd);
                l.add(p);
                
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(""+e);                  
        }
        
        System.out.println("chegamos");
        return l;
    }
}
