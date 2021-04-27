package Algorithm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.eclipse.cdt.core.*;
import org.eclipse.cdt.core.dom.ast.*;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTAliasDeclaration;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.core.parser.*;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTFunctionCallExpression;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTFunctionDeclarator;
import org.eclipse.cdt.internal.core.dom.parser.cpp.CPPASTName;
import org.eclipse.cdt.internal.core.model.FunctionDeclaration;


public class CDTparser {
    public static IASTTranslationUnit getTranslationUnit(File source) throws Exception
    {
        FileContent reader = FileContent.create(source.getAbsolutePath(), getContentFile(source).toCharArray());
        return GPPLanguage.getDefault().getASTTranslationUnit(reader, new ScannerInfo(), IncludeFileContentProvider.getSavedFilesProvider(), null, ILanguage.OPTION_IS_SOURCE_UNIT, new DefaultLogService());
    }

    public static String getContentFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) 
        {
            while ((line = br.readLine()) != null)
                content.append(line).append('\n');
        }
        return content.toString();
    }
    
    public IASTTranslationUnit getUnit(File f) throws Exception
    {
    	return getTranslationUnit(f);
    }
    public static void main(String[] args) throws Exception {
        IASTTranslationUnit u = getTranslationUnit(new File("C:\\Users\\Administrator\\Desktop\\�½��ı��ĵ� (2).cpp"));
		
        /*IASTDeclaration[] decs=u.getDeclarations();
    			for ( IASTDeclaration child : decs)
    			{
    				IASTNode[] nodes;
    				if (child instanceof IASTFunctionDefinition)
    				{	
    					nodes=child.getChildren();
    					for(IASTNode child1:nodes)
    					{
    						if(child1 instanceof ICPPASTCompoundStatement)
    						{	IASTNode[] statements;
    						    statements=child1.getChildren();
    							for(IASTNode child2:statements)
    							{
    								if(child2 instanceof IASTIfStatement)
    									System.out.println(child2.getRawSignature());
    							}
    						}
    	    					
    					}
    				}
    			} 
        //System.out.println(u.getRawSignature());//ֱ���������
        /*IASTDeclaration[] decs = u.getDeclarations();
        for ( IASTDeclaration child : decs)
		{
        	//��������
			if (child instanceof IASTFunctionDefinition)
			{
				//��ú���˵����,��void
				System.out.println(((IASTFunctionDefinition)child).getDeclSpecifier().getRawSignature());
				//��ú����ĺ��������� ,��Dijkstra(ALGraph g, int v0, int n)
				System.out.println(((IASTFunctionDefinition)child).getDeclarator().getRawSignature());
				//��ú���������� {}֮�������
				System.out.println(((IASTFunctionDefinition)child).getBody().getRawSignature());
				//���������ȫ������
				System.out.println(child.getRawSignature());
				//�뺯������ʼλ���й�
				IASTFileLocation  FileLocation = ((IASTFunctionDefinition)child).getFileLocation();
				int startLine = FileLocation.getStartingLineNumber();
				int endLine = FileLocation.getEndingLineNumber();
				System.out.println("length:"+ (endLine-startLine));
			}
		}    */

        
    }
  //��ȡ��������ͼ
  /*  private void getCallTree(IASTNode node) {
        IASTNode[] children = node.getChildren();
        if(node instanceof CPPASTFunctionCallExpression && functionList.contains(node.getChildren()[0].getRawSignature())){
//            System.out.println(node.getChildren()[0].getRawSignature());
            String childName =node.getChildren()[0].getRawSignature() ;
            String parentName = "";
//            System.out.println(node.getRawSignature());
            IASTNode nodeparent = node;
            String nodeType = nodeparent.getClass().getSimpleName();

            while (!"CPPASTFunctionDefinition".equals(nodeType) ){
                if("CPPASTTranslationUnit".equals(nodeType)){
                    break;
                }
                nodeparent = nodeparent.getParent();
                nodeType = nodeparent.getClass().getSimpleName();
            }
            for(IASTNode itemNode: nodeparent.getChildren()){
                if (itemNode instanceof CPPASTFunctionDeclarator){

                    for(IASTNode iastNode:itemNode.getChildren()){
                        if (iastNode instanceof CPPASTName){
                            parentName = iastNode.getRawSignature();}}
//                    parentName = itemNode.getChildren()[0].getRawSignature();
                    break;
                }
            }
            //��ʼ�����ݴ��뵽��ϣ����
            if(!functionCallList.containsKey(parentName))
            {//���������
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.add(childName);
                functionCallList.put(parentName,tempList);
            }
            else {
                if (!functionCallList.get(parentName).contains(childName))
                {
                    functionCallList.get(parentName).add(childName);
                }

            }
        }
        for (IASTNode iastNode : children)
            getCallTree(iastNode);
    }*/
}

