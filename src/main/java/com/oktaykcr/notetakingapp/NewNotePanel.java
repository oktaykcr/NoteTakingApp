package com.oktaykcr.notetakingapp;



import com.oktaykcr.util.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NewNotePanel extends Library implements ActionListener {
	private JPanel panel;
	
	private NoteBlock block;
	private ArrayList<NoteBlock> blocks;
	
	private String[] colors={"Default","Red","Green","Blue","Yellow"};
	private JComboBox<String> cb; 
	
	private JLabel[] labels;private int labelSize=1;//bg
	private JButton[] buttons;private int buttonSize=2;//0:Create 1:Back
	private JTextField[] fields;private int fieldSize=2;//0:Title 1:Description
	

	private void init(){
		//New note panel 
		panel=new JPanel();
		panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
		panel.setLayout(null);
		
		//JComboBox
		cb=new JComboBox<String>(colors);
		panel.add(cb);
		
		//ARRAYS
		labels=new JLabel[labelSize];
		buttons=new JButton[buttonSize];
		fields=new JTextField[fieldSize];
		blocks=new ArrayList<NoteBlock>();
		
	}
	
	public NewNotePanel(boolean visibility){
		init();
		panel.setVisible(visibility);
		cb.setBounds(55, 283, 201, 32);
		addTextField(panel, fields, 1, 56, 198, 201, 32);fields[1].setDocument(new FixedSizeDocument(26));//DESCRIPTION TEXT FIELD
		addTextField(panel, fields, 0, 56, 113, 201, 32);fields[0].setDocument(new FixedSizeDocument(11));//TITLE TEXT FIELD
		addImgButton(panel, buttons, 1, ResourceFactory.icons, 13, this, 55, 593, 92, 33);//BACK BUTTON
		addImgButton(panel, buttons, 0, ResourceFactory.icons, 11, this, 164, 593, 92, 33);//CREATE BUTTON
		//backButton-createButton ANIMATIONS
		buttons[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				buttons[0].setIcon(ResourceFactory.icons[25]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				buttons[0].setIcon(ResourceFactory.icons[11]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				buttons[0].setIcon(ResourceFactory.icons[24]);
			}
		});
		buttons[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				buttons[1].setIcon(ResourceFactory.icons[23]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				buttons[1].setIcon(ResourceFactory.icons[13]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				buttons[1].setIcon(ResourceFactory.icons[22]);
			}
		});
		addImgLabel(panel, labels, 0, ResourceFactory.icons, 10, 0, 0, 308, 662);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//CREATE BUTTON
			if(!(fields[0].getText().isEmpty()) && !(fields[1].getText().isEmpty())){
				for(int i=0;i<blocks.size();i++) {
					if(blocks.get(i).getTitle().equals(fields[0].getText())) {
						JOptionPane.showMessageDialog(panel, "Please enter a different title!","Warning", JOptionPane.WARNING_MESSAGE);
						fields[0].setText("");
						return;
					}
				}
				block=new NoteBlock(fields[0].getText(), fields[1].getText(), cb.getSelectedItem().toString().toLowerCase(), new ArrayList<Note>(), Main.menu.getNoteBlockPanel());
				blocks.add(block);
				Main.database.insert(block.getTitle(), block.getDescription(), block.getColor(), null);
				//RESET
				fields[0].setText("");
				fields[1].setText("");
				cb.setSelectedIndex(0);
				panel.setVisible(false);
				Main.menu.getPanel().setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(panel, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource().equals(buttons[1])){//BACK BUTTON
			//RESET
			fields[0].setText("");
			fields[1].setText("");
			cb.setSelectedIndex(0);
			
			panel.setVisible(false);
			Main.menu.getPanel().setVisible(true);
		}
	}
	public ArrayList<NoteBlock> getBlocks(){
		return this.blocks;
	}
	public void setBlocks(ArrayList<NoteBlock> blocks) {
		this.blocks=blocks;
	}
	public JPanel getPanel() {
		return panel;
	}
}
