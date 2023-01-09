import java.util.ArrayList;
import java.util.Iterator;

public class Family implements Iterator<Human>{
    private ArrayList<Human> humans = new ArrayList<>();

    private int indexHumans = 0;

    public Family(Human human){
        addHuman(human);
    }

    public Family() {
        System.out.println("addHuman() для заполнения семьи");
    }

    public void addHuman(Human human) {
        if (humans.indexOf(human)==-1) {
            humans.add(human);
        }
        if (human.getPresenceParents()==PresenceParents.present) {
            for (Human hum : human.getParents()) {
                if (humans.indexOf(hum)==-1) {
                    humans.add(hum);
                    addHuman(hum);
                }
            }
        }
        if (!human.getBrothersSisters().isEmpty()) {
            for (Human hum : human.getBrothersSisters()) {
                if (humans.indexOf(hum)==-1) {
                    humans.add(hum);
                    addHuman(hum);
                }
            }
        }
        if (!human.getChildren().isEmpty()) {
            for (Human hum : human.getChildren()) {
                if (humans.indexOf(hum)==-1) {
                    humans.add(hum);
                    addHuman(hum);
                }
            }
        }
        if (!human.getGrandparents().isEmpty()) {
            for (Human hum : human.getGrandparents()) {
                if (humans.indexOf(hum)==-1) {
                    humans.add(hum);
                    addHuman(hum);
                }
            }
        }
        if (!human.getUnclesAunts().isEmpty()) {
            for (Human hum : human.getUnclesAunts()) {
                if (humans.indexOf(hum)==-1) {
                    humans.add(hum);
                    addHuman(hum);
                }
            }
        }
    }


    public ArrayList<Human> getFamily() {
        return humans;
    }


    public int getSizeFamily() {
        return humans.size();
    }


    public boolean familyMember(Human hum) {
        if (humans.indexOf(hum)!=-1) {
            return true;
        } else {
            return false;
        }
    }


    public ArrayList<Human> findByName(String name) {
        ArrayList<Human> result = new ArrayList<>();
        for (Human hum : humans) {
            if (hum.getName().equals(name)) {
                result.add(hum);
            }
        }
        return result;
    }


    public ArrayList<Human> findByGender(Gender gender) {
        ArrayList<Human> result = new ArrayList<>();
        for (Human hum : humans) {
            if (hum.getGender()==gender) {
                result.add(hum);
            }
        }
        return result;
    }


    public Human findById(int id) {
        for (Human hum : humans) {
            if (hum.getId()==id) {
                return hum;
            }
        }
        return null;
    }


    public void remove(Human hum) {
        humans.remove(hum);
    }


    public void removeById(int id) {
        int count = humans.size();
        for (int i = 0; i < count; i++) {
            if (humans.get(i).getId()==id) {
                humans.remove(i);
            }
        }
    }


    public ArrayList<Human> sortByAge() {
        ArrayList<Human> result = humans;
        result.sort(new AgeComporator());
        return result;
    }


    @Override
    public boolean hasNext() {
        return indexHumans++<humans.size();
    }


    @Override
    public Human next() {
        return humans.get(indexHumans);
    }

}
