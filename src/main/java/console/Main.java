package console;

import pojo.Floor;

public class Main {
    public static void main(String[] args) {
        DataHandler handler = new DataHandler();

        handler.open();
        handler.importTables();

        System.out.println("rooms imported: " + handler.room_countAll());
        System.out.println("classes imported: " + handler.classname_countAll());
        System.out.println("classteacher imported: " + handler.classTeacher_countAll());

        System.out.println("ALLE RÄUME-------------------------------------------------------------------------------");
        System.out.println(handler.room_findAll());
        System.out.println("RÄUME IM FLOOR FIRST---------------------------------------------------------------------");
        System.out.println(handler.room_findByFloor(Floor.FIRST));

        System.out.println("ALLE Klassen-----------------------------------------------------------------------------");
        System.out.println(handler.classname_findAll());
        System.out.println("ALLE KLASSEN IM FLOOR GROUND-------------------------------------------------------------");
        System.out.println(handler.classname_findByFloor(Floor.GROUND));

        System.out.println("ALLE LEHRER IM GRADE 3-------------------------------------------------------------------");
        System.out.println(handler.classTeacher_findByGrade(3));
        System.out.println("LEHRER DER 1BHIF-------------------------------------------------------------------------");
        System.out.println(handler.classTeacher_findByClassName("1BHIF"));
        System.out.println("LEHRER DER PAULUS HEIßT------------------------------------------------------------------");
        System.out.println(handler.classTeachers_findByName("Paulus"));
        handler.close();
    }
}
