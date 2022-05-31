import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class WrongDate extends Exception { }
class WrongStudentName extends Exception { }
class WrongMenu extends Exception {}


class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {
            System.out.println("Błędne dane liczbowe");
            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            }catch(WrongDate e) {
                System.out.println("Błędna data!");
            }catch(WrongMenu e)
                      {
                  System.out.println("Podales zly znak!");
                      }
        }
    }

    public static int menu() throws WrongMenu {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        String wybor=scan.nextLine();
        if (wybor.charAt(0)<48||wybor.charAt(0)>58)
        {
          throw new WrongMenu();
        }
        int wybor1=Integer.parseInt(wybor);
return wybor1;
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongDate {
        var name = ReadName();
        System.out.println("Podaj wiek: ");
        var age = scan.nextInt();
        if(age<0||age>100)
        {
          throw new IOException();
        }
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYY");
        var date = scan.nextLine();
       // String date1= date;
       // DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        ////LocalDate dateTime = LocalDate.parse(date1, myFormatObj);
        //String strDate = myFormatObj.format(dateTime);  
      
        
        if (date.charAt(2)!=45||date.charAt(5)!=45)
        {
          throw new WrongDate();
        }
        (new Service1()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service1()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service1()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}