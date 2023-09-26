package co.edu.politecncointernacionl.crud_JDBC.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrudApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomEmp_C;
	private JTextField txtApeEmp_C;
	private JTextField txtEmailEmp_C;
	private JTextField txtIdEmp_R;
	private JTextField txtNomEmp_U;
	private JTextField txtApeEmp_U;
	private JTextField txtEmailEmp_U;
	private JTextField txtNomEmp_D;
	private JTextField txtIdEmp_U;
	
	
	
	/**
	 * Create the frame.
	 */
	public CrudApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane pnCRUD = new JTabbedPane(JTabbedPane.TOP);
		pnCRUD.setBounds(12, 22, 416, 236);
		contentPane.add(pnCRUD);
		
		JPanel pnCreate = new JPanel();
		pnCRUD.addTab("Registrar", null, pnCreate, null);
		pnCreate.setLayout(null);
		
		JLabel lblNomEmp_C = new JLabel("Nombre Empleado");
		lblNomEmp_C.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblNomEmp_C.setBounds(12, 12, 170, 30);
		pnCreate.add(lblNomEmp_C);
		
		txtNomEmp_C = new JTextField();
		txtNomEmp_C.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtNomEmp_C.setColumns(10);
		txtNomEmp_C.setBounds(12, 44, 170, 30);
		pnCreate.add(txtNomEmp_C);
		
		JLabel lblApeEmp_C = new JLabel("Apellido Empleado");
		lblApeEmp_C.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblApeEmp_C.setBounds(12, 74, 170, 30);
		pnCreate.add(lblApeEmp_C);
		
		txtApeEmp_C = new JTextField();
		txtApeEmp_C.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtApeEmp_C.setColumns(10);
		txtApeEmp_C.setBounds(12, 106, 170, 30);
		pnCreate.add(txtApeEmp_C);
		
		JLabel lblCorreoEmp_C = new JLabel("Correo empleado");
		lblCorreoEmp_C.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblCorreoEmp_C.setBounds(12, 137, 170, 30);
		pnCreate.add(lblCorreoEmp_C);
		
		txtEmailEmp_C = new JTextField();
		txtEmailEmp_C.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtEmailEmp_C.setColumns(10);
		txtEmailEmp_C.setBounds(12, 169, 170, 30);
		pnCreate.add(txtEmailEmp_C);
		
		JButton btnCreate = new JButton("Registrar Empleado");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3308/Empresa",
							"root", "polint2023");
					Statement comando = conexion.createStatement();
					String nombre = txtNomEmp_C.getText();
					String apellido = txtApeEmp_C.getText();
					String correo = txtEmailEmp_C.getText();
					System.out.println("el nombre del empleado es " + txtNomEmp_C.getText() + apellido
							+ "y el correo es " + correo);

					comando.executeUpdate(
							"insert into empleados(nombreEmp,apellidoEmp,emailEmp) values ('" + nombre + "','"
									+ apellido + "','" + txtEmailEmp_C.getText() + "')");
				} catch (SQLException ex) {
					System.out.println("error en SQl: " + ex);
				}
				txtNomEmp_C.setText("");
				txtApeEmp_C.setText("");
				txtEmailEmp_C.setText("");
			}
		});
		btnCreate.setBounds(247, 107, 162, 27);
		pnCreate.add(btnCreate);
		
		JPanel pnRead = new JPanel();
		pnCRUD.addTab("Consultar", null, pnRead, null);
		pnRead.setLayout(null);
		
		JLabel lblIdEmp_R = new JLabel("Id Empleado");
		lblIdEmp_R.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblIdEmp_R.setBounds(12, 12, 170, 30);
		pnRead.add(lblIdEmp_R);
		
		txtIdEmp_R = new JTextField();
		txtIdEmp_R.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtIdEmp_R.setColumns(10);
		txtIdEmp_R.setBounds(12, 44, 170, 30);
		pnRead.add(txtIdEmp_R);
		
		JLabel lblIdEmpResult_R = new JLabel("Resultado");
		lblIdEmpResult_R.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblIdEmpResult_R.setBounds(253, 12, 146, 30);
		pnRead.add(lblIdEmpResult_R);
		

		final JTextArea txtResultEmp_R = new JTextArea();
		txtResultEmp_R.setBounds(203, 44, 196, 151);
		pnRead.add(txtResultEmp_R);
		
		JButton btnRead = new JButton("Consultar Empleado");
		btnRead.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
								
				try {
					Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3308/Empresa",
							"root", "polint2023");
					Statement comando = conexion.createStatement();
					
					ResultSet consulta=comando.executeQuery(
							"select * from empleados where idEmp =" + txtIdEmp_R.getText()+";"
							);
					
					//es necesario para que tome el dato de la consulta
					if (consulta.next()) {
						
						txtResultEmp_R.append(consulta.getString("nombreEmp")+" ");
						txtResultEmp_R.append(consulta.getString("apellidoEmp")+"\n");
						txtResultEmp_R.append(consulta.getString("emailEmp"));
					} 
					
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null,"Ingrese los datos de consulta");
					System.out.println("error en SQl: " + ex);
					
				}
				
				
			}
		});
		btnRead.setBounds(12, 86, 170, 27);
		pnRead.add(btnRead);
		
		JPanel pnUpdate = new JPanel();
		pnCRUD.addTab("Actualizar", null, pnUpdate, null);
		pnUpdate.setLayout(null);
		
		JLabel lblIdEmp_U = new JLabel("Correo Empleado");
		lblIdEmp_U.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblIdEmp_U.setBounds(233, 86, 170, 30);
		pnUpdate.add(lblIdEmp_U);
		
		txtIdEmp_U = new JTextField();
		txtIdEmp_U.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtIdEmp_U.setColumns(10);
		txtIdEmp_U.setBounds(233, 116, 170, 30);
		pnUpdate.add(txtIdEmp_U);
		
		JLabel lblNomEmp_U = new JLabel("Id Empleado");
		lblNomEmp_U.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblNomEmp_U.setBounds(12, 12, 170, 30);
		pnUpdate.add(lblNomEmp_U);
		
		txtNomEmp_U = new JTextField();
		txtNomEmp_U.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtNomEmp_U.setColumns(10);
		txtNomEmp_U.setBounds(12, 44, 170, 30);
		pnUpdate.add(txtNomEmp_U);
		
		JLabel lblApeEmp_U = new JLabel("Nombre Empleado");
		lblApeEmp_U.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblApeEmp_U.setBounds(233, 12, 170, 30);
		pnUpdate.add(lblApeEmp_U);
		
		txtApeEmp_U = new JTextField();
		txtApeEmp_U.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtApeEmp_U.setColumns(10);
		txtApeEmp_U.setBounds(233, 44, 170, 30);
		pnUpdate.add(txtApeEmp_U);
		
		JLabel lblCorreoEmp_U = new JLabel("Apellido Empleado");
		lblCorreoEmp_U.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblCorreoEmp_U.setBounds(12, 86, 170, 30);
		pnUpdate.add(lblCorreoEmp_U);
		
		txtEmailEmp_U = new JTextField();
		txtEmailEmp_U.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtEmailEmp_U.setColumns(10);
		txtEmailEmp_U.setBounds(12, 116, 170, 30);
		pnUpdate.add(txtEmailEmp_U);
		
		JButton btnUpdate = new JButton("Actualizar Empleado");
		btnUpdate.setBounds(144, 168, 162, 27);
		pnUpdate.add(btnUpdate);
		
		JPanel pnDelete = new JPanel();
		pnCRUD.addTab("Borrar", null, pnDelete, null);
		pnDelete.setLayout(null);
		
		JLabel lblIdEmp_D = new JLabel("Id Empleado");
		lblIdEmp_D.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
		lblIdEmp_D.setBounds(12, 12, 170, 30);
		pnDelete.add(lblIdEmp_D);
		
		txtNomEmp_D = new JTextField();
		txtNomEmp_D.setFont(new Font("Liberation Mono", Font.PLAIN, 14));
		txtNomEmp_D.setColumns(10);
		txtNomEmp_D.setBounds(12, 44, 170, 30);
		pnDelete.add(txtNomEmp_D);
		
		final JLabel lblResultado_D = new JLabel("Resultado");
		lblResultado_D.setBounds(12, 99, 389, 58);
		pnDelete.add(lblResultado_D);
		
		JButton btnDelete = new JButton("Borrar Empleado");
		
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				 
				
				try {
					Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3308/Empresa",
							"root", "polint2023");
					Statement comando = conexion.createStatement();
					
					
					ResultSet eliminar=comando.executeQuery(
							"delete from empleados where idEmp =" + txtNomEmp_D.getText()+";"
							);
					
					//es necesario para que tome el dato de la consulta
					if (eliminar.next()) {
						
						lblResultado_D.setText(eliminar.getString("nombreEmp")+ " fue eliminado");
						
					}
					
					 
					
					} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null,"Ingrese los datos de consulta");
					System.out.println("error en SQl: " + ex);
						
				} 
				

				
			}
		});
		btnDelete.setBounds(239, 47, 162, 27);
		pnDelete.add(btnDelete);
		
		
	}
}