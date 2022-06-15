import javax.swing.*;
import java.math.BigDecimal;

public abstract class Raporty {
    public void raport(ListaWydatkow lw, ListaKategorii lk, JLabel lista) {
        int i = 0;
        BigDecimal razem = new BigDecimal("0");
        String info = "<html><table style='border: 1px solid black; padding: 2px;'><tr><th>Lp.</th><th>Kategoria</th><th>Razem [zł]</th></tr>";
        for (String k : lk.getKategorie()) {
            BigDecimal suma = new BigDecimal("0");
            for (Wydatek w : lw.getWydatki()) {
                if (w.getKategoria().equals(k))
                    suma = suma.add(w.getKwota());
            }
            info +="<tr><td style='border: 1px solid black;'>"+ (i + 1) + ".</td>";
            info +="<td style='border: 1px solid black;'>"+ k +"</td>";
            info +="<td style='border: 1px solid black;'>"+ suma + "</td></tr>";
            i++;
            razem = razem.add(suma);

        }

        info += "</table><p>Łącznie wydano: " +razem+ "zł</p></html>";
        lista.setText(info);
    }
}
