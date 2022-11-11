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
                name = "room_findByClassName",
                query = "SELECT room FROM Room room WHERE room.classname.name = :classname"
        ),
        @NamedQuery(
                name = "room_findAll",
                query = "SELECT room FROM Room room"
        ),
        @NamedQuery(
                name = "room_findByFloor",
                query = "SELECT room FROM Room room WHERE room.floor = :floor"
        ),
        @NamedQuery(
                name = "room_countAll",
                query = "SELECT COUNT(room) FROM Room room"
        )
})
public class Room {
    @Id
    @GeneratedValue
    private int roomId;
    @NonNull
    private String name;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Floor floor;
    @OneToOne(mappedBy = "room")
    @JoinColumn(name = "classname_id")
    @ToString.Exclude
    private Classname classname;
}
