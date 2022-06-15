import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wydatek implements Serializable {
    private Data data;
    private String kategoria;
    private String nazwa;
    private BigDecimal kwota;

    public Wydatek(Data data, String kategoria, String nazwa, Double kwota) {
        this.kwota = new BigDecimal(kwota).setScale(2, RoundingMode.HALF_UP);
        this.data = data;
        this.kategoria = kategoria;
        this.nazwa = nazwa;
    }

    public Data getData() {
        return data;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String dataString() {
        String data = "";
        if (this.data.getDzien() < 10)
            data += "0"+this.data.getDzien();
        else data += this.data.getDzien();
        if (this.data.getMiesiac() < 10)
            data += "-0"+this.data.getMiesiac();
        else data += "-"+this.data.getMiesiac();
        data += "-"+this.data.getRok();

        return data;
    }
}
