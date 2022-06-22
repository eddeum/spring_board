package ezenboard.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    //
    List<BoardEntity> fingBybtitle();
    List<BoardEntity> fingBybcontent();
    List<BoardEntity> fingBybwriter();
}
