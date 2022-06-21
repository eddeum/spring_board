package ezenboard.dto;

import ezenboard.domain.BoardEntity;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BoardDto {
    // 필드
    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String bpassword;

    private String category;    // 카테고리

    // dto -> entity 변환
    public BoardEntity toentity(){
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bwriter(this.bwriter)
                .bpassword(this.bpassword)
                .build();
    }
}
