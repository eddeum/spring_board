
board_list(1, "", "");
category_list();

// 전역변수 선언
let current_cno = 1; // 카테고리 선택 변수(없을경우 1 = 자유게시판)

// 게시물출력[cno = 카테고리번호, key = 검색 키, keyword = 검색내용]
function board_list(cno, key, keyword){
    // 테스트
    alert("게시물출력 카테고리번호 : "+cno);
    alert("게시물출력 key : "+key);
    // key, keyword가 있을경우



    $.ajax({
        url : "/board/getboardlist",
        data : {"cno" : cno, "key" : key,"keyword" : keyword },
        method : "GET",
        success : function(boardlist){
            console.log(boardlist);
            let html =
                  '<tr>'+
                      '<th>번호</th> <th>제목</th> <th>작성자</th>'+
                  '</tr>';

            if(boardlist.length == 0){ // 검색 결과가 존재하지 않으면
                html +=
                    '<tr>'+
                        '<td colspan="3"> 검색 결과가 존재하지 않습니다. </td>'+
                    '</tr>';
            }else{
                for(let i = 0; i < boardlist.length; i++){
                    html +=
                           '<tr>'+
                              '<td>'+boardlist[i].bno+'</td>'+
                              '<td> <a href="/board/view/'+boardlist[i].bno+'">'+boardlist[i].btitle+' </a> </td>'+
                              '<td>'+boardlist[i].bwriter+'</td>'+
                          '</tr>';
                } // for end
            } // else end
            // 테이블에 html 넣기
            $("#boardtable").html(html);
        } // success end
    }); // ajax end
}


function category_list(){
    $.ajax({
        url : "/board/getcategory",
        success : function(categorylist){
            let html = "";
            for(let i = 0; i < categorylist.length; i++){
                html +=
                    '<button onclick="categorybtn('+categorylist[i].cno+')"> '+categorylist[i].cname+' </button>';
            } // for end
            $("#categorybox").html(html);
        } // success end
    }); // ajax end
}
// 카테고리 버튼 클릭했을 때
function categorybtn(cno){
    // 현재클릭한 카테고리번호를 카테고리번호에 대입
    this.current_cno = cno;
    // 테스트
    alert("카테고리버튼클릭 번호 : "+cno);
    // boardlist에 현재클릭한 카테고리번호 넘기기(key, keyword는 없음)
    board_list(cno, "", "");
}

// 검색버튼 클릭했을 때
function search(){
    let key = $("#key").val();
    let keyword = $("#keyword").val();

    // 키와 키워드 입력받음(현재페이지 카테고리번호 넘기기)
    board_list(this.current_cno, key, keyword);
}