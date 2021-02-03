package guiwork;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class gui extends JFrame {
    private JPanel jframe;
    private JPanel titlebar;
    private JScrollPane tscrollpane;
    private JPanel codearea;
    private JTable table1;
    private JTextArea textArea1;
    private JButton SCAN;
    private JButton RESETButton;
    private JPanel tablepanel;
    private JButton fileupload;
    private JLabel filelabel;
    private JButton removeCommentsButton;
    String value;
    String[] output;
    boolean numflag=true;
    boolean symbflag=true;

    DefaultTableModel model;
  //  String keyword1 = "abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for goto if implements import  instanceof int interface long native  new package private protected public return short static strictfp uper switch synchronized this throw throws transient try void volatile while true NULL false";
    String[] num =  {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final String[] symbols = {"&", "|", "+", "-", "*", ",", "/", "%", "+", "-", "=", "<","<=", ">",">=","==","!=","!", "[", "]", "{", "}", "(", ")", ".", ";", ":","&&", "||"};
    final String[] keywords = {"abstract", "assert",
            "Boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new","String", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "uper",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "NULL", "false"};
    String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String[] columnNames = {"token",
            "description","token count"
    };
    boolean flag=true;
    int tokencount;
// ********************************************************************************************************** //

    public JPanel getJframe() {
        return jframe;
    }
//   /\*.*?\*/
    public void createtable() {
        model = new DefaultTableModel(columnNames, 0);
        value = textArea1.getText();
        String output1 = value.replaceAll("//.*?\n", " ");
        String output2 = output1.replaceAll("/\\*([^*]|[\\r\\n])*\\*/", "\n");
        output = output2.split("\\s+");
        for (int i = 0; i < output.length; i++) {



            if(output[i].matches("\".*?\""))
            {
                model.addRow(new Object[]{output[i], "String Literal", tokencount});
            }
            if(output[i].matches("'[\\w\\s]+'"))
            {
                model.addRow(new Object[]{output[i], "character Literal", tokencount});
            }

            if( output[i].matches("[ a-zA-Z]+")) {
                for (int j = 0; j < keywords.length; j++) {
                    if (output[i].equals(keywords[j])) {
                        flag = false;
                    }
                }
                if (!flag) {
                    tokencount++;
                    model.addRow(new Object[]{output[i], "keyword",tokencount});

                    flag=true;
                } else {
                    tokencount++;
                    model.addRow(new Object[]{output[i], "identifier",tokencount});

                }
                table1.setModel(model);
            }
            for (int z=0;z<num.length; z++){
            if (output[i].contains(num[z])) {
                numflag=false; }}
                if (!numflag)
                {
                    tokencount++;
                    model.addRow(new Object[]{output[i], "number",tokencount});
                    numflag = true;
                    table1.setModel(model);
                }


            for (int j = 0; j < symbols.length; j++) {
                if (output[i].equals(symbols[j])) { symbflag=false; }}
                if(!symbflag){
                    switch (output[i])
                    {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            tokencount++;
                            model.addRow(new Object[]{output[i], "Arithmetic Operator", tokencount});
                            symbflag=true;
                            break;
                        case "++":
                        case "--":
                        case "!":
                            tokencount++;
                            model.addRow(new Object[]{output[i], "Unary Operator", tokencount});
                            symbflag=true;
                            break;
                        case "<=":
                        case ">":
                        case "<":
                        case ">=":
                        case "!=":
                        case "==":
                            tokencount++;
                            model.addRow(new Object[]{output[i], "Relational Operator", tokencount});
                            symbflag=true;
                            break;
                        case "=":
                            tokencount++;
                            model.addRow(new Object[]{output[i], "Assignment Operator", tokencount});
                            symbflag=true;
                            break;
                        case ";":
                            tokencount++;
                            model.addRow(new Object[]{output[i], "semi colon", tokencount});
                            symbflag=true;
                            break;
                        default:
                            tokencount++;
                            model.addRow(new Object[]{output[i], "Symbols", tokencount});
                            symbflag=true;
                            break;
                    }



            }
//                if(output[i].matches(" \" "))
//                {
//                    String result;
//                    while(!output[i + 1].equals("\" ")) {
//                        result=output[i].toString();
//                    }
//                    model.addRow(new Object[]{output[i], "String literal", tokencount});
//                }
            table1.setModel(model);



//            if (output[i].matches("[ a-zA-Z]+") && !output[i].contentEquals(keyword1)) {
//                model.addRow(new Object[]{output[i], "alphabet"});
//                table1.setModel(model);
//            }


//        table1.setModel(new DefaultTableModel(data,columnNames));
//         model = new DefaultTableModel(new Object[]{"column1", "column2"}, 0);


//            Pattern pattern = Pattern.compile("\".*?\"");
//            Matcher m = pattern.matcher(output[i]);
//            while (m.find()) {
//                tokencount++;
//                model.addRow(new Object[]{output[i], "String Literal", tokencount});
//            }
//            Pattern p = Pattern.compile("'[\\w\\s]+'");
//            Matcher match = p.matcher(output[i]);
//            while (match.find()) {
//                tokencount++;
//                model.addRow(new Object[]{output[i], "character Literal", tokencount});
//            }

//        model.addColumn("workplace");
//        model.addRow(new Object[]{"jack", "roger", "home"});
//
//        table1.setModel(model);


        }
    }
    public void filepicker()
    {
        JFileChooser jf=new JFileChooser();
        if(jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

            //get the file
            File f1 = jf.getSelectedFile();

            //create a scanner for the file
            try {
                Scanner input = new Scanner(f1);
                while(input.hasNext()){
                    textArea1.append(input.nextLine());
                    textArea1.append("\n");;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            filelabel.setText("file selected as: "+ f1.getName());
        }
        else {
            filelabel.setText("please select a file");
        }
    }


    public gui() throws HeadlessException {
//        createtable();

//        String[] columnNames = {"token",
//                "description","token"
//        };
////        table1.setModel(new DefaultTableModel(data,columnNames));
//        model = new DefaultTableModel(columnNames, 0);
////        model.addColumn("workplace");
//        model.addRow(new Object[]{"jack", "roger", "home"});
//
//        table1.setModel(model);

        SCAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createtable();
            }
        });
        RESETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                model=(DefaultTableModel)table1.getModel();
                model.setRowCount(0);
//                model.fireTableDataChanged();
                tokencount=0;
                filelabel.setText("no file chosen");
                table1.setModel(model);
            }
        });
        fileupload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            filepicker();
            }
        });

    }
    }

//class symboltable
//{
//
//
//        final String[] symbols = {"&", "|", "+", "-", "*", ",", "/", "%", "+", "-", "=", "<", ">", "!", "[", "]", "{", "}", "(", ")", ".", ";", ":"};
//        final String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
//
//
//
//}


