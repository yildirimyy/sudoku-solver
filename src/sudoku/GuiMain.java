package sudoku;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class GuiMain extends JFrame {

	private JPanel contentPane;
	private JTextArea thread1Text;
	private JTextArea thread2Text;
	private JTextArea thread3Text;
	
	private JPanel thread1Panel;
	private JPanel thread2Panel;
	private JPanel thread3Panel;
	
	//timer adim icin sayac
	private static int sayac1 = 0;
	private static int sayac2 = 0;
	private static int sayac3 = 0;
	
	private static Timer timer1;
	private static Timer timer2;
	private static Timer timer3;
	
	//1000 milisaniye , 1 saniye
	private static long timer1Hiz = 1*1000;
	private static long timer2Hiz = 1*1000;
	private static long timer3Hiz = 1*1000;
	
	//konumlar sınıfından rasgele konum methodu ile konum olusturma	
	public void rastgele_konum() {
		
 	   Main.konumlar = new Konum().rasgele_konum();
	   
 	   thread1Text.setText("\n\nBaslangic: " + Main.konumlar.get(0).getX() + " , " + Main.konumlar.get(0).getY());
 	   thread2Text.setText("\n\nBaslangic: " + Main.konumlar.get(1).getX() + " , " + Main.konumlar.get(1).getY());
 	   thread3Text.setText("\n\nBaslangic: " + Main.konumlar.get(2).getX() + " , " + Main.konumlar.get(2).getY());
 	
	}
	
	//eski cizimleri kaldır
	//yeni cizim olustur
	//yeni cizimi dogrula
	public void sudoku_ciz(boolean success1, boolean success2, boolean success3 ) {
		
		thread1Panel.removeAll();
		thread1Panel
				.add(new SudokuCizim(success1, Main.konumlar.get(0).getX(), Main.konumlar.get(0).getY(), Main.sudoku1.sonucmatris));
		thread1Panel.validate();
		
		thread2Panel.removeAll();
		thread2Panel
				.add(new SudokuCizim(success2, Main.konumlar.get(1).getX(), Main.konumlar.get(1).getY(), Main.sudoku2.sonucmatris));
		thread2Panel.validate();
		
		thread3Panel.removeAll();
		thread3Panel
				.add(new SudokuCizim(success3, Main.konumlar.get(2).getX(), Main.konumlar.get(2).getY(), Main.sudoku3.sonucmatris));
		thread3Panel.validate();
	}

	/**
	 * Create the frame.
	 */
	public GuiMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1059, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		thread1Panel = new JPanel();
		thread1Panel.setBounds(12, 185, 276, 280);
		panel.add(thread1Panel);
		
		thread1Panel.add(new SudokuCizim(true, -1, -1));
		
		thread2Panel = new JPanel();
		thread2Panel.setBounds(294, 185, 276, 280);
		panel.add(thread2Panel);
		
		thread2Panel.add(new SudokuCizim(true, -1, -1));
		
		thread3Panel = new JPanel();
		thread3Panel.setBounds(576, 185, 276, 280);
		panel.add(thread3Panel);
		
		thread3Panel.add(new SudokuCizim(true, -1, -1));
				
		
		JSpinner timerHizlandir = new JSpinner();
		timerHizlandir.setModel(new SpinnerNumberModel(new Float(1), new Float(0), new Float(3), new Float(0.01)));
		
		
		JButton timer1Button = new JButton("Timer Sonuc");
		timer1Button.setBounds(72, 125, 159, 39);
		panel.add(timer1Button);
		
		timer1Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Sudoku cozum = new Sudoku(Main.sudokuMatris);
				
				float spin = (float) timerHizlandir.getValue();
				timer1Hiz = (long) (spin * 1000);
				if(timer1 != null) {
					timer1.cancel();
					timer1.purge();
				}
				timer1 = new Timer();
				sayac1 = 0;
				timer1.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  
						  	if(sayac1 >= Main.thread1Adimlar.size()) {
						  		sayac1 = 0;
						  		timer1.cancel();
						  		timer1.purge();
						  		return;
						  	}else {
							  	Konum item = Main.thread1Adimlar.get(sayac1);
								  
								int x = item.getX();
								int y = item.getY();
								
								cozum.matris[x][y] = item.getVal();

								thread1Panel.removeAll();
								thread1Panel.add(new SudokuCizim(x, y, cozum.matris));
								thread1Panel.validate();
								
								sayac1++;
						  	}
						  

						  
					  }
					}, timer1Hiz, timer1Hiz);
				
								

			}
		});
		timer1Button.setEnabled(false);
		
		
		
		
		JButton timer2Button = new JButton("Timer Sonuc");
		timer2Button.setBounds(358, 125, 159, 39);
		panel.add(timer2Button);
		
		timer2Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Sudoku cozum = new Sudoku(Main.sudokuMatris);
				
				float spin = (float) timerHizlandir.getValue();
				timer2Hiz = (long) (spin * 1000);
				if(timer2 != null) {
					timer2.cancel();
					timer2.purge();
				}
				timer2 = new Timer();
				sayac2 = 0;
				timer2.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  
						  	if(sayac2 >= Main.thread2Adimlar.size()) {
						  		sayac2 = 0;
						  		timer2.cancel();
						  		timer2.purge();
						  		return;
						  	}else {
							  	Konum item = Main.thread2Adimlar.get(sayac2);
								  
								int x = item.getX();
								int y = item.getY();
								
								cozum.matris[x][y] = item.getVal();

								thread2Panel.removeAll();
								thread2Panel.add(new SudokuCizim(x, y, cozum.matris));
								thread2Panel.validate();
								
								sayac2++;
							  
						  	}					  

					  }
					}, timer2Hiz, timer2Hiz);
				
				
				

			}
		});
		timer2Button.setEnabled(false);
		
		
		JButton timer3Button = new JButton("Timer Sonuc");
		timer3Button.setBounds(636, 125, 159, 39);
		panel.add(timer3Button);
		
		timer3Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Sudoku cozum = new Sudoku(Main.sudokuMatris);
				
				float spin = (float) timerHizlandir.getValue();
				timer3Hiz = (long) (spin * 1000);
				if(timer3 != null) {
					timer3.cancel();
					timer3.purge();
				}
				timer3 = new Timer();
				sayac3 = 0;
				timer3.scheduleAtFixedRate(new TimerTask() {
					  @Override
					  public void run() {
						  
						  	if(sayac3 >= Main.thread3Adimlar.size() ) {
						  		sayac3 = 0;
						  		timer3.cancel();
						  		timer3.purge();
						  		return;
						  	}else {
							  	Konum item = Main.thread3Adimlar.get(sayac3);
								  
								int x = item.getX();
								int y = item.getY();
								
								cozum.matris[x][y] = item.getVal();

								thread3Panel.removeAll();
								thread3Panel.add(new SudokuCizim(x, y, cozum.matris));
								thread3Panel.validate();
								
								sayac3++;
						  	}
						 
						  
					  }
					}, timer3Hiz, timer3Hiz);
				
				
				

			}
		});
		timer3Button.setEnabled(false);
		
		
		
		
		
		Font font1 = new Font("Monospace", 1 , 12);
		
		thread1Text = new JTextArea();
		thread1Text.setBounds(35, 30, 220, 80);
		thread1Text.setBackground(getBackground());
		thread1Text.setEditable(false);
		thread1Text.setColumns(10);
		thread1Text.setFont(font1);
		panel.add(thread1Text);
		thread1Text.setText("\n\n  thread 1");
		
		thread2Text = new JTextArea();
		thread2Text.setEditable(false);
		thread2Text.setBackground(getBackground());
		thread2Text.setColumns(10);
		thread2Text.setBounds(320, 30, 220, 80);
		thread2Text.setFont(font1);
		panel.add(thread2Text);
		thread2Text.setText("\n\n  thread 2");

		
		thread3Text = new JTextArea();
		thread3Text.setEditable(false);
		thread3Text.setBackground(getBackground());
		thread3Text.setColumns(10);
		thread3Text.setBounds(595, 30, 220, 80);
		thread3Text.setFont(font1);
		panel.add(thread3Text);
		thread3Text.setText("\n\n  thread 3");

		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		
		
		JButton dosyaAcButton = new JButton("Dosya Ac");
		dosyaAcButton.setFont(font1);
		dosyaAcButton.setFocusable(false);
		dosyaAcButton.setBackground(Color.red);
		dosyaAcButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	               JFileChooser openFile = new JFileChooser();
	               openFile.setCurrentDirectory(new File(System.getProperty("user.dir")));
	               openFile.setPreferredSize(new Dimension(540, 400));
	               openFile.setFileFilter(new FileNameExtensionFilter("*.txt", "txt", "text"));
	               openFile.showOpenDialog(null);
	               Dosya.file = openFile.getSelectedFile();
	               if(Dosya.file != null){
	            	   dosyaAcButton.setEnabled(false);
	            	   Dosya.DosyaOku();
	            	   
	            	   rastgele_konum();

	            	   thread1Panel.removeAll();
	            	   thread2Panel.removeAll();
	            	   thread3Panel.removeAll();
	            	   
	            	   thread1Panel.add(new SudokuCizim(false, Main.konumlar.get(0).getX(), Main.konumlar.get(0).getY()));
	            	   thread1Panel.validate();
	            	   thread2Panel.add(new SudokuCizim(false, Main.konumlar.get(1).getX(), Main.konumlar.get(1).getY()));
	            	   thread2Panel.validate();
	            	   thread3Panel.add(new SudokuCizim(false, Main.konumlar.get(2).getX(), Main.konumlar.get(2).getY()));
	            	   thread3Panel.validate();
	               }
			}
		});
		
		
		JButton threadleriBaslat = new JButton("Threadleri Baslat");
		threadleriBaslat.setFont(font1);
		threadleriBaslat.setBackground(Color.GREEN);
		threadleriBaslat.setFocusable(false);
		threadleriBaslat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Dosya.file != null){
					threadleriBaslat.setEnabled(false);
					
					
					Main.threadleri_baslat();
					
					
					String messageSuccess = "";
					if(Main.thread1Basarili) {
						messageSuccess = "1.Thread Başarılı\n Time: " + Main.thread1Time;
						
						thread1Text.setBackground(Color.GREEN);
						thread2Text.setBackground(Color.RED);
						thread3Text.setBackground(Color.RED);
						
						thread1Text.setText(thread1Text.getText() + "\n\nTime: " + Main.thread1Time + " milisaniye");

						sudoku_ciz(true, false, false);
						
						JOptionPane.showMessageDialog(
						        null, messageSuccess, "Basarili", JOptionPane.INFORMATION_MESSAGE);
						
						timer1Button.setEnabled(true);
						timer2Button.setEnabled(true);
						timer3Button.setEnabled(true);

					}else if(Main.thread2Basarili) {
						messageSuccess = "2.Thread Basarili\n Time: " + Main.thread2Time;
						
						thread1Text.setBackground(Color.RED);
						thread2Text.setBackground(Color.GREEN);
						thread3Text.setBackground(Color.RED);
						
						thread2Text.setText(thread2Text.getText() + "\n\nTime: " + Main.thread2Time + " milisaniye");
						
						sudoku_ciz(false, true, false);
						
						JOptionPane.showMessageDialog(
						        null, messageSuccess, "Basarili", JOptionPane.INFORMATION_MESSAGE);
						
						timer1Button.setEnabled(true);
						timer2Button.setEnabled(true);
						timer3Button.setEnabled(true);
						
					}else if(Main.thread3Basarili) {
						messageSuccess = "3.Thread Basarili\nTime: " + Main.thread3Time;
						
						thread1Text.setBackground(Color.RED);
						thread2Text.setBackground(Color.RED);
						thread3Text.setBackground(Color.GREEN);
						
						thread3Text.setText(thread3Text.getText() + "\n\nTime: " + Main.thread3Time + " milisaniye");
						
						sudoku_ciz(false, false, true);
						
						JOptionPane.showMessageDialog(
						        null, messageSuccess, "Basarili", JOptionPane.INFORMATION_MESSAGE);
						
						timer1Button.setEnabled(true);
						timer2Button.setEnabled(true);
						timer3Button.setEnabled(true);
						
					}else {
						
						JOptionPane.showMessageDialog(
						        null, "Çözüm Bulunamadı", "Başarısız", JOptionPane.ERROR_MESSAGE);
						
						threadleriBaslat.setEnabled(true);
					}
					

				}else{
					JOptionPane.showMessageDialog(
					        null, "Dosya Seçilmedi", "Dosya", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
				
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(timerHizlandir, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(threadleriBaslat, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addComponent(dosyaAcButton, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addComponent(dosyaAcButton)
					.addGap(18)
					.addComponent(threadleriBaslat)
					.addGap(75)
					.addComponent(timerHizlandir)
					.addGap(26)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
}
