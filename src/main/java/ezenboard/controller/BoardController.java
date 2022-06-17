package ezenboard.controller;

import ezenboard.dto.BoardDto;
import ezenboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

    // board 서비스 호출
    @Autowired
    BoardService boardService;

    // 게시물등록 페이지 이동매핑
    @GetMapping("/write")
    public String write(){return "/board/write";}

    // 게시물등록 처리 매핑
    @PostMapping("/write")
    @ResponseBody
    public boolean board_save(BoardDto boardDto){
        System.out.println(boardDto.getBno()); // 확인
        boardService.board_save(boardDto);
        return true;
    }

    // 모든게시물출력 페이지 이동매핑
    @GetMapping("/list")
    public String list(){return "/board/list";}
    
    // 모든게시물출력 처리 매핑

}
