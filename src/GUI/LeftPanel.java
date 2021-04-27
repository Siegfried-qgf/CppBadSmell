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
	 JLabel label=new JLabel("��ѡ�ļ�·����");
	 JTextField jtf=new JTextField(25);
	 JButton button=new JButton("���");
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
	         int val=fc.showOpenDialog(null);    //�ļ��򿪶Ի���
	         if(val==fc.APPROVE_OPTION)
	         {
	             //����ѡ���ļ�
	             jtf.setText(fc.getSelectedFile().toString());
	             jta.setText("");
	             MainFrame.path=fc.getSelectedFile().toString();
	             File file=fc.getSelectedFile();
	             FileReader reader;    //�����ַ���
	             BufferedReader in;    //�����ַ�������
	             try
	             {
	            	 reader=new FileReader(file);    //�����ַ���
	                 in=new BufferedReader(reader);    //�����ַ�������
	                 String info=in.readLine();    //���ļ��ж�ȡһ����Ϣ
	                 while(info!=null)
	                 {
	                 //�ж��Ƿ��������
	                     jta.append(info+"\n");    //�Ѷ�������Ϣ׷�ӵ��ı�����
	                     //(JTextArea)recvArea.setCaretPosition(recvArea.getText().length()),
	                     info=in.readLine();    //��������һ����Ϣ
	                 }
	                 jta.setCaretPosition(0); //���λ�ûص����
	             in.close();    //�ر��ַ�������
	             reader.close();    //�ر��ַ���
	             }
	             catch(Exception ex)
	             {
	                 ex.printStackTrace();    //���ջ�ټ�
	             }
	         }
	         else
	         {
	             //δ����ѡ���ļ�����ѡ��ȡ����ť
	             jtf.setText("δѡ���ļ�");
	         }
	     }	
	     
	 }
	 public String getpath()
	 {
		 return jtf.getText();
	 }
}

