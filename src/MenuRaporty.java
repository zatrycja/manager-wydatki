import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuRaporty implements Opcja {
    private GUI gui;
    private ListaWydatkow lw;
    private JButton dzienny;
    private JButton miesieczny;
    private JButton ogolny;
    private JButton cofnij;
    private JPanel panel;

    public MenuRaporty(GUI gui, JButton cofnij) {
        this.gui = gui;
        lw = new ListaWydatkow();
        dzienny = new JButton("raport dzienny");
        miesieczny = new JButton("raport miesięczny");
        ogolny = new JButton("raport ogólny");
        this.cofnij = cofnij;
        panel = new JPanel();

        dzienny.setPreferredSize(new Dimension(150,30));
        miesieczny.setPreferredSize(new Dimension(150,30));
        ogolny.setPreferredSize(new Dimension(150,30));
        this.cofnij.setPreferredSize(new Dimension(150,30));

        dzienny.addActionListener(dziennyRaport);
        miesieczny.addActionListener(miesiecznyRaport);
        ogolny.addActionListener(ogolnyRaport);


        panel.add(dzienny);
        panel.add(miesieczny);
        panel.add(ogolny);
        panel.add(this.cofnij);

    }

    public ActionListener dziennyRaport = e -> {
        try {
            lw.zaladujWydatki();
            lw.sortuj();
            new DziennyRaport(gui.getOkienko(), lw);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Brak pliku.");
        }
    };

    public ActionListener miesiecznyRaport = e -> {
        try {
            lw.zaladujWydatki();
            lw.sortuj();
            new MiesiecznyRaport(gui.getOkienko(), lw);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Brak pliku.");
        }
    };

    public ActionListener ogolnyRaport = e -> {
        try {
            lw.zaladujWydatki();
            lw.sortuj();
            new OgolnyRaport(gui.getOkienko(), lw);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Brak pliku.");
        }
    };

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public void pokaz() {
        //gui.getCl().next(gui.getKarty());
        gui.getCl().show(gui.getKarty(), "2");
    }

    public void cofnij() {
        //gui.getCl().previous(gui.getKarty());
        gui.getCl().show(gui.getKarty(), "1");
    }
}
