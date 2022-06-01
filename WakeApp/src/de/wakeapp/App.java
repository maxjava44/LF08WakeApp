package de.wakeapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.SwingConstants;

import org.json.JSONObject;

import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private static final String DB_URL = "jdbc:h2:" + Paths.get(".").toAbsolutePath().normalize().toString()
            + "/test"
            + ";CIPHER=AES";
	private static final String DB_USER = "sa";
	private static final String DB_PWS = "uwu oof";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWS)) {
			try(Statement stat = conn.createStatement()){
				if (stat.executeUpdate(
		                "CREATE TABLE IF NOT EXISTS DATEN(" +
				                "ID SMALLINT PRIMARY KEY," +
				                "ANKUNFTSZEIT VARCHAR(6)," +
				                "ZEITFERTIGMACHEN SMALLINT," +
				                "WOHNORT VARCHAR(255)," +
				                "ZIELORT VARCHAR(255)," +
				                "TRANSIT BOOL" +
			                ");"
			            ) > 0) {
					
				}
				ResultSet rs = stat.executeQuery("SELECT * FROM DATEN WHERE ID = 0;");
				if(!rs.next()) {
					stat.executeUpdate("INSERT INTO DATEN (ID,ANKUNFTSZEIT,ZEITFERTIGMACHEN,WOHNORT,ZIELORT,TRANSIT) VALUES(0,'12:00',0,'','',true);");
				}} catch(SQLException e) {
					e.printStackTrace();
				}
			try(Statement stat = conn.createStatement()){
				ResultSet rs = stat.executeQuery("SELECT * FROM DATEN WHERE ID = 0;");
				rs.next();
				String ankunftszeit = rs.getString("ANKUNFTSZEIT");
				int zeitzumfertigmachen = rs.getInt("ZEITFERTIGMACHEN");
				String wohnort = rs.getString("WOHNORT");
				String zielort = rs.getString("ZIELORT");
				boolean transit = rs.getBoolean("TRANSIT");
				frame = new JFrame();
				frame.setBounds(100, 100, 539, 343);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(new BorderLayout(0, 0));
				
				JLabel lblNewLabel = new JLabel("WakeApp");
				frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(new GridLayout(7, 0, 0, 0));
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				panel_2.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_8 = new JPanel();
				panel_2.add(panel_8);
				panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_1 = new JLabel("Ankunftszeit");
				panel_8.add(lblNewLabel_1);
				
				JPanel panel_9 = new JPanel();
				panel_2.add(panel_9);
				panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				textField = new JTextField(ankunftszeit);
				panel_9.add(textField);
				textField.setColumns(10);
				
				JPanel panel_3 = new JPanel();
				panel.add(panel_3);
				panel_3.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_10 = new JPanel();
				panel_3.add(panel_10);
				panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_2 = new JLabel("Zeit zum Fertigmachen in min");
				panel_10.add(lblNewLabel_2);
				
				JPanel panel_11 = new JPanel();
				panel_3.add(panel_11);
				panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				textField_1 = new JTextField(""+zeitzumfertigmachen);
				panel_11.add(textField_1);
				textField_1.setColumns(10);
				
				JPanel panel_4 = new JPanel();
				panel.add(panel_4);
				panel_4.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_12 = new JPanel();
				panel_4.add(panel_12);
				panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_3 = new JLabel("Wohnort");
				panel_12.add(lblNewLabel_3);
				
				JPanel panel_13 = new JPanel();
				panel_4.add(panel_13);
				panel_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				textField_2 = new JTextField(wohnort);
				panel_13.add(textField_2);
				textField_2.setColumns(10);
				
				JPanel panel_5 = new JPanel();
				panel.add(panel_5);
				panel_5.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_14 = new JPanel();
				panel_5.add(panel_14);
				panel_14.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_4 = new JLabel("Zielort");
				panel_14.add(lblNewLabel_4);
				
				JPanel panel_15 = new JPanel();
				panel_5.add(panel_15);
				panel_15.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				textField_3 = new JTextField(zielort);
				panel_15.add(textField_3);
				textField_3.setColumns(10);
				
				JPanel panel_6 = new JPanel();
				panel.add(panel_6);
				panel_6.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_16 = new JPanel();
				panel_6.add(panel_16);
				panel_16.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_5 = new JLabel("Verkehrsmittel");
				panel_16.add(lblNewLabel_5);
				
				JPanel panel_17 = new JPanel();
				panel_6.add(panel_17);
				panel_17.setLayout(new GridLayout(0, 2, 0, 0));
				
				ButtonGroup g = new ButtonGroup();
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("ÖPNV",transit);
				panel_17.add(rdbtnNewRadioButton);
				
				JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Auto",!transit);
				panel_17.add(rdbtnNewRadioButton_1);
				
				g.add(rdbtnNewRadioButton);
				g.add(rdbtnNewRadioButton_1);
				
				JPanel panel_7 = new JPanel();
				panel.add(panel_7);
				panel_7.setLayout(new GridLayout(0, 1, 0, 0));
				
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_21 = new JPanel();
				panel_1.add(panel_21);
				panel_21.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_8 = new JLabel("Stellen sie ihren Wecker auf diese Uhrzeit");
				panel_21.add(lblNewLabel_8);
				
				JPanel panel_20 = new JPanel();
				panel_1.add(panel_20);
				panel_20.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JLabel lblNewLabel_7 = new JLabel("");
				panel_20.add(lblNewLabel_7);
				
				JButton btnNewButton = new JButton("Berechnen");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Calendar cal = Calendar.getInstance();
							cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(textField.getText().split(":")[0]));
							cal.set(Calendar.MINUTE, Integer.parseInt(textField.getText().split(":")[1]));
							cal.add(Calendar.SECOND, -1 * Utility.getWegzeit(textField_2.getText(), textField_3.getText(),rdbtnNewRadioButton.isSelected(),cal));
							cal.add(Calendar.MINUTE, -1 * Integer.parseInt(textField_1.getText()));
							if(cal.get(Calendar.SECOND) < 10) {
								if(cal.get(Calendar.MINUTE) < 10) {
									lblNewLabel_7.setText("" + cal.get(Calendar.HOUR_OF_DAY) + ":0" + cal.get(Calendar.MINUTE) + ":0" + cal.get(Calendar.SECOND) );
								}else {
									lblNewLabel_7.setText("" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":0" + cal.get(Calendar.SECOND) );
								}
							}else {
								if(cal.get(Calendar.MINUTE) < 10) {
									lblNewLabel_7.setText("" + cal.get(Calendar.HOUR_OF_DAY) + ":0" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) );
								}else {
									lblNewLabel_7.setText("" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) );
								}
							}
							try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWS)) {
								try(Statement stat2 = conn.createStatement()){
									stat2.executeUpdate("DELETE FROM DATEN WHERE ID = 0;");
									stat2.executeUpdate("INSERT INTO DATEN (ID,ANKUNFTSZEIT,ZEITFERTIGMACHEN,WOHNORT,ZIELORT,TRANSIT) VALUES(0,'" + textField.getText() +  "'," + Integer.parseInt(textField_1.getText()) + ",'" + textField_2.getText() +"','" + textField_3.getText() +"'," + rdbtnNewRadioButton.isSelected() + ");");
								}catch (SQLException e1) {
									e1.printStackTrace();
									new Fehler("Übertragung in die DB ist fehlgeschlagen");
								}
							} catch (SQLException e2) {
								e2.printStackTrace();
								new Fehler("Übertragung in die DB ist fehlgeschlagen");
							}
							
						} catch (IOException e1) {
							new Fehler("Request ist fehlgeschlagen");
						} catch (NumberFormatException e2) {
							new Fehler("Falsches Nummerformat bitte nutzen sie Stunde:Minute und die Zeit zum fertigmachen in ganzen Minuten");
						} 
					}
				});
				panel_7.add(btnNewButton);
			}catch (Exception e) {
				e.printStackTrace();
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
}
