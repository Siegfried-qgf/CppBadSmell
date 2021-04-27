package Algorithm;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

public class LongMethod {
	public CDTparser cdt=new CDTparser();
	public ArrayList<String> storebadsmell=new ArrayList<String>();
    public Output getLongMethod(String str) throws Exception
    {
    	Output out=new Output();
		    int numofbadsmell=0;
			IASTTranslationUnit ast=cdt.getUnit(new File(str));
			IASTDeclaration[] decs = ast.getDeclarations();
			for ( IASTDeclaration child : decs)
			{
				if (child instanceof IASTFunctionDefinition)
				{	
					
					IASTFileLocation  FileLocation = ((IASTFunctionDefinition)child).getFileLocation();
					int startLine = FileLocation.getStartingLineNumber();
					int endLine = FileLocation.getEndingLineNumber();
					out.out1+="第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"共"+(endLine-startLine)+"行\n";
					if((endLine-startLine)>=30)
					{
						numofbadsmell++;
						storebadsmell.add("第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"共"+(endLine-startLine)+"行\n");
					}
				}
			} 
			out.out2+="共检测到长方法代码坏味道"+numofbadsmell+"处\n";
			for(int i=0;i<numofbadsmell;i++)
			{
				out.out2+=storebadsmell.get(i);
			}
			return out;
    	}
  }

