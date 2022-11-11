package pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(
                name = "classname_findByName",
                query = "SELECT classname FROM Classname classname WHERE classname.name = :name"
        ),
        @NamedQuery(
                name = "classname_findAll",
                query = "SELECT classname FROM Classname classname"
        ),
        @NamedQuery(
                name = "classname_findByFloor",
                query = "SELECT classname FROM Classname classname WHERE classname.room.floor = :floor"
        ),
        @NamedQuery(
                name = "classname_countAll",
                query = "SELECT COUNT(classname) FROM Classname classname"
        )
})
public class Classname {
    @Id
    @GeneratedValue
    private int classId;
    @NonNull
    private String name;
    @NonNull
    private int grade;
    @NonNull
    private int size;
    @OneToOne
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    private Room room;
    @OneToOne
    @JoinColumn(name = "classTeacher_id")
    @ToString.Exclude
    private ClassTeacher classTeacher;
}
