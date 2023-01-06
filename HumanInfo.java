
public class HumanInfo{

    private Human human;

    public HumanInfo(Human h) {
        human = h;
    }

    public void printInfo() {
        printInfo(human);
    }

    
    private void printInfo(Human h) {
        System.out.printf("%s %s\nПол: %s\n", h.getName(), h.getSurname(), h.getGender());
        System.out.println(h.getBirthdayDate().toString());
        if (h.getState()==State.dead){
            System.out.println(h.getDeathDayDate().toString());                  
        }
    }


    public void printParents() {
        System.out.println();
        if (human.getPresenceParents()==PresenceParents.present) {
            for (int i = 0; i < human.getParents().size(); i++) {
                System.out.printf("Родитель %d\n", i);
                printInfo(human.getParents().get(i));
            }
        } else {
            System.out.println("Родители у данного человека не указаны");
        }
    }


    public void printChildren() {
        System.out.println();
        if (!human.getChildren().isEmpty()) {
            System.out.println("Дети:");
            for (Human ch : human.getChildren()) {
                printInfo(ch);    
            }
        } else {
            System.out.println("Детей у данного человека нет");
        }
    }


    public void printBrotherSister() {
        System.out.println();
        if (!human.getBrothersSisters().isEmpty()) {
            System.out.println("Братья и сетры:");
            for (Human hum : human.getBrothersSisters()) {
                printInfo(hum);
            }
        } else {
            System.out.println("Братьев и сестер у данного человека нет");
        }
    }


    public void printGrandparents() {
        System.out.println();
        if (!human.getGrandparents().isEmpty()) {
            for (Human hum : human.getGrandparents()) {
                printInfo(hum);
            }
        } else {
            System.out.println("Бабушек и дедушек у данного человека нет");
        }
    }


    public void printUnclesAunts() {
        System.out.println();
        if (!human.getUnclesAunts().isEmpty()) {
            for (Human hum : human.getUnclesAunts()) {
                printInfo(hum);
            }
        } else {
            System.out.println("Теть и дядь у данного человека нет");
        }
    }


    public void printFullInfo() {
        printInfo();
        printParents();
        printBrotherSister();
        printChildren();
        printGrandparents();
        printUnclesAunts();
    }
}
