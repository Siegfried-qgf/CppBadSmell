package Algorithm;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.cdt.core.dom.ast.IASTCaseStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTCatchHandler;

public class IfNested {
	public CDTparser cdt=new CDTparser(); 
	IASTTranslationUnit ast;
	public ArrayList<String> storebadsmell=new ArrayList<String>();
	public IfNested() throws Exception
	{
		ast=cdt.getUnit(new File("C:\\Users\\Administrator\\Desktop\\新建文本文档 (2).cpp"));
	}
	ArrayList<IASTNode> ifnodes=new ArrayList(); 
    private void findif(IASTNode node)
    {
    	IASTNode[] children = node.getChildren();
    	if(children.length==0)
    	return;
    	for(IASTNode childrennode:children)
    	{
    		IASTNode temp=childrennode;
    		if(childrennode instanceof IASTIfStatement)
    		{
    			ifnodes.add(temp);
    			continue;
    		}
    		else 
    		findif(childrennode);	
    	}
    }
    private int ifnum(IASTNode node)
    {
    	IASTNode[] children = node.getChildren();
    	int temp=0;
    	int comp=0;
    	int max=0;
    	if(children.length==0)
    	return 0;
    	for(IASTNode childrennode:children)
    	{
    		if(childrennode instanceof IASTIfStatement)
    		temp+=1+ifnum(childrennode);
    		else 
    		temp+=ifnum(childrennode);
    		
    		max=max(comp,temp);
    		comp=temp;
    		temp=0;
    	}
    	return max;
    }
    private void travel(IASTNode node)
    {
    	IASTNode[] children = node.getChildren();
    	if(children.length==0)
    	return;
    	for(IASTNode childrennode:children)
    	{
    		System.out.println(node.getRawSignature());
    		travel(childrennode);
    	}
    	return;
    }
    public Output getIfNested(String str) throws Exception
    {
    	Output out=new Output();
		//int numofbadsmell=0;
		IASTTranslationUnit ast=cdt.getUnit(new File(str));
		IASTDeclaration[] decs = ast.getDeclarations();
		for ( IASTDeclaration child : decs)
		{
			if (child instanceof IASTFunctionDefinition)
			{	
				this.findif(child);
				for(int i=0;i<ifnodes.size();i++)
				{
					IASTFileLocation  FileLocation = (ifnodes.get(i)).getFileLocation();
					int startLine = FileLocation.getStartingLineNumber();
					out.out1+="函数"+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"中"+"第"+startLine+"行if嵌套个数为"+(this.ifnum(ifnodes.get(i))+1)+"个\n";
				}
				//IASTFileLocation  FileLocation = ((IASTFunctionDefinition)child).getFileLocation();
				//int startLine = FileLocation.getStartingLineNumber();
				//int endLine = FileLocation.getEndingLineNumber();
				//out.out1+="第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"共"+(endLine-startLine)+"行\n";
				//if((endLine-startLine)>=30)
				//{
			//		numofbadsmell++;
			//		storebadsmell.add("第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"共"+(endLine-startLine)+"行\n");
				//}
			}
		} 
		//out.out2+="共检测到长方法代码坏味道"+numofbadsmell+"处\n";
		//for(int i=0;i<numofbadsmell;i++)
		//{
			//out.out2+=storebadsmell.get(i);
		//}
		return out;
    }
    /*public static void main(String[] args) throws Exception 
    {
		IfNested ifnest=new IfNested();
		IASTDeclaration[] decs = ifnest.ast.getDeclarations();
		for(IASTDeclaration child:decs)
		{
			if(child instanceof IASTFunctionDefinition)
			{
				ifnest.findif(child);
			}
		}
		for(int i=0;i<ifnest.ifnodes.size();i++)
		{	
			System.out.println(ifnest.ifnodes.get(i).getRawSignature());
			System.out.println((ifnest.ifnum(ifnest.ifnodes.get(i))+1)+"\n");
		}
		
	}*/
    private int max(int a,int b)
    {
    	if(a>b)
    	return a;
    	else 
    	return b;
    }
}
	
