import java.util.ArrayList;

public class Parents {
    
    private ArrayList<Human> parents = new ArrayList<>(); 


    public Parents(Human h1, Human h2){
        parents.add(h1);
        parents.add(h2);
    }


    public Parents(Human h) {
        parents.add(h);
    }


    public ArrayList<Human> getParents() {
        return parents;
    }


    public int getSizeFamily() {
        return parents.size();
    }


    public void addParent(Human parent) {
        if (parents.size()<2) {
            parents.add(parent);
        } else {
            System.out.println("Родителей уже двое");
        }
    }


    public void changeParent(Human whom, Human onWhom) {
        if (parents.indexOf(whom)!=-1 || parents.indexOf(onWhom)==-1) {
            parents.remove(parents.indexOf(whom));
            parents.add(onWhom);
        } else {
            if (parents.indexOf(whom)==-1) {
                System.out.println("Операция не выполнена, тк человека которого нужно заменить нету в родителях");
            }
            if (parents.indexOf(onWhom)!=-1) {
                System.out.println("Операция не выполнена, тк человек которого нужно сделать родителем, уже им является");
            }
        }
    }  

    public void removeParent(Human parent) {
        if (parents.indexOf(parent)!=-1) {
            parents.remove(parents.indexOf(parent));
        } else {
            System.out.println("операция не выполнена, тк этот человек не является родителем");
        }
    }   

}
