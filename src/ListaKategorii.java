import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaKategorii {
   private List<String> kategorie;

    public ListaKategorii() throws IOException {
        kategorie = new ArrayList<>();
        BufferedReader w = new BufferedReader(new FileReader("kategorie.txt"));

        while (true) {
            String nazwa = w.readLine();
            if (nazwa == null) break;
            kategorie.add(nazwa);
        }
        w.close();
    }

    public List<String> getKategorie() {
        return kategorie;
    }
}