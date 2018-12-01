package com.oktaykcr.notetakingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class NoteBlock implements ActionListener{
	private String title;
	private String description;
	private String color;
	
	//NOTES
	//private Note note;
	private ArrayList<Note> notes;
	
	private JPanel panel;
	private JLabel bg;
	private JLabel[] labels;//0:Title and 1:Description Labels
	private JButton[] buttons;//0:Edit and 1:Trash buttons
	
	
	public NoteBlock(String title,String description,String color,ArrayList<Note> notes,JPanel panel){
		this.title=title;
		this.description=description;
		this.color=color;
		this.notes=notes;
		this.panel=panel;
		notes=new ArrayList<Note>();
		
		bg=new JLabel();
		
		//Buttons
		buttons=new JButton[2];//0:Edit and 1:Trash buttons
		addImgButton(0, 9, 3);
		addImgButton(1, 50, 4);
		//Labels
		labels=new JLabel[2];//0:Title and 1:Description Labels
		addLabel(0, title);
		addLabel(1, description);
		
		
		bg.add(labels[0]);
		bg.add(labels[1]);
		bg.add(buttons[0]);
		bg.add(buttons[1]);
		panel.add(bg);
		
		//editButton-trashButton ANIMATIONS
		buttons[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				buttons[0].setIcon(ResourceFactory.icons[27]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				buttons[0].setIcon(ResourceFactory.icons[3]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				buttons[0].setIcon(ResourceFactory.icons[26]);
			}
		});
		buttons[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				buttons[1].setIcon(ResourceFactory.icons[29]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				buttons[1].setIcon(ResourceFactory.icons[4]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				buttons[1].setIcon(ResourceFactory.icons[28]);
			}
		});
		
		labels[0].setBounds(59, 8, 79, 26);//Title
		labels[1].setBounds(22, 37, 190, 41);//Description
		
		switch(color){
			case "default":
				bg.setIcon(ResourceFactory.icons[5]);//GRAY
				break;
			case "red":
				bg.setIcon(ResourceFactory.icons[6]);//RED
				break;
			case "green":
				bg.setIcon(ResourceFactory.icons[7]);//GREEN 
				break;
			case "blue":
				bg.setIcon(ResourceFactory.icons[8]);//BLUE
				break;
			case "yellow":
				bg.setIcon(ResourceFactory.icons[9]);//YELLOW
				break;
		}
		panel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//Edit button
			Main.menu.getPanel().setVisible(false);
			Main.notePanel.openNotePanel(true,this);
		}
		if(e.getSource().equals(buttons[1])){//Trash button
			int dialogButton = javax.swing.JOptionPane.YES_NO_OPTION;
			int dialogResult = javax.swing.JOptionPane.showConfirmDialog (null, "Are you sure you want to remove "+title+" block?","Warning",dialogButton);
			if(dialogResult == javax.swing.JOptionPane.YES_OPTION){
				Main.nNotePanel.getBlocks().remove(this);
				panel.remove(bg);
				panel.revalidate();
				panel.repaint();
				Main.database.deleteBlock(this.getTitle());
			}
		}
	}
	
	private void addImgButton(int index,int y,int iconIndex){
		buttons[index]=new JButton(ResourceFactory.icons[iconIndex]);
		buttons[index].setBounds(226, y, 34, 32);
		buttons[index].addActionListener(this);
		buttons[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		buttons[index].setContentAreaFilled(false);
		buttons[index].setOpaque(false);
		buttons[index].setFocusPainted(false);
	}
	private void addLabel(int index,String text){
		labels[index]=new JLabel();
		labels[index].setText(text);
		labels[index].setForeground(new Color(255,255,255));
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	public void setNotes(ArrayList<Note> notes) {
		this.notes=notes;
	}
}
