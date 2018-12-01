package com.oktaykcr.util;

public class Library {
	

	/**
	 * to create the new frame with layeredPane
	 * @param frame JFrame
	 * @param lp JLayeredPane
	 * @param layout Layout of the layered pane
	 * @param name the title name of the frame
	 * @param width the width size of the frame
	 * @param height the height size of the frame
	 * @param visibility the visibility of the frame
	 */
	public void createFrame(javax.swing.JFrame frame,javax.swing.JLayeredPane lp,java.awt.LayoutManager layout,String name,int width,int height,boolean visibility){
		frame.setTitle(name);
		frame.setSize(width,height);
		frame.setVisible(visibility);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.add(lp);
		lp.setLayout(layout);
	}

	/**
	 * to add a opaque text field to a null layout panel 
	 * @param panel null layout JPanel
	 * @param fields an array of JTextField
	 * @param index an index of JTextField array
	 * @param x position of x of JTextField
	 * @param y position of y of JTextField
	 * @param width the width size of JTextField
	 * @param height the height size of JTextField
	 */
	public void addTextField(javax.swing.JPanel panel,javax.swing.JTextField[] fields,int index,int x,int y,int width,int height ){
		fields[index]=new javax.swing.JTextField();
		panel.add(fields[index]);
		fields[index].setBounds(x, y, width, height);
		fields[index].setOpaque(false);
		fields[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		fields[index].setForeground(java.awt.Color.black);
		fields[index].setCaretColor(java.awt.Color.black);
		fields[index].setHorizontalAlignment(javax.swing.JTextField.CENTER);//caret i ortaya alma
		panel.revalidate();
	}

	/**
	 * addTextLabel
	 * <p>
	 * @param panel add label text into the panel
	 * @param label
	 * @param labelIndex
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 *  */
	public void addTextLabel(javax.swing.JPanel panel,javax.swing.JLabel[] label,int labelIndex,String text,int x,int y,int width,int height){
		label[labelIndex]=new javax.swing.JLabel();
		panel.add(label[labelIndex]);
		label[labelIndex].setText(text);
		label[labelIndex].setBounds(x, y, width, height);
		panel.revalidate();
	}
	/**
	 * addImgLabel<p>
	 * After function resize ImageIcon according to params width and height, function adds into the label.
	 * <p>
	 * @param panel JPanel
	 * @param label JLabel
	 * @param labelIndex index of label array
	 * @param icons ImageIcon 
	 * @param iconIndex index of ImageIcon array
	 * @param x the x position of the label in the panel
	 * @param y the y position of the label in the panel
	 * @param width label width
	 * @param height label height
	 */
	public void addImgLabel(javax.swing.JPanel panel,javax.swing.JLabel[] label,int labelIndex,javax.swing.ImageIcon[] icons,int iconIndex,int x,int y,int width,int height){
		label[labelIndex]=new javax.swing.JLabel();
		panel.add(label[labelIndex]);
		label[labelIndex].setBounds(x, y,width,height);
		java.awt.Image img=icons[iconIndex].getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
		icons[iconIndex]=new javax.swing.ImageIcon(img);
		label[labelIndex].setIcon(icons[iconIndex]);
		panel.revalidate();
	}

	/**
	 * imageIconInit<p>
	 * Create a new package and  change the name of the package to 'Resources'
	 * @param icons ImageIcon array
	 * @param index index of ImageIcon
	 * @param file_name Image name("Image.png")
	 */
	public void imageIconInit(javax.swing.ImageIcon[] icons,int index,String file_name){
		try{
			icons[index]=new javax.swing.ImageIcon(getClass().getClassLoader().getResource(file_name));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * addImgButton <p>
	 * without resize Image
	 * <p>
	 * @param panel
	 * @param button
	 * @param index
	 * @param icons
	 * @param iconIndex
	 * @param al
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void  addImgButton(javax.swing.JPanel panel,javax.swing.JButton[] button,int index,javax.swing.ImageIcon[] icons,int iconIndex,java.awt.event.ActionListener al,int x,int y,int width,int height ){
		button[index]=new javax.swing.JButton(icons[iconIndex]);
		panel.add(button[index]);
		button[index].setBounds(x, y, width, height);
		button[index].addActionListener(al);
		button[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		button[index].setContentAreaFilled(false);
		button[index].setOpaque(false);
		button[index].setFocusPainted(false);
		panel.revalidate();
	}

	public void addRadioButton(javax.swing.JPanel panel,javax.swing.JRadioButton[] radioButton, int index, java.awt.event.ActionListener al, int x, int y, int width, int height) {
		radioButton[index]=new javax.swing.JRadioButton();
		radioButton[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
		radioButton[index].setOpaque(false);
		radioButton[index].setBounds(x, y, width, height);
		radioButton[index].addActionListener(al);
		panel.add(radioButton[index]);
	}

	public class FixedSizeDocument extends javax.swing.text.PlainDocument
	{
	   private int max = 10;
	   public FixedSizeDocument(int max) 
	   { 
	        this.max = max; 
	   } 
	   @Override
	   public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
	      throws javax.swing.text.BadLocationException
	   {   
	      if (getLength()+str.length()>max)  str = str.substring(0, max - getLength());
	      super.insertString(offs, str, a);
	   }
	}

}
