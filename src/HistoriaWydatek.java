import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HistoriaWydatek extends Sortowanie {
    private ListaWydatkow lw;
    private JLabel lista;
    private JComboBox<String> dzien;
    private JComboBox<String> miesiac;
    private JButton wroc;
    private JButton znajdzDzien;
    private JButton znajdzMiesiac;
    private JDialog dialog;

    public HistoriaWydatek (JFrame okienko, ListaWydatkow lw) {
        dialog = new JDialog(okienko,"Historia wydatków",true);
        dialog.setLayout(new FlowLayout());
        this.lw = lw;
        JPanel panel = new JPanel();
        lista = new JLabel();
            setWydatki();
        dzien = new JComboBox<>();
        miesiac = new JComboBox<>();
        setdzien();
        znajdzDzien = new JButton("dla danego dnia");
        znajdzMiesiac = new JButton("dla danego miesiąca");
        wroc = new JButton("Wróć");

        znajdzDzien.addActionListener(znajdzDzienW);
        znajdzMiesiac.addActionListener(znajdzMiesiacW);
        wroc.addActionListener(wrocW);

        panel.add(dzien);
        panel.add(znajdzDzien);
        panel.add(miesiac);
        panel.add(znajdzMiesiac);
        panel.add(lista);
        panel.add(wroc);

        dialog.add(panel);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setdzien() {
        int i = 0;
        List<String> dzien = new ArrayList<>();
        List<String> miesiac = new ArrayList<>();
        
        for (Wydatek w: lw.getWydatki()) {
            String pomocnicza = w.dataString().substring(3);
            if (!miesiac.contains(pomocnicza))
                miesiac.add(pomocnicza);
            if (!dzien.contains(w.dataString()))
                dzien.add(w.dataString());
        }

        sortuj(dzien);
        sortujMiesiecznie(miesiac);

        for (String d: dzien) {
            this.dzien.addItem(d);
        }

        for (String d: miesiac) {
            this.miesiac.addItem(d);
        }

    }

    public void setWydatki() {
        int i = 0;
        String info = "<html><table style='border: 1px solid black; padding: 2px;'><tr><th>Id.</th><th>Data</th><th>Kategoria</th><th>Nazwa</th><th>Koszt[zł]</th></tr>";
        for (Wydatek w : lw.getWydatki()) {
                info +="<tr><td style='border: 1px solid black;'>"+ (i + 1) + ".</td>";
                info +="<td style='border: 1px solid black;'>"+ w.dataString() +"</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKategoria() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getNazwa() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKwota() + "</td></tr>";
                i++;
        }
        info += "</table></html>";
        lista.setText(info);
    }

    public void zmienWydatkiDzien() {
        int i = 0;
        String info = "<html><table style='border: 1px solid black; padding: 2px;'><tr><th>Id.</th><th>Data</th><th>Kategoria</th><th>Nazwa</th><th>Koszt[zł]</th></tr>";
        for (Wydatek w : lw.getWydatki()) {
            if (w.dataString().equals(dzien.getSelectedItem())) {
                info +="<tr><td style='border: 1px solid black;'>"+ (i + 1) + ".</td>";
                info +="<td style='border: 1px solid black;'>"+ w.dataString() +"</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKategoria() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getNazwa() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKwota() + "</td></tr>";
                i++;
            }
        }
        info += "</table></html>";
        lista.setText(info);
    }

    public void zmienWydatkiMiesiac() {
        int i = 0;
        String info = "<html><table style='border: 1px solid black; padding: 2px;'><tr><th>Id.</th><th>Data</th><th>Kategoria</th><th>Nazwa</th><th>Koszt[zł]</th></tr>";
        for (Wydatek w : lw.getWydatki()) {
            if (w.dataString().substring(3).equals(miesiac.getSelectedItem())) {
                info +="<tr><td style='border: 1px solid black;'>"+ (i + 1) + ".</td>";
                info +="<td style='border: 1px solid black;'>"+ w.dataString() +"</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKategoria() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getNazwa() + "</td>";
                info +="<td style='border: 1px solid black;'>"+ w.getKwota() + "</td></tr>";
                i++;
            }
        }
        info += "</table></html>";
        lista.setText(info);
    }


    public ActionListener wrocW = e -> {
        dialog.dispose();
    };

    public ActionListener znajdzDzienW = e -> {
        lista.setText("");
        zmienWydatkiDzien();
    };

    public ActionListener znajdzMiesiacW = e -> {
        lista.setText("");
        zmienWydatkiMiesiac();
    };
    
}
