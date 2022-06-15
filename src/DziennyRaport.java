import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DziennyRaport extends Raporty {
    private JDialog dialog;
    private ListaWydatkow lw;
    private ListaWydatkow wydatki;
    private ListaKategorii lk;
    private JLabel lista;
    private JComboBox<String> daty;
    private JButton wroc;
    private JButton znajdz;

    public DziennyRaport (JFrame okienko, ListaWydatkow lw) throws IOException {
        dialog = new JDialog(okienko, "Dzienny raport", true);
        dialog.setLayout(new FlowLayout());
        this.lw = lw;
        lk = new ListaKategorii();
        JPanel panel = new JPanel();
        lista = new JLabel();
        daty = new JComboBox<>();
        setDaty();
        znajdz = new JButton("Zatwierdź datę");
        wroc = new JButton("Wróć");

        znajdz.addActionListener(znajdzW);
        wroc.addActionListener(wrocW);

        panel.add(daty);
        panel.add(znajdz);
        panel.add(lista);
        panel.add(wroc);

        dialog.add(panel);
        dialog.setLocationRelativeTo(okienko);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setDaty() {
        int i = 0;
        List<String> daty = new ArrayList<>();
        for (Wydatek w: lw.getWydatki()) {
            if (!daty.contains(w.dataString()))
                daty.add(w.dataString());
        }

        sortuj(daty);

        for (String d: daty) {
            this.daty.addItem(d);
        }

    }

    public void setWydatki() {
        wydatki = new ListaWydatkow();
        for (Wydatek w : lw.getWydatki()) {
            if (w.dataString().equals(daty.getSelectedItem()))
                wydatki.dodaj(w);
        }
    }

    public ActionListener wrocW = e -> {
        dialog.dispose();
    };

    public ActionListener znajdzW = e -> {
        lista.setText("");
        dialog.setMinimumSize(new Dimension(600,500));
        dialog.pack();
        setWydatki();
        raport(wydatki,lk,lista);
    };

    public void sortuj(List<String> daty) {
        sortujRok(daty);
        for (int i = 0; i < daty.size(); i++) {
            for (int k = 0; k < daty.size(); k++)
                if (Integer.parseInt(daty.get(k).substring(5)) >= Integer.parseInt(daty.get(i).substring(5)))
                    if (Integer.parseInt(daty.get(k).substring(3,4)) >= Integer.parseInt(daty.get(i).substring(3,4)))
                        if (Integer.parseInt(daty.get(k).substring(0,1)) > Integer.parseInt(daty.get(i).substring(0,1)))
                            swap(i, k, daty);
        }

    }

    public void sortujRok(List<String> daty) {
        for (int i = 0; i < daty.size(); i++)
            for (int k = 0; k < daty.size(); k++)
                if (Integer.parseInt(daty.get(k).substring(5)) > Integer.parseInt(daty.get(i).substring(5)))
                    swap(i, k, daty);

    }

    public void swap (int i, int k, List<String> daty) {
        String pomocniczy = daty.get(i);
        daty.set(i,daty.get(k));
        daty.set(k,pomocniczy);
    }
}
