import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsunWydatek extends Sortowanie {
    private ListaWydatkow lw;
    private JComboBox<String> wydatki;
    private JComboBox<String> daty;
    private JButton usun;
    private JButton anuluj;
    private JButton znajdz;
    private JDialog dialog;

    public UsunWydatek (JFrame okienko, ListaWydatkow lw) {
        dialog = new JDialog(okienko,"Usuwanie wydatku",true);
        dialog.setLayout(new FlowLayout());
        this.lw = lw;
        JPanel panel = new JPanel();
        wydatki = new JComboBox<>();
            setWydatki();
        daty = new JComboBox<>();
        setDaty();
        znajdz = new JButton("Znajź według daty");
        usun = new JButton("Usuń");
        anuluj = new JButton("Anuluj");

        znajdz.addActionListener(znajdzW);
        usun.addActionListener(usunW);
        anuluj.addActionListener(anulujW);

        panel.add(daty);
        panel.add(znajdz);
        panel.add(wydatki);
        panel.add(usun);
        panel.add(anuluj);

        dialog.getContentPane().add(panel);
        dialog.setLocationRelativeTo(null);
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
        int i = 0;
        String info;
        for (Wydatek w: lw.getWydatki()){
            info = (i+1) +". ";
            info += w.dataString() +" /";
            info += w.getKategoria() +" /";
            info += w.getNazwa() +" /";
            info += w.getKwota()+" zł";
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
            info += w.getKategoria() +" /";
            info += w.getNazwa() +" /";
            info += w.getKwota()+" zł";
            wydatki.addItem(info);
            }
            i++;
        }
    }

    public ActionListener usunW = e -> {
        try {
            String s = (String) wydatki.getSelectedItem();
            String[] parts = s.split("\\.");
            int indeks = Integer.parseInt(parts[0]) - 1;
            lw.getWydatki().remove(indeks);
            lw.zapiszWydatki();
        }
        catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null,"Brak wydatków do usunięcia.");
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

}
