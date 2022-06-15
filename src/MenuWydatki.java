import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuWydatki implements Opcja {
    private ListaWydatkow lw;
    private GUI gui;
    private JButton dodaj;
    private JButton usun;
    private JButton modyfikuj;
    private JButton historia;
    private JButton cofnij;
    private JPanel panel;


    public MenuWydatki(GUI gui, JButton cofnij) {
        lw = new ListaWydatkow();
        this.gui = gui;
        dodaj = new JButton("dodaj nowy wydatek");
        usun = new JButton("usuń wydatek");
        modyfikuj = new JButton("modyfikuj wydatek");
        historia = new JButton("historia wydatków");
        this.cofnij = cofnij;
        dodaj.addActionListener(dodajWydatek);
        usun.addActionListener(usunWydatek);
        modyfikuj.addActionListener(modyfikujWydatek);
        historia.addActionListener(historiaWydatkow);

        dodaj.setPreferredSize(new Dimension(150,30));
        usun.setPreferredSize(new Dimension(150,30));
        modyfikuj.setPreferredSize(new Dimension(150,30));
        historia.setPreferredSize(new Dimension(150,30));
        this.cofnij.setPreferredSize(new Dimension(150,30));

        panel = new JPanel();
        panel.add(dodaj);
        panel.add(usun);
        panel.add(modyfikuj);
        panel.add(historia);
        panel.add(this.cofnij);

    }

    public ActionListener dodajWydatek = e -> {
        try {
           new DodajWydatek(gui.getOkienko(), lw);

        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "brak pliku.");
        }
    };

    public ActionListener usunWydatek = e -> {
        lw.zaladujWydatki();
        new UsunWydatek(gui.getOkienko(), lw);
    };

    public ActionListener modyfikujWydatek = e -> {
        lw.zaladujWydatki();
        new ModyfikujWydatek(gui.getOkienko(),lw);
    };

    public ActionListener historiaWydatkow = e -> {
        lw.zaladujWydatki();
        new HistoriaWydatek(gui.getOkienko(), lw);
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
