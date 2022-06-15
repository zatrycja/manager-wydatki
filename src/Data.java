import java.io.Serializable;

public class Data implements Serializable {
    private int data[];

    public Data(int dzien, int miesiac, int rok) {
        data = new int[3];
        data[0] = dzien;
        data[1] = miesiac;
        data[2] = rok;
    }

    public int getDzien() {
        return data[0];
    }

    public int getMiesiac() {
        return data[1];
    }

    public int getRok() {
        return data[2];
    }
}
