import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OgolnyRaport extends Raporty {
    private JDialog dialog;
    private ListaWydatkow lw;
    private ListaKategorii lk;
    private JLabel lista;
    private JButton wroc;

    public OgolnyRaport (JFrame okienko, ListaWydatkow lw) throws IOException {
        dialog = new JDialog (okienko,"Ogólny raport",true);
        dialog.setMinimumSize(new Dimension(600,500));
        dialog.pack();
        dialog.setLayout(new FlowLayout());
        this.lw = lw;
        lk = new ListaKategorii();
        JPanel panel = new JPanel();
        lista = new JLabel();
        raport(this.lw, lk, lista);
        wroc = new JButton("Wróć");

        wroc.addActionListener(wrocW);

        panel.add(lista);
        panel.add(wroc);

        dialog.getContentPane().add(panel);
        dialog.setLocationRelativeTo(okienko);
        dialog.pack();
        dialog.setVisible(true);
    }

    public ActionListener wrocW = e -> {
        dialog.dispose();
    };
}
