package ezenboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    private String btitle;
    @Column(columnDefinition = "TEXT")
    private String bcontent;
    private String bwriter;
    private String bpassword;
}
