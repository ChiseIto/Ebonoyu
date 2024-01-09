package Ebonoyu.JFrame;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
	public static void main(String[] args) {
		GameWindow gw = new GameWindow("ゑぼの湯", 1200, 800);
		DrawCanvas dc = new DrawCanvas();
		gw.add(dc);
		gw.setVisible(true);
	}
}

class GameWindow extends JFrame{
 
	public GameWindow(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}

class DrawCanvas extends JPanel{
	Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ChiseIto\\Java_Programming\\Ebonoyu\\JFrame\\img\\dansei_01_a.png");
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//画像の表示
		g.drawImage(img, 0, 0, this);
	}
}
