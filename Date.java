import java.util.HashMap;

public class Date {
    private int day;
    private int month;
    private int year;
    private boolean empty = false;
    

    
    public Date(int d, int m, int y) {
        if (dateСheck(d, m, y)) {
            day = d;
            month = m;
            year = y; 
            empty = true;
        } else {
            System.out.println("Введите корректную дату");
        }
    }


    public boolean comparison(Date value) {
        // возвращает true если this "старше" передаваемой даты
        if (this.year>value.year 
            || this.month>value.month
            || this.day>value.month) {
            return true; 
        } 
        return false;
    }


    public int differenceYears(Date date) {      
        return Math.abs(date.year-this.year);
    }


    public int getDay() {
        return this.day;
    }


    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }


    @Override
    public String toString() {
        int[] arr = new int[]{this.day, this.month, this.year};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i!=2) {
                result.append(arr[i]);
                result.append(":");
            } else {
                result.append(arr[i]);
            }
            
        }
        return result.toString();
    }


    public boolean isEmpty() {
        if (empty) {
            return true;
        }
        return false;
    }

    
    private boolean dateСheck(int d, int m, int y) {
        // проверяет может ли существовать такая дата
        HashMap<Integer, Integer> numberDaysInMonth = new HashMap<>();
        int[] countDay = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int month = 0; month < countDay.length; month++) {
            numberDaysInMonth.put(month+1, countDay[month]);
        }

        if (d<=0 | numberDaysInMonth.containsKey(m)==false
            || d>numberDaysInMonth.get(m)) {
            return false;
        }
        return true;
    }
}
