package Algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

public class LongParameterList {
	public CDTparser cdt=new CDTparser();
	public ArrayList<String> storebadsmell=new ArrayList<String>();
    public Output getLongMethod(String str) throws Exception
    {
    	    Pattern p=Pattern.compile("([_a-zA-Z0-9]*)[(]([^;]*)[)]");
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
					Matcher matcher=p.matcher(((IASTFunctionDefinition)child).getDeclarator().getRawSignature());
			        boolean b = matcher.find();
					out.out1+="第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"有"+findinstring(matcher.group(2), ',')+"个参数\n";
					if(findinstring(((IASTFunctionDefinition)child).getDeclarator().getRawSignature(), ',')>=5)
					{
						numofbadsmell++;
						storebadsmell.add("第"+startLine+"行: "+((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature()+" "+((IASTFunctionDefinition)child).getDeclarator().getRawSignature()+"有"+findinstring(matcher.group(2), ',')+"个参数\n");
					}
				}
			} 
			out.out2+="共检测到长参数列表代码坏味道"+numofbadsmell+"处\n";
			for(int i=0;i<numofbadsmell;i++)
			{
				out.out2+=storebadsmell.get(i);
			}
			return out;
    }
    private int findinstring(String a,char b)
    {
    	int num=1;
    	if(a=="")
    	return 0;
    	for(int i=0;i<a.length();i++)
    	{
    		if(a.charAt(i)==b)
    			num++;
    	}
    	return num;
    }
  }

