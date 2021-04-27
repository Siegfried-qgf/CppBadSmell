package Algorithm;
import java.io.File;
import java.util.ArrayList;

import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import java.text.NumberFormat;
public class Annotation {
	public CDTparser cdt=new CDTparser();
	public ArrayList<String> storebadsmell=new ArrayList<String>();
    public Output getAnnotation(File f) throws Exception
    {
    	    int num=0;
    	    Output out=new Output();
		    int numofbadsmell=0;
			IASTTranslationUnit ast=cdt.getUnit(f);
			IASTComment[]  com = ast.getComments();
			for ( IASTComment iastComment : com)
			{
			   IASTFileLocation  FileLocation = iastComment.getFileLocation();
			   int startLine = FileLocation.getStartingLineNumber();
			   int endLine = FileLocation.getEndingLineNumber();
	           String temp=iastComment+"";
	           out.out1+="第"+startLine+"行:"+iastComment+"共"+temp.length()+"个字符\n";
	           num+=temp.length();
			} 
			NumberFormat nf = NumberFormat.getPercentInstance();
			nf.setMaximumFractionDigits(1);//这个1的意识是保存结果到小数点后几位，但是特别声明：这个结果已经先＊100了。
			double temp=Double.valueOf(num)/Double.valueOf(f.length());
			out.out2+="注释共"+num+"个字符，占总代码的"+nf.format(temp)+"\n";
			if(temp<0.2)
			out.out2+="该文件不具有注释坏味";
			else
			out.out2+="该文件具有注释坏味";	
			return out;
    }
}

