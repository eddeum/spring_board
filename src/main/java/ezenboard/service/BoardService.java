package ezenboard.service;

import ezenboard.domain.BoardEntity;
import ezenboard.domain.BoardRepository;
import ezenboard.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public boolean board_save(BoardDto boardDto){
        // 1. dto -> entity 변환메소드
        BoardEntity boardEntity = boardDto.toentity();
        // 2. 저장
        boardRepository.save(boardEntity);
        if(boardEntity.getBno() < 1){
            return false;
        }else{
            return true;
        } // else end

    } // 게시물 저장 end
}
