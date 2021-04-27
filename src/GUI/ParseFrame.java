package GUI;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.eclipse.cdt.core.dom.ast.IASTPreprocessorIncludeStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.gnu.cpp.GPPLanguage;
import org.eclipse.cdt.core.parser.DefaultLogService;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IParserLogService;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.cdt.core.parser.IncludeFileContentProvider;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.core.runtime.CoreException;

import treeview.JTreeTable;
import treeview.ast.ASTTreeModel;

public class ParseFrame{

    public ParseFrame() throws CoreException {
        JFrame frame = new JFrame("ASTViewer");
        frame.setBounds(400, 100, 1200, 800);
        
        /*JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setCurrentDirectory(new File("./docs"));
       
        int result = fileChooser.showOpenDialog (frame);
        if (result != JFileChooser.APPROVE_OPTION)
            return;*/
        try {
        	FileContent fileContent = FileContent.createForExternalFileLocation (MainFrame.path);
        	 Map definedSymbols = new HashMap();
             String[] includePaths = new String[0];
             IScannerInfo info = new ScannerInfo(definedSymbols, includePaths);
             IParserLogService log = new DefaultLogService();

             IncludeFileContentProvider emptyIncludes = IncludeFileContentProvider.getEmptyFilesProvider();

             int opts = 8;
             IASTTranslationUnit translationUnit = GPPLanguage.getDefault().getASTTranslationUnit(fileContent, info, emptyIncludes, null, opts, log);
             JTreeTable treeTable = new JTreeTable(new ASTTreeModel(translationUnit));
             
             
             JScrollPane scrollPane = new JScrollPane(treeTable);
             scrollPane.setPreferredSize(new Dimension(1400,600));
             frame.getContentPane().add(scrollPane);
             frame.pack();
             frame.show();
        } catch (Exception e) {
        	// TODO: handle exception
        	JOptionPane.showMessageDialog(null, "请输入正确的文件路径", "警告",JOptionPane.WARNING_MESSAGE);  
        }
        

       

        /*IASTPreprocessorIncludeStatement[] includes = translationUnit.getIncludeDirectives();
        for (IASTPreprocessorIncludeStatement include : includes) {
            System.out.println("include - " + include.getName());
        }*/

      
    }
}