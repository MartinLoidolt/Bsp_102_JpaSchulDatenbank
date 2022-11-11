package pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(
                name = "classTeacher_findByName",
                query = "SELECT classTeacher FROM ClassTeacher classTeacher WHERE classTeacher.lastname = :lastname"
        ),
        @NamedQuery(
                name = "classTeacher_findByClassname",
                query = "SELECT classTeacher FROM ClassTeacher classTeacher WHERE classTeacher.classname.name = :classname"
        ),
        @NamedQuery(
                name = "classTeacher_findByGrade",
                query = "SELECT classTeacher FROM ClassTeacher classTeacher WHERE classTeacher.classname.grade = :grade"
        ),
        @NamedQuery(
                name = "classTeacher_countAll",
                query = "SELECT COUNT(classTeacher) FROM ClassTeacher classTeacher"
        )
})
public class ClassTeacher {
    @Id
    @GeneratedValue
    private int teacherId;
    @NonNull
    @Column(length = 2)
    private String initials;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String title;
    @OneToOne(mappedBy = "classTeacher")
    @ToString.Exclude
    private Classname classname;
}
