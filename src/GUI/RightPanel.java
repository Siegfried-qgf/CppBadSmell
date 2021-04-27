package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import Algorithm.*;
import javax.swing.*;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.core.runtime.CoreException;

public class RightPanel extends JPanel{ 
	 JButton bt0=new JButton("查看解析结果");
	 JLabel label1=new JLabel("选择需要检测的坏味道");
	 JComboBox<String> c1 = new JComboBox<String>();//创建一个下拉列表框c1
	 JTextArea jta1=new JTextArea();
	 JTextArea jta2=new JTextArea();
	 JButton bt=new JButton("检测");
	 JScrollPane sp1 = new JScrollPane();
	 JScrollPane sp2 = new JScrollPane();
	 String path;
	 CDTparser cdt=new CDTparser();
	 ArrayList<String> storebadsmell=new ArrayList<String>();
	 public void setpath(String path)
     {
   	  this.path=path;
     }
	 public RightPanel() {
    	 setPreferredSize(new Dimension(600, 800));
    	 c1.addItem("Long Method");  
    	 c1.addItem("Long Parameter List");       // 创建5个下拉选项
    	 c1.addItem("Annotation");
    	 c1.addItem("Cyclomatic");
    	 c1.addItem("If Nested");
    	 label1.setPreferredSize(new Dimension(150,40));
    	 label1.setFont(new Font("Serif", Font.BOLD, 14));
    	 c1.setPreferredSize(new Dimension(120,30));
    	 bt.setPreferredSize(new Dimension(100,30));
    	 bt.setFont(new Font("Serif", Font.BOLD, 14));
    	 bt0.setPreferredSize(new Dimension(150,30));
    	 bt0.setFont(new Font("Serif", Font.BOLD, 14));
    	 sp1.setPreferredSize(new Dimension(500,350));
    	 sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	 sp1.setViewportView(jta1);
    	 sp2.setPreferredSize(new Dimension(500,350));
    	 sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	 sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	 sp2.setViewportView(jta2);
    	 jta1.setFont(new Font("Serif", Font.BOLD, 14));
    	 jta2.setFont(new Font("Serif", Font.BOLD, 14));
    	 //setBackground(new Color(0,0,0));
    	 add(bt0);
    	 add(label1);
    	 add(c1);
    	 add(bt);
    	 add(sp1);
    	 add(sp2);
    	 setVisible(true);
    	 bt.addActionListener(new MyActionListenerright());
    	 bt0.addActionListener(new MyActionListenerright0());
      }
	 class MyActionListenerright implements ActionListener
 	 {
	     @Override
	     public void actionPerformed(ActionEvent e)
	     {
	    	 int badtype;
	    	 badtype=c1.getSelectedIndex();
	    	 setpath(MainFrame.path);
	    	 try {
	    		    int numofbadsmell=0;
					IASTTranslationUnit ast=cdt.getUnit(new File(path));
					IASTDeclaration[] decs = ast.getDeclarations();
					if(badtype==0)
					{
						LongMethod lm=new LongMethod();
						jta1.setText(lm.getLongMethod(path).out1);
						jta2.setText(lm.getLongMethod(path).out2);
					}
			        else if(badtype==1)
					{
			        	LongParameterList lpl=new LongParameterList();
						jta1.setText(lpl.getLongMethod(path).out1);
						jta2.setText(lpl.getLongMethod(path).out2);
					}
			        else if(badtype==2)
			        {
			        	Annotation anno=new Annotation();
						jta1.setText(anno.getAnnotation(new File(path)).out1);
						jta2.setText(anno.getAnnotation(new File(path)).out2);
			        }
			        else if(badtype==3)
			        {
			        	Cyclomatic cyc=new Cyclomatic();
			        	jta1.setText(cyc.getcc(path).out1);
			        	jta2.setText(cyc.getcc(path).out2);
			        }
			        else if(badtype==4)
			        {
			        	IfNested  ifNested=new IfNested();
			        	jta1.setText(ifNested.getIfNested(path).out1);
			        	jta2.setText(ifNested.getIfNested(path).out2);

			        }
	    	 	} catch (Exception e1) {
	    	 			e1.printStackTrace();
	    	 			JOptionPane.showMessageDialog(null, "请输入正确的文件路径", "警告",JOptionPane.WARNING_MESSAGE);  
	    	 		}
	     	}	
	 }
	 class MyActionListenerright0 implements ActionListener
 	 {
	     @Override
	     public void actionPerformed(ActionEvent e)
	     {
	    	 try {
				new ParseFrame();
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     }	
	 }
}
