import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Save implements Reading, Storage{
    
    private ArrayList<String> info = new ArrayList<>();

    public Save(Human human) {
        save(human);
    }


    public Save(Family family) {
        for (Human hum : family.getFamily()) {
            save(hum);
            info.add("_______________________________");
        }
    }


    private void save(Human human) {
        info.addAll(translationInfo(human));
        if (human.getPresenceParents()==PresenceParents.present) {
            info.add("Родители");
            info.addAll(translationInfo(human.getParents()));
        }
        if (!human.getBrothersSisters().isEmpty()) {
            info.add("БратьяСестры");
            info.addAll(translationInfo(human.getBrothersSisters()));
        }
        if (!human.getChildren().isEmpty()) {
            info.add("Дети");
            info.addAll(translationInfo(human.getChildren()));
        }
        if (!human.getGrandparents().isEmpty()) {
            info.add("БабушкиДедушки");
            info.addAll(translationInfo(human.getGrandparents()));
        }
        if (!human.getUnclesAunts().isEmpty()) {
            info.add("ТетиДяди");
            info.addAll(translationInfo(human.getUnclesAunts()));
        }
        System.out.println(info);
    }


    private ArrayList<String> translationInfo(ArrayList<Human> hums) {
        ArrayList<String> result = new ArrayList<>();
        for (Human human : hums) {
            result.addAll(translationInfo(human));
        }
        return result;
    }


    private ArrayList<String> translationInfo(Human hum) {
        ArrayList<String> result = new ArrayList<>();
        result.add("id:"+Integer.toString(hum.getId()));
        result.add("name:"+hum.getName());
        result.add("surname:"+hum.getSurname());
        result.add("gender:"+hum.getGender().toString());
        result.add("birthday:"+hum.getBirthdayDate().toString());
        if (hum.getState()==State.dead) {
            result.add("deathday:"+hum.getDeathDayDate().toString());
        }
        return result;
    }


    @Override
    public void save(String path) {
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".txt")))
        {
            for (String val : info) {
                writer.write(val);
                writer.append('\n');
                
            }
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }
        System.out.println("Запись завершена");
        
    }

    
    @Override
    public Human read(String path) {
        try(BufferedReader br = new BufferedReader (new FileReader(path + ".txt")))
        {   

            ArrayList<String> values = new ArrayList<>();
            String s;
            while((s=br.readLine())!=null){
                 values.add(s);
            }

            return parseString(values);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }        
        return null;
    }

    private Human parseString(ArrayList<String> vals) {
        
        for (int i = 0; i < vals.size(); i++) {
            String str = vals.get(i);
            if (i==0 || Human.getHuman(Integer.valueOf(str.substring(str.indexOf(":")+1)))!=null) {
                return Human.getHuman(Integer.valueOf(str.substring(str.indexOf(":")+1)));
            } 
            // распарсить строки и создать экземпляры Human
        }
                
        return null;
    }
}
