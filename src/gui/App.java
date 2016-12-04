package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import base.Craw;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JSeparator;
import java.awt.Color;

public class App {

	public JFrame frmStatsbisorgScraper;
	public static JTextField textFieldOutputPath;
	public JFileChooser chooser;
	public static String choosertitle ="Select folder..";
	public boolean emptyFolderPath = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStatsbisorgScraper = new JFrame();
		frmStatsbisorgScraper.setTitle("syndics-de-france.com CRAWLER");
		frmStatsbisorgScraper.setResizable(false);
		frmStatsbisorgScraper.setBounds(100, 100, 450, 300);
		frmStatsbisorgScraper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStatsbisorgScraper.getContentPane().setLayout(null);

		JButton btnDownload = new JButton("DOWNLOAD DATA");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text;

				if (emptyFolderPath) {
					text = "You have not specified download location.\n"
							+ "Files will be downloaded in folder where app is located. \nContinue?";
					int dialogResult = JOptionPane.showConfirmDialog(null, text, "Warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult == 0) {
						JOptionPane.showMessageDialog(null, "DO NOT OPEN FILE BEFORE DOWNLOAD IS COMPLETE!!! \n\nYOU WILL BE NOTIFIED WHEN DOWNLOAD IS OVER!!!\n\nIT USUALY TAKES FEW MINUTES FOR DOWNLOAD TO COMPLETE\n\n\n                     CLICK \"OK\" TO START DOWNLOAD");

						Craw.getLinks();
						Craw.getData(textFieldOutputPath.getText());
						
						JOptionPane.showMessageDialog(null, "DOWNLOAD FINISHED");
					}
				}else{
					text = "Files will be downloaded in folder:\n"+textFieldOutputPath.getText()+"\n\tContinue?";
					int dialogResult = JOptionPane.showConfirmDialog(null, text, "WARNING", JOptionPane.YES_NO_OPTION);
					if(dialogResult == 0) {
						
						JOptionPane.showMessageDialog(null, "DO NOT OPEN FILE BEFORE DOWNLOAD IS COMPLETE!!! \n\nYOU WILL BE NOTIFIED WHEN DOWNLOAD IS OVER!!!\n\nIT USUALY TAKES FEW MINUTES FOR DOWNLOAD TO COMPLETE\n\n\n                     CLICK \"OK\" TO START DOWNLOAD");

						Craw.getLinks();
						Craw.getData(textFieldOutputPath.getText());
						
						JOptionPane.showMessageDialog(null, "DOWNLOAD FINISHED");
					}					
				}				
			}
		});
		btnDownload.setBounds(119, 157, 197, 58);
		frmStatsbisorgScraper.getContentPane().add(btnDownload);

		JLabel lblOutputFolder = new JLabel("Download Folder");
		lblOutputFolder.setBounds(22, 34, 111, 23);
		frmStatsbisorgScraper.getContentPane().add(lblOutputFolder);

		textFieldOutputPath = new JTextField();
		textFieldOutputPath.setBackground(Color.WHITE);
		textFieldOutputPath.setEditable(false);
		textFieldOutputPath.setBounds(22, 68, 391, 23);
		frmStatsbisorgScraper.getContentPane().add(textFieldOutputPath);
		textFieldOutputPath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//
				// disable the "All files" option.
				//
				chooser.setAcceptAllFileFilterUsed(false);
				//    
				if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) { 
					textFieldOutputPath.setText(chooser.getSelectedFile().getAbsolutePath()+"\\");
					emptyFolderPath = false;
				}
				else {
					System.out.println("You haven't selected anything ");
				}
			}
		});

		btnBrowse.setBounds(333, 34, 80, 23);
		frmStatsbisorgScraper.getContentPane().add(btnBrowse);

		JSeparator separator = new JSeparator();
		separator.setBounds(24, 23, 389, 7);
		frmStatsbisorgScraper.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 117, 389, 7);
		frmStatsbisorgScraper.getContentPane().add(separator_1);


	}

	public static void deleteFIles(String fileLocation){
		File dir = new File(fileLocation);
		File[] list = dir.listFiles();
		if (list.length > 0) {

			for (File f : list) {
				String temp = f.getName();
				if (!temp.endsWith(".jar")) {
					f.delete();
					System.out.println(temp+" is deleted");
				}
					
			}	
		}else{
			System.out.println("Folder is empty");
		}

	}
}
