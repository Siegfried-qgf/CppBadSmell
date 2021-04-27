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
	           out.out1+="��"+startLine+"��:"+iastComment+"��"+temp.length()+"���ַ�\n";
	           num+=temp.length();
			} 
			NumberFormat nf = NumberFormat.getPercentInstance();
			nf.setMaximumFractionDigits(1);//���1����ʶ�Ǳ�������С�����λ�������ر��������������Ѿ��ȣ�100�ˡ�
			double temp=Double.valueOf(num)/Double.valueOf(f.length());
			out.out2+="ע�͹�"+num+"���ַ���ռ�ܴ����"+nf.format(temp)+"\n";
			if(temp<0.2)
			out.out2+="���ļ�������ע�ͻ�ζ";
			else
			out.out2+="���ļ�����ע�ͻ�ζ";	
			return out;
    }
}

