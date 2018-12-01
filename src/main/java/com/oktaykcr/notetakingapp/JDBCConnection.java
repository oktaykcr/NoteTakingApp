package com.oktaykcr.notetakingapp;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {
private final String database_url="jdbc:sqlite:notetakingapp.db";
	
	public JDBCConnection() {
		this.createTable("noteBlocks");
	}
	
	private Connection connect() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(database_url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void createTable(String tableName) {
		Connection conn=null;
		String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
				+ "id integer PRIMARY KEY,\n"
				+ "title text NOT NULL,\n"
				+ "description text NOT NULL,\n"
				+ "color text NOT NULL,\n"
				+ "notes text\n"
				+ ");";
		try {
			conn=this.connect();
			Statement st=conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(conn!=null) conn.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<NoteBlock> loadDatas(String tableName) {
		ArrayList<NoteBlock> blocks=new ArrayList<NoteBlock>();
		ArrayList<Note> notes=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		String sql= "SELECT id, title, description, color, notes FROM "+tableName;
		try {
			conn=this.connect();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				String note=rs.getString("notes");
				notes=new ArrayList<Note>();
				if(note!=null) {
					String[] notes_array=note.split("-_-");
					for(int i=1;i<notes_array.length;i++)
						notes.add(new Note(notes_array[i].split("-")[1], Integer.parseInt(notes_array[i].split("-")[0]), Main.notePanel.getNotePanel()));
				}
				blocks.add(new NoteBlock(rs.getString("title"), rs.getString("description"), rs.getString("color"), notes, Main.menu.getNoteBlockPanel()));
				
				/*System.out.println(rs.getInt("id") + "\t" +
								   rs.getString("title") + "\t" +
								   rs.getString("description") + "\t" +
								   rs.getString("color") + "\t" +
								   rs.getString("notes"));	*/			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
		return blocks;
	}
	
	public void insert(String title, String description, String color, String notes) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql= "INSERT INTO noteBlocks(title,description,color,notes) VALUES(?,?,?,?)";
		
		try {
			conn=this.connect();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, description);
			pstmt.setString(3, color);
			pstmt.setString(4, notes);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.getMessage();
			}
		}
	}
	
	public void deleteBlock(String title) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql= "DELETE FROM noteBlocks WHERE title=?";
		
		try {
			conn=this.connect();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null)  conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateNotes(String title,String notes) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql= "UPDATE noteBlocks SET notes=? WHERE title=?";
		
		try {
			conn=this.connect();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, notes);
			pstmt.setString(2, title);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.getMessage();
			}
			
		}
	}
}
