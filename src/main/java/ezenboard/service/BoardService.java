package ezenboard.service;

import ezenboard.domain.BoardEntity;
import ezenboard.domain.BoardRepository;
import ezenboard.domain.CategoryEntity;
import ezenboard.domain.CategoryRepository;
import ezenboard.dto.BoardDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired // 자동 빈 생성[자동 생성자 이용한 객체에 메모리 할당]
    private BoardRepository boardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. C
    @Transactional
    public boolean board_save(BoardDto boardDto){
        // 카테고리 유무 스위치
        boolean catesw = false;
        int cno = 0;
        // 카테고리 찾기
        List<CategoryEntity> CategoryList = categoryRepository.findAll();
        for(CategoryEntity entity : CategoryList){
            if(entity.getCname().equals(boardDto.getCategory() )){ // 카테고리 이름이 dto에 이미 존재하면
                catesw = true;
                cno = entity.getCno();
            } // if end
        } // for end

        CategoryEntity categoryEntity = null;
        if(!catesw){ // 카테고리가 없을경우
            // 카테고리 생성
            categoryEntity = CategoryEntity.builder()
                    .cname(boardDto.getCategory() )
                    .build();
            categoryRepository.save(categoryEntity);
        }else{ // 카테고리가 있을경우
            categoryEntity = categoryRepository.findById(cno).get(); ////?????????????????????????????????
        } // else end


        // 1. dto -> entity 변환메소드
        BoardEntity boardEntity = boardDto.toentity();
        // 2. 저장
        boardRepository.save(boardEntity);
        // 카테고리 엔티티에 게시물 연결
        categoryEntity.getBoardEntityList().add(boardEntity);
        // board 엔티티에 카테고리 연결
        boardEntity.setCategoryEntity( categoryEntity );

            return true;

    } // 게시물 등록 end

    // 2-1. R(전체게시물 조회)
    public JSONArray getboardlist(int cno){
        JSONArray jsonArray = new JSONArray();
        List<BoardEntity> boardEntitylist = boardRepository.findAll();
        // 엔티티 -> json 변환
        for(BoardEntity entity : boardEntitylist){
            if(entity.getCategoryEntity().getCno() == cno){
                JSONObject object = new JSONObject();
                object.put("bno", entity.getBno() );
                object.put("btitle", entity.getBtitle() );
                object.put("bwriter", entity.getBwriter() );
                jsonArray.put(object);
            } // if end
        } // for end
        return jsonArray;
    } // 전체게시물 조회 end

    // 2-2. R(개별게시물 조회)
    public JSONObject getboard(int bno){
        // 1. 해당 게시물번의 board엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        BoardEntity boardEntity =optional.get();
        // 2. 해당 엔티티 -> json 객체 변환
        JSONObject object = new JSONObject();
        object.put("bno", boardEntity.getBno() );
        object.put("btitle", boardEntity.getBtitle() );
        object.put("bcontent", boardEntity.getBcontent() );
        object.put("bwriter", boardEntity.getBwriter() );
        return object;
    } // 개별게시물 조회 end

    // 3. U
    @Transactional
    public boolean update(BoardDto boardDto){
        // 1. 해당 게시물번의 board엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno() );
        BoardEntity boardEntity = optional.get();
        // 2. 제목/내용 수정하기
        boardEntity.setBtitle(boardDto.getBtitle() );
        boardEntity.setBcontent(boardDto.getBcontent() );
        return true;
    } // 게시물수정 end

    // 4. D
    @Transactional
    public boolean delete(int bno){
        // 1. 해당 게시물번의 board엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        BoardEntity boardEntity =optional.get();
        boardRepository.delete(boardEntity);
        return true;
    } // 게시물삭제 end

    // 카테고리 호출 메소드
    public JSONArray getcategory(){
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for(CategoryEntity entity : categoryEntityList){
            JSONObject object = new JSONObject();
            object.put("cno", entity.getCno() );
            object.put("cname", entity.getCname() );
            jsonArray.put(object);
        } // for end
        return jsonArray;
    } // 카테고리호출 end



}
