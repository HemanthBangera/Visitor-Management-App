package com.itep.VisitorManagementSystem;

import java.awt.Image;

import javax.swing.*;

public class Sample extends JFrame{

	Sample()
	{
		
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("img/i1.jpg"));
        Image i2=i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel img=new JLabel(i3);
        add(img);
        
        setSize(400,400);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
	}
	public static void main(String[] args) 
	{
		new Sample();

	}

}
