import javax.swing.*;
import guiwork.gui;

public class abc {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                rungui();
            }
        });

    }

    private static void rungui() {
        gui g = new gui();
        JFrame jf = new JFrame("SCANNER IN JAVA");
        JPanel root = g.getJframe();
        jf.setContentPane(root);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}