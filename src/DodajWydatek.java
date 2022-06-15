import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DodajWydatek extends JDialog {
    private ListaWydatkow lw;
    private JTextField nazwaWydatku;
    private JSpinner kwota;
    private JComboBox<String> kategorie;
    private JTextField data;
    private JButton dodaj;
    private JButton anuluj;
    private Wydatek edytowany;


    public DodajWydatek (JFrame okienko, ListaWydatkow lw) throws IOException {
        super(okienko,"Dodawanie nowego wydatku",true);
        this.lw = lw;
        JPanel panel = new JPanel();
        JLabel etykietaDaty = new JLabel("Data:");
        JLabel etykietaNazwy = new JLabel("Nazwa wydatku:");
        JLabel etykietaKwota = new JLabel("Kwota [zł]:");
        JLabel etykietaKategorie = new JLabel("Kategoria:");
        data = new JTextField(dzisiaj(),10 );
        nazwaWydatku = new JTextField("wydatek",10);
        SpinnerModel model = new SpinnerNumberModel(2.5, 0, 10000, 0.01);
        kwota = new JSpinner(model);
        kategorie = new JComboBox<String>();
            setKategorie();
        dodaj = new JButton("Dodaj");
        anuluj = new JButton("Anuluj");

        dodaj.addActionListener(dodajW);
        anuluj.addActionListener(anulujW);

        panel.add(etykietaDaty);
        panel.add(data);
        panel.add(etykietaNazwy);
        panel.add(nazwaWydatku);
        panel.add(etykietaKwota);
        panel.add(kwota);
        panel.add(etykietaKategorie);
        panel.add(kategorie);

        panel.add(dodaj);
        panel.add(anuluj);


        getContentPane().add(panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public DodajWydatek (JFrame okienko, ListaWydatkow lw, Wydatek w) throws IOException {      //modyfikacja wydatku
        super(okienko,"Modyfikacja wydatku",true);
        this.lw = lw;
        edytowany = w;
        JPanel panel = new JPanel();
        JLabel etykietaDaty = new JLabel("Data:");
        JLabel etykietaNazwy = new JLabel("Nazwa wydatku:");
        JLabel etykietaKwota = new JLabel("Kwota [zł]:");
        JLabel etykietaKategorie = new JLabel("Kategoria:");
        data = new JTextField(w.dataString(),10 );
        nazwaWydatku = new JTextField(w.getNazwa(),10);
        SpinnerModel model = new SpinnerNumberModel(2.5, 0, 10000, 0.01);
        kwota = new JSpinner(model);
        String s = ""+w.getKwota();
        Double kw = Double.parseDouble(s);
        kwota.setValue(kw);
        kategorie = new JComboBox<String>();
        setKategorie();
        kategorie.setSelectedItem(w.getKategoria());
        dodaj = new JButton("Zatwierdź");
        anuluj = new JButton("Anuluj");

        dodaj.addActionListener(zmienW);
        anuluj.addActionListener(anulujW);

        panel.add(etykietaDaty);
        panel.add(data);
        panel.add(etykietaNazwy);
        panel.add(nazwaWydatku);
        panel.add(etykietaKwota);
        panel.add(kwota);
        panel.add(etykietaKategorie);
        panel.add(kategorie);

        panel.add(dodaj);
        panel.add(anuluj);


        getContentPane().add(panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public void setKategorie() throws IOException {
        ListaKategorii lk = new ListaKategorii();
        int i = 0;
        for (String k: lk.getKategorie()){
            kategorie.addItem(lk.getKategorie().get(i));
            i++;
        }
    }

    public Data data() {
        String tekst = data.getText();
        int dzien, miesiac, rok;
        try {
            if (tekst.length() <= 1) throw new NumberFormatException();
            dzien = Integer.parseInt(tekst.substring(0,2));
            miesiac = Integer.parseInt(tekst.substring(3,5));
            rok = Integer.parseInt(tekst.substring(6,10));

            if (dzien < 1 || dzien > 31) throw new NumberFormatException();
            if (miesiac < 1 || miesiac > 12) throw new NumberFormatException();
            if (rok < 1000 || rok > 9999) throw new NumberFormatException();
           return new Data(dzien,miesiac,rok);

        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Wprowadzono błędną date\nformat daty: dd-mm-rrrr");
        }
        return null;
    }

    public String dzisiaj() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-YYYY");
        Date date = new Date(System.currentTimeMillis());

        return (formatter.format(date));
    }

    public ActionListener dodajW = e -> {
        Wydatek w = new Wydatek(data(),(String)kategorie.getSelectedItem(),nazwaWydatku.getText(),(Double) kwota.getValue());
        lw.zaladujWydatki();
        lw.dodaj(w);
        lw.zapiszWydatki();
        dispose();
    };

    public ActionListener anulujW = e -> {
        dispose();
    };

    public ActionListener zmienW = e -> {
        lw.getWydatki().remove(edytowany);
        lw.zapiszWydatki();
        Wydatek w = new Wydatek(data(),(String)kategorie.getSelectedItem(),nazwaWydatku.getText(),(Double) kwota.getValue());
        lw.zaladujWydatki();
        lw.dodaj(w);
        lw.zapiszWydatki();
        dispose();
    };
}
