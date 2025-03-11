package scor05;
import java.util.Comparator;

public class DigimonComparator implements Comparator<Digimon> {
    public int compare(Digimon d1, Digimon d2){
        return (d1.getType1().compareTo(d2.getType1()) < 0) ? -1 : (d1.getType1().compareTo(d2.getType1()) > 0) ? 1 : 0;
    }
}
