import java.time.LocalDateTime;
import java.util.ArrayList;

public class Human {
    //состояние человека
    private State state;
    
    private Date birthdayDate;
    private Date deathDayDate;

    private String name;
    private String surname;
    private Gender gender;

    // родители (мама и папа, или кто то из них)
    private PresenceParents presence;
    private Parents parents;

    // бабушки дедушки
    private ArrayList<Human> grandparents = new ArrayList<>();
    
    // тети дяди
    private ArrayList<Human> unclesAunts = new ArrayList<>();

    // братья сестры
    private ArrayList<Human> brothersSisters = new ArrayList<>();

    // дети
    private ArrayList<Human> children = new ArrayList<>();



    private void human(String name, String surname, Gender gender,
                 int birthday, int monthOfBirth, int yearOfBirth){
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdayDate = new Date(birthday, monthOfBirth, yearOfBirth);

        this.state = State.alive;
    }


    public Human(String name, String surname, Gender gender,
                 int birthday, int monthOfBirth, int yearOfBirth){
        if (numberCheck(name) || numberCheck(surname)) {
            human(name, surname, gender, birthday, monthOfBirth, yearOfBirth);
        } else {
            System.out.println("Имя и фамилия не должны содержать цифр");
        }
    }


    public Human(String name, String surname, Gender gender){
        if (numberCheck(name) || numberCheck(surname)) {
            LocalDateTime dateNow = LocalDateTime.now();
            human(name, surname, gender, dateNow.getDayOfMonth(), dateNow.getMonthValue(), dateNow.getYear());
        } else {
            System.out.println("Операция не выполнена, тк имя и фамилия не должны содержать цифр");
        }
    }    


    public Human(String name, String surname) {
        if (numberCheck(name) || numberCheck(surname)) {
            LocalDateTime dateNow = LocalDateTime.now();
            human(name, surname, Gender.man, dateNow.getDayOfMonth(), dateNow.getMonthValue(), dateNow.getYear());
        } else {
            System.out.println("Операция не выполнена, тк имя и фамилия не должны содержать цифр");
        }
    }


    public Human() {
        LocalDateTime dateNow = LocalDateTime.now();
        human("name", "surname", Gender.man, dateNow.getDayOfMonth(), dateNow.getMonthValue(), dateNow.getYear());
    }    


    private boolean numberCheck(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }


    public void setGender(Gender newGender) {
        if (this.gender!=newGender) {
            this.gender = newGender;
        } else {
            System.out.println("Пол данного человека совпадает новым");
        }
    }


    public void setDayOfDeath(int dayOfDeath, int monthOfDeath, int yearOfDeath) {
        Date check = new Date(dayOfDeath, monthOfDeath, yearOfDeath);
        if (!check.comparison(this.birthdayDate)) {
            System.out.println("Действие не было выполнено, тк дата смерти указана раньше даты рождения");
        } else {
            this.deathDayDate = check;
        }

        this.state = State.dead;
    }


    public void setBirthday(int birthday, int monthOfBirth, int yearOfBirth) {
        Date check = new Date(birthday, monthOfBirth, yearOfBirth);
        if (presence==PresenceParents.present
            && parents.getParents().get(0).getBirthdayDate().comparison(this.birthdayDate)
            && parents.getSizeFamily()==2
            & parents.getParents().get(1).getBirthdayDate().comparison(this.birthdayDate)) {

            if (!deathDayDate.isEmpty() && !deathDayDate.comparison(check)) {
                birthdayDate = check;
            }
        } 
    }
    

    public void indicateParents(Human parentOne, Human parentTwo) {
        // указать родителей данного человека
        if (parentOne.birthdayDate.comparison(this.birthdayDate)
            || parentTwo.birthdayDate.comparison(this.birthdayDate)) {
            parents = new Parents(parentOne, parentTwo);
            presence = PresenceParents.present;
            parentOne.children.add(this);
            parentTwo.children.add(this);
            checkingParentsChildren(parentOne);
            checkingParentsChildren(parentTwo);

            grandparents.addAll(parentOne.parents.getParents());
            grandparents.addAll(parentTwo.parents.getParents());

            unclesAunts.addAll(parentOne.getBrothersSisters());
            unclesAunts.addAll(parentTwo.getBrothersSisters());
            } else {
            System.out.println("Действие не выполнено, тк ребенок не может быть старше родителя");
        }
    }


    public void indicateParents(Human parent) {
        // указать родителя данного человека
        if (parent.birthdayDate.comparison(this.birthdayDate)) {
            parents = new Parents(parent);
            presence = PresenceParents.present;
            parent.children.add(this);
            checkingParentsChildren(parent);
            if (parent.getPresenceParents()==PresenceParents.present) {
                grandparents.addAll(parent.parents.getParents());
            }
            unclesAunts.addAll(parent.getBrothersSisters());
        } else {
            System.out.println("Действие не выполнено, тк ребенок не может быть старше родителя");
        }
    }


    private void checkingParentsChildren(Human parent) {
        if (parent.children.size()>1) {
            for (int i = 0; i < parent.children.size(); i++) {
                addBrotherSister(parent.children.get(i));
            }  
        }
    }


    public void addBrotherSister(Human brother) {
        if (parents.getParents().indexOf(brother)==-1
            | this!=brother
            | children.indexOf(brother)==-1) {
            
            brothersSisters.add(brother);
            if (!children.isEmpty()) {
                for (Human child : children) {
                    child.addUnclesAunts(brother);
                }
            }
            if (!brother.getChildren().isEmpty()) {
                for (int i = 0; i < brother.getChildren().size(); i++) {
                    Human childBrother = brother.getChildren().get(i);
                    childBrother.addUnclesAunts(this);
                }
            }
        }
    }


    public void addChildren(Human kid) {
        if (this.birthdayDate.comparison(kid.birthdayDate)) {
            children.add(kid);
            if (!this.brothersSisters.isEmpty()) {
                for (Human human : brothersSisters) {
                    kid.addUnclesAunts(human);
                }
            }
            if (!this.parents.getParents().isEmpty()) {
                for (Human human : parents.getParents()) {
                    kid.addGrandparents(human);
                }
            }
        } else {
            System.out.println("действие не выполнено, тк ребенок не может быть старше родителей");
        }
    }


    public void addUnclesAunts(Human uncl) {
        unclesAunts.add(uncl);
    }


    public void addGrandparents(Human grantparent) {
        grandparents.add(grantparent);
    }


    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


    public Gender getGender() {
        return gender;
    }


    public Date getBirthdayDate() {
        return birthdayDate;
    }


    public Date getDeathDayDate() {
        if (this.state == State.alive) {
            System.out.println("Этот человек жив");
        }
        return deathDayDate;
    }


    public State getState() {
        return state;
    }


    public PresenceParents getPresenceParents() {
        return presence;
    }


    public ArrayList<Human> getParents() {
        return parents.getParents();
    }


    public ArrayList<Human> getGrandparents() {
        return grandparents;
    }


    public ArrayList<Human> getUnclesAunts() {
        return unclesAunts;
    }


    public ArrayList<Human> getChildren() {
        return children;
    }


    public ArrayList<Human> getBrothersSisters() {
        return brothersSisters;
    }
}
