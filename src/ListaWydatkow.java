import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaWydatkow implements Serializable {
    private List<Wydatek> wydatki;

    public ListaWydatkow(){
        wydatki = new ArrayList<>();
    }

    public void zapiszWydatki() {
        try {
            sortuj();
            ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("wydatki.txt"));
            for (int i = 0; i < wydatki.size(); i++)
                 zapis.writeObject(wydatki.get(i));
            zapis.close();
        }
        catch (EOFException e) {

        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Brak pliku");
        }
    }

    public List<Wydatek> getWydatki() {
        return wydatki;
    }

    public void zaladujWydatki() {
        wydatki.clear();
        try {
            ObjectInputStream wczytaj = new ObjectInputStream(new FileInputStream("wydatki.txt"));
            while (true){
               Wydatek w = (Wydatek) wczytaj.readObject();
                if (w == null) break;
                wydatki.add((Wydatek) w);
            }

            wczytaj.close();


        }catch (EOFException e) {

        }
        catch (IOException e) {
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Brak pliku 'Wydatek.java'.");
        }
        sortuj();
    }

    public void dodaj(Wydatek wydatek) {
        wydatki.add(wydatek);
    }

    public void sortuj() {
        sortujRok();
        for (int i = 0; i < wydatki.size(); i++) {
            for (int k = 0; k < wydatki.size(); k++)
                if (wydatki.get(k).getData().getRok() >= (wydatki.get(i).getData().getRok()))
                    if (wydatki.get(k).getData().getMiesiac() >= (wydatki.get(i).getData().getMiesiac()))
                        if (wydatki.get(k).getData().getDzien() > (wydatki.get(i).getData().getDzien()))
                            swap(wydatki.get(k), wydatki.get(i));
        }

    }

    public void sortujRok() {
        for (int i = 0; i < wydatki.size(); i++)
            for (int k = 0; k < wydatki.size(); k++)
                if (wydatki.get(k).getData().getRok() > (wydatki.get(i).getData().getRok()))
                    swap(wydatki.get(k), wydatki.get(i));

    }

    public void swap (Wydatek w1, Wydatek w2) {
        Wydatek pomocniczy;
        pomocniczy = w1;
        w1 = w2;
        w2 = pomocniczy;
    }
}
