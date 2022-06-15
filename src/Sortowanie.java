import java.util.List;

public abstract class Sortowanie {

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

    public void sortujMiesiecznie(List<String> daty) {
        sortujRokMiesiecznie(daty);
        for (int i = 0; i < daty.size(); i++) {
            for (int k = 0; k < daty.size(); k++)
                if (Integer.parseInt(daty.get(k).substring(3)) >= Integer.parseInt(daty.get(i).substring(3)))
                    if (Integer.parseInt(daty.get(k).substring(0,1)) > Integer.parseInt(daty.get(i).substring(0,1)))
                        swap(i, k, daty);
        }

    }

    public void sortujRokMiesiecznie(List<String> daty) {
        for (int i = 0; i < daty.size(); i++)
            for (int k = 0; k < daty.size(); k++)
                if (Integer.parseInt(daty.get(k).substring(3)) < Integer.parseInt(daty.get(i).substring(3)))
                    swap(i, k, daty);

    }
}
