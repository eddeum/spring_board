package ezenboard.controller;

import ezenboard.dto.BoardDto;
import ezenboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
public class BoardController {

    // board 서비스 호출
    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;

    /////////////////////////////////////// 페이지 이동 매핑 ///////////////////////////////////////////

    // 게시물등록 페이지 이동매핑
    @GetMapping("/write")
    public String write(){return "/board/write";}

    // 모든게시물출력 페이지 이동매핑
    @GetMapping("/list")
    public String list(){return "/board/list";}

    // 개별게시물출력 페이지 이동매핑
    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno){
        // 내가 보고있는 게시물번호 저장
        request.getSession().setAttribute("bno", bno);
        return "/board/view";
    }
    
    // 수정페이지 이동매핑
    @GetMapping("/update")
    public String update(){return "/board/update";}


    /////////////////////////////////////// 기능 처리 매핑 ///////////////////////////////////////////
    // 게시물등록 처리 매핑
    @PostMapping("/write")
    @ResponseBody
    public boolean board_save(BoardDto boardDto){
        return boardService.board_save(boardDto);
    }

    // 모든게시물출력 처리 매핑
    @GetMapping("/getboardlist")
    public void getboardlist(HttpServletResponse response, @RequestParam("cno") int cno){
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(boardService.getboardlist(cno) );
        }catch(Exception e){System.out.println("board컨트롤러 boardlist 오류"+e);}
    }

    // 개별게시물출력 처리 매핑
    @GetMapping("/getboard")
    public void getboard(HttpServletResponse response){
        int bno = (Integer)request.getSession().getAttribute("bno");
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardService.getboard(bno) );
        }catch(Exception e){System.out.println("board컨트롤러 getboard 오류"+e);}
    }

    // 게시물수정 처리 매핑
    @PutMapping("/update")
    @ResponseBody
    public boolean update(BoardDto boardDto){
        int bno = (Integer)request.getSession().getAttribute("bno");
        boardDto.setBno(bno);
        return boardService.update(boardDto);
    }

    // 게시물삭제 처리 매핑
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestParam("bno") int bno){
        return boardService.delete(bno);
    }

    // 카테고리 출력 매핑
    @GetMapping("/getcategory")
    public void getcategory(HttpServletResponse response){
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(boardService.getcategory() );
        }catch(Exception e){System.out.println("board컨트롤러 getcategory 오류"+e);}
    }
}
