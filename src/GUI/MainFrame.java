package GUI;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

public class MainFrame extends JFrame {
	public static String path;
	public MainFrame() {
		setTitle("C++代码坏味道检测工具");
		setBounds(400, 100, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
        this.setLayout(new BorderLayout());
		LeftPanel leftp=new LeftPanel();
		RightPanel rightp=new RightPanel();
		this.add(leftp,BorderLayout.WEST);
		this.add(rightp,BorderLayout.EAST);
		pack();
	}
    public static void main(String[] args) {
    	new MainFrame();
    }
}
