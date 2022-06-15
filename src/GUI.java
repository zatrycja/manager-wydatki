import javax.swing.*;
import java.awt.*;

public class GUI {

    protected JFrame okienko;
    protected JPanel karty;
    protected CardLayout cl;
    protected JButton wydatki;
    protected JButton raporty;
    protected JPanel menu;
    protected JPanel akcje;
    private Opcja opcja;


    public GUI() {
        okienko = new JFrame("Dusigrosz");
        okienko.setMinimumSize(new Dimension(240,250));
        karty = (JPanel) okienko.getContentPane();
        cl = new CardLayout();
        karty.setLayout(cl);

        menu = new JPanel();
        akcje = new JPanel();

        wydatki = new JButton("wydatki");
        raporty = new JButton("raporty");
        menu.add(wydatki);
        menu.add(raporty);
        karty.add(menu, "1");
        okienko.setLocationRelativeTo(null);
        okienko.pack();
        okienko.setVisible(true);
        okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public JButton getRaporty() {
        return raporty;
    }

    public JButton getWydatki() {
        return wydatki;
    }

    public Opcja getOpcja() {
        return opcja;
    }

    public CardLayout getCl() {
        return cl;
    }

    public JPanel getKarty() {
        return karty;
    }

    public void setOpcja(Opcja opcja) {
        this.opcja = opcja;
        this.akcje = opcja.getPanel();
        karty.add(akcje, "2");
    }

    public JFrame getOkienko() {
        return okienko;
    }
}
