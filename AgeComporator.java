import java.util.Comparator;

public class AgeComporator implements Comparator<Human>{

    @Override
    public int compare(Human humOne, Human humTwo) {
        return Integer.compare(humOne.getAge(), humTwo.getAge());
    }
    
}
