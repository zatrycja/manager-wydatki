import javax.swing.*;
import java.awt.event.ActionListener;

public class Akcje {

    private GUI gui;
    private JButton wydatki;
    private JButton raporty;
    private JButton wstecz;
    private JButton zapasowyWstecz;
    private MenuWydatki w;
    private MenuRaporty r;

    public Akcje() {
        gui = new GUI();
        wstecz = new JButton("Wstecz");
        zapasowyWstecz = new JButton("Wstecz");
        r = new MenuRaporty(gui,this.zapasowyWstecz);
        w = new MenuWydatki(gui,this.wstecz);

        wydatki = gui.getWydatki();
        raporty = gui.getRaporty();

        wydatki.addActionListener(pokazWydatki);
        raporty.addActionListener(pokazRaporty);
        wstecz.addActionListener(cofnij);
        zapasowyWstecz.addActionListener(cofnij);
    }

    public ActionListener pokazWydatki = e -> {
        gui.setOpcja(w);
        gui.getOpcja().pokaz();

    };

    public ActionListener pokazRaporty = e -> {
        gui.setOpcja(r);
        gui.getOpcja().pokaz();
    };

    public ActionListener cofnij = e -> {
        gui.getOpcja().cofnij();
    };
}
