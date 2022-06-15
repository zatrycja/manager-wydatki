import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModyfikujWydatek extends Sortowanie {
    private ListaWydatkow lw;
    private JComboBox<String> wydatki;
    private JComboBox<String> daty;
    private JButton zmien;
    private JButton anuluj;
    private JButton znajdz;
    private JFrame okienko;
    private JDialog dialog;

    public ModyfikujWydatek(JFrame okienko, ListaWydatkow lw) {
        dialog = new JDialog(okienko, "Modyfikacja wydatku", true);
        this.okienko = okienko;
        dialog.setLayout(new FlowLayout());
        this.lw = lw;
        JPanel panel = new JPanel();
        wydatki = new JComboBox<>();
        setWydatki();
        daty = new JComboBox<>();
        setDaty();
        znajdz = new JButton("Znajdź według daty");
        zmien = new JButton("Zmień");
        anuluj = new JButton("Anuluj");

        znajdz.addActionListener(znajdzW);
        zmien.addActionListener(zmienW);
        anuluj.addActionListener(anulujW);

        panel.add(daty);
        panel.add(znajdz);
        panel.add(wydatki);
        panel.add(zmien);
        panel.add(anuluj);

        dialog.getContentPane().add(panel);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setDaty() {
        List<String> daty = new ArrayList<>();
        lw.sortuj();
        for (Wydatek w : lw.getWydatki()) {
            if (!daty.contains(w.dataString()))
                daty.add(w.dataString());
        }

        sortuj(daty);

        for (String d : daty) {
            this.daty.addItem(d);
        }

    }

    public void setWydatki() {
        int i = 0;
        String info;
        for (Wydatek w : lw.getWydatki()) {
            info = i + 1 + ". ";
            info += w.dataString() + " /";
            info += w.getKategoria() + " /";
            info += w.getNazwa() + " /";
            info += w.getKwota() + " zł";
            wydatki.addItem(info);
            i++;
        }
    }


    public void zmienWydatki() {
        int i = 0;
        for (Wydatek w: lw.getWydatki()){
            if (w.dataString().equals(daty.getSelectedItem())) {
                String info = i+1 +". ";
                info += w.dataString() +" /";
                info += w.getKategoria() + " /";
                info += w.getNazwa() + " /";
                info += w.getKwota()+" zł";
                wydatki.addItem(info);
            }
            i++;
        }
    }

    public ActionListener zmienW = e -> {
        try {
        String s = (String) wydatki.getSelectedItem();
        String[] parts = s.split("\\.");
        int indeks = Integer.parseInt(parts[0]) - 1;
        Wydatek w = lw.getWydatki().get(indeks);
        new DodajWydatek(okienko, lw, w);
        }
          catch (NullPointerException | IOException ex){
                JOptionPane.showMessageDialog(null,"Brak wydatków do modyfikacji.");
            }
        dialog.dispose();
    };

    public ActionListener anulujW = e -> {
        dialog.dispose();
    };

    public ActionListener znajdzW = e -> {
        wydatki.removeAllItems();
        zmienWydatki();
    };
/*
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

 */
}