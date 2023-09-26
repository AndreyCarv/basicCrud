package co.edu.politecncointernacionl.crud_JDBC.logica;

import java.awt.EventQueue;


import co.edu.politecncointernacionl.crud_JDBC.vista.CrudApp;



public class Crud_Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudApp frame = new CrudApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
