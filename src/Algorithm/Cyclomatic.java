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
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTBinaryExpression;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTCatchHandler;

public class Cyclomatic {
	public CDTparser cdt=new CDTparser(); 
	IASTTranslationUnit ast;
	public ArrayList<String> storebadsmell=new ArrayList<String>();
	public Cyclomatic() throws Exception
	{
		ast=cdt.getUnit(new File("C:\\Users\\Administrator\\Desktop\\新建文本文档 (2).cpp"));
	}
    private int findcc(IASTNode node)
    {
    	IASTNode[] children = node.getChildren();
    	if(children.length==0)
    	return 0;
    	int temp=0;
    	for(IASTNode childrennode:children)
    	{
    		if(childrennode instanceof IASTIfStatement)
    		temp+=1+findcc(childrennode);
    		else if(childrennode instanceof IASTForStatement)
    		temp+=1+findcc(childrennode);
    		else if(childrennode instanceof IASTWhileStatement)
    		temp+=1+findcc(childrennode);
    		else if(childrennode instanceof IASTCaseStatement)
    		temp+=1+findcc(childrennode);
    		else if(childrennode instanceof CPPASTCatchHandler)
    		temp+=1+findcc(childrennode);	
    		else if(childrennode instanceof CPPASTBinaryExpression)
    		{
    			if( (childrennode.getRawSignature().indexOf("&&")!=-1) || (childrennode.getRawSignature().indexOf("||")!=-1) )
    			{
    				temp+=1+findcc(childrennode);
    				System.out.println(childrennode.getRawSignature().indexOf("&&"));
    				System.out.println(childrennode.getRawSignature().indexOf("||"));
    			    System.out.println("sbsbsbsbsbs"+childrennode.getRawSignature()+"\n");
    			}
    		}
    		else 
    		temp+=findcc(childrennode);
    	}
    	return temp;
    }
    public Output getcc(String str)
    {
    	int numofbadsmell=0;
    	Output out=new Output();
    	try {
			ast=cdt.getUnit(new File(str));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	IASTDeclaration[] decs = ast.getDeclarations();
    	for(IASTDeclaration child:decs)
    	{
    		if (child instanceof IASTFunctionDefinition)
			{	
				IASTFileLocation  FileLocation = ((IASTFunctionDefinition)child).getFileLocation();
				int startLine = FileLocation.getStartingLineNumber();
				int endLine = FileLocation.getEndingLineNumber();
				int num=0;
				num=findcc(child);
				out.out1+="第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"圈复杂度数值为"+num+"个\n";
				if(num>5)
				{
					numofbadsmell++;
					storebadsmell.add("第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"圈复杂度数值为"+num+"个\n");
				}
				
			}
    	}
    	out.out2+="共检测到圈复杂度代码坏味道"+numofbadsmell+"处\n";
		for(int i=0;i<numofbadsmell;i++)
		{
			out.out2+=storebadsmell.get(i);
		}
		return out;
    }
}
	
