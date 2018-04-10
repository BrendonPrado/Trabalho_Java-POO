/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import classes.Produto;
import classes.Venda;
import java.sql.*;
import java.util.ArrayList;
import trabalho.Vendas;

public class tabela {
    
    public void cria_venda() throws ClassNotFoundException{
        Connection c = null;
         Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Vendas.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Vendas" +
                        "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " Nome Text NOT NULL,"+
                        " DATA Text NOT NULL," + 
                        " Total REAL NOT NULL," +
                        "Lucro REAL NOT NULL,"
                    + "Coment Text)"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
                    
        } catch (SQLException e) {
               System.out.println(""+e);
        }
    }
    public ArrayList<Venda> select_vendas(){
        ArrayList<Venda> l = new ArrayList<>();
        Connection c =null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Vendas.db");
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vendas;" );
            
            while(rs.next()){
                int id = rs.getInt("ID");
                String prod = rs.getString("Nome");
                String data = rs.getString("DATA");
                double total = rs.getDouble("Total");
                double lucro = rs.getDouble("Lucro");
                String coment = rs.getString("Coment");
                Venda v = new Venda(id,prod, data, total, lucro,coment);
                l.add(v);  
            }
            c.commit();
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(""+e);                  
        }
        
        return l;
    }



    
    
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
    
    public void insert(String name,double preco_u,int qtd) throws SQLException, ClassNotFoundException{
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
            		update(name,preco_u,qtd);
            	}else{
            		String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
         	                    "VALUES ('"+name+"',"+preco_u+","+qtd+");";
         	        stmt.executeUpdate(code);
         	        
         	        stmt.close();
         	        c.commit();
         	        c.close();
            		}	        
            }else {
	        String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
	                    "VALUES ('"+name+"',"+preco_u+","+qtd+");";
	        stmt.executeUpdate(code);
	        
	        stmt.close();
	        c.commit();
	        c.close();
            	}
            }           
        	catch (SQLException e) {
        		 String code = "INSERT INTO Estoque(NAME,Preco_u,qtd) " +
 	                    "VALUES ('"+name+"',"+preco_u+","+qtd+");";
 	        stmt.executeUpdate(code);
 	        
 	        stmt.close();
 	        c.commit();
 	        c.close();
        }
    }
    public void update(String name, double preco_u, int qtd) {
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
    	   
    	      
    	      String sql = "UPDATE Estoque set NAME ='"+name+"', Preco_u ="+preco_u+",qtd ="+(q_n+qtd)+" where NAME='"+name+"';";
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


    public void insert_v(String nome, String data, double total, double lucro) throws ClassNotFoundException {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Vendas.db");
            c.setAutoCommit(false);
            String code = "INSERT INTO Vendas(Nome,DATA,Total,Lucro)"
                    + "VALUES('"+nome+"','"+data+"'," + total + "," + lucro +");";
            stmt = c.createStatement();
            stmt.executeUpdate(code);

            stmt.close();
            c.commit();
            c.close();
           
            System.out.println("Opened database successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void update_venda(String id, String coment){
           Connection c = null;
    	   Statement stmt = null;  
    	   try {
              System.out.println("updando");
    	      Class.forName("org.sqlite.JDBC");
    	      c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Vendas.db");
    	      System.out.println("Opened database successfully");
    	      stmt = c.createStatement();
    	      String sql;
              sql = "UPDATE Vendas set Coment = '"+coment+"' where ID = "+id+";";

    	      stmt.executeUpdate(sql);
    	      
    	   
    	      stmt.close();
    	      c.close();
    	   }
    	      catch ( Exception e ) {
    	    	  System.out.println(e);
    	      }
	}
   public ArrayList<Venda> lucra() throws ClassNotFoundException{
        ArrayList<Venda> l = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\db\\Vendas.db");
             System.out.println("Opened database successfully");
             stmt = c.createStatement();
             
             ResultSet rs = stmt.executeQuery("Select Nome,sum(Lucro) from Vendas GROUP BY Nome ORDER BY Lucro Desc;" );
             while(rs.next()){
                 String nome = rs.getString("Nome");
                 double lucro = rs.getDouble("sum(Lucro)");
                 Venda v = new Venda(nome,lucro);
                 l.add(v);
             }
             c.close();
             stmt.close();
             rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return l;
   }
}
