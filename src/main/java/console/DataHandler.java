package console;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pojo.ClassTeacher;
import pojo.Classname;
import pojo.Floor;
import pojo.Room;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class DataHandler {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void importTables() {

        File file = Paths.get(System.getProperty("user.dir"),
                "src", "main", "resources", "schooldata.csv").toFile();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.lines().skip(1).forEach(line -> {
                String[] lineData = line.split(";");

                ClassTeacher teacher = new ClassTeacher(lineData[0], lineData[3],lineData[2], lineData[1]);
                Classname classname = new Classname(lineData[4], Integer.parseInt(String.valueOf(lineData[4].charAt(0))), Integer.parseInt(lineData[5]));
                Room room = new Room(lineData[5], lineData[5].charAt(0) == '1' ? Floor.GROUND : Floor.FIRST);

                teacher.setClassname(classname);
                classname.setClassTeacher(teacher);
                classname.setRoom(room);
                room.setClassname(classname);

                em.getTransaction().begin();
                em.persist(teacher);
                em.persist(classname);
                em.persist(room);
                em.getTransaction().commit();

            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Room room_findByClassName(String classname) {
        return em.createNamedQuery("room_findByClassName", Room.class)
                .setParameter("classname", classname)
                .getSingleResult();
    }

    public List<Room> room_findAll() {
        return em.createNamedQuery("room_findAll", Room.class)
                .getResultList();
    }

    public List<Room> room_findByFloor(Floor floor) {
        return em.createNamedQuery("room_findByFloor", Room.class)
                .setParameter("floor", floor)
                .getResultList();
    }

    public Long room_countAll() {
        return em.createNamedQuery("room_countAll", Long.class)
                .getSingleResult();
    }

    public Classname classname_findByName(String name) {
        return em.createNamedQuery("classname_findByName", Classname.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Classname> classname_findAll() {
        return em.createNamedQuery("classname_findAll", Classname.class)
                .getResultList();
    }

    public List<Classname> classname_findByFloor(Floor floor) {
        return em.createNamedQuery("classname_findByFloor", Classname.class)
                .setParameter("floor", floor)
                .getResultList();
    }

    public Long classname_countAll() {
        return em.createNamedQuery("classname_countAll", Long.class)
                .getSingleResult();
    }

    public List<ClassTeacher> classTeachers_findByName(String lastname) {
        return em.createNamedQuery("classTeacher_findByName", ClassTeacher.class)
                .setParameter("lastname", lastname)
                .getResultList();
    }

    public ClassTeacher classTeacher_findByClassName(String classname) {
        return em.createNamedQuery("classTeacher_findByClassname", ClassTeacher.class)
                .setParameter("classname", classname)
                .getSingleResult();
    }

    public List<ClassTeacher> classTeacher_findByGrade(int grade) {
        return em.createNamedQuery("classTeacher_findByGrade", ClassTeacher.class)
                .setParameter("grade", grade)
                .getResultList();
    }

    public Long classTeacher_countAll() {
        return em.createNamedQuery("classTeacher_countAll", Long.class)
                .getSingleResult();
    }

    public void open() {
        emf = Persistence.createEntityManagerFactory("PU_default");
        em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }


}
