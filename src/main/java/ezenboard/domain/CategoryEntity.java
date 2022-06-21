package ezenboard.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;

    // board와 연관관계
    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}
