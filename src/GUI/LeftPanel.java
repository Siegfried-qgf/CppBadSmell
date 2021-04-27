package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class LeftPanel extends JPanel{
	 JLabel label=new JLabel("所选文件路径：");
	 JTextField jtf=new JTextField(25);
	 JButton button=new JButton("浏览");
	 JTextArea jta=new JTextArea();
	 JScrollPane sp = new JScrollPane();
	 public LeftPanel() {
    	 setPreferredSize(new Dimension(600, 800));
    	 setVisible(true);
    	 label.setPreferredSize(new Dimension(110,40));
    	 label.setFont(new Font("Serif", Font.BOLD, 14));
    	 jtf.setPreferredSize(new Dimension(100,30));
    	 jtf.setFont(new Font("Serif", Font.BOLD, 14));
    	 button.setPreferredSize(new Dimension(100,30));
    	 button.setFont(new Font("Serif", Font.BOLD, 14));
    	 //jta.setPreferredSize(new Dimension(500,700));
    	 sp.setPreferredSize(new Dimension(550,700));
    	 sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	 sp.setViewportView(jta);
    	 sp.setRowHeaderView(new LineNumberHeaderView());
    	 jta.setFont(new Font("Serif", Font.BOLD, 16));
    	 add(label);
    	 add(jtf);
    	 add(button);
    	 add(sp);	 
    	 button.addActionListener(new MyActionListenerleft());
     }
	 class MyActionListenerleft implements ActionListener
	 {
	     @Override
	     public void actionPerformed(ActionEvent e)
	     {
	         JFileChooser fc=new JFileChooser("C:\\Users\\Administrator\\Desktop");
	         fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	         int val=fc.showOpenDialog(null);    //文件打开对话框
	         if(val==fc.APPROVE_OPTION)
	         {
	             //正常选择文件
	             jtf.setText(fc.getSelectedFile().toString());
	             jta.setText("");
	             MainFrame.path=fc.getSelectedFile().toString();
	             File file=fc.getSelectedFile();
	             FileReader reader;    //声明字符流
	             BufferedReader in;    //声明字符缓冲流
	             try
	             {
	            	 reader=new FileReader(file);    //创建字符流
	                 in=new BufferedReader(reader);    //创建字符缓冲流
	                 String info=in.readLine();    //从文件中读取一行信息
	                 while(info!=null)
	                 {
	                 //判断是否读到内容
	                     jta.append(info+"\n");    //把读到的信息追加到文本域中
	                     //(JTextArea)recvArea.setCaretPosition(recvArea.getText().length()),
	                     info=in.readLine();    //继续读下一行信息
	                 }
	                 jta.setCaretPosition(0); //光标位置回到最顶端
	             in.close();    //关闭字符缓冲流
	             reader.close();    //关闭字符流
	             }
	             catch(Exception ex)
	             {
	                 ex.printStackTrace();    //输出栈踪迹
	             }
	         }
	         else
	         {
	             //未正常选择文件，如选择取消按钮
	             jtf.setText("未选择文件");
	         }
	     }	
	     
	 }
	 public String getpath()
	 {
		 return jtf.getText();
	 }
}

