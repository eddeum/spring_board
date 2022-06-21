
board_get();

function board_get(){
    $.ajax({
        url : "/board/getboard",
        success : function(board){
            let html =
                    '<div>게시물 번호 : '+board.bno+' </div>'+
                    '<div>게시물 제목 : '+board.btitle+' </div>'+
                    '<div>게시물 내용 : '+board.bcontent+' </div>'+
                    '<div>게시물 작성자 : '+board.bwriter+' </div>'+
                    '<button onclick="board_delete('+board.bno+')">삭제하기</button>';
            $("#boarddiv").html(html);
        } // success end
    }); // ajax end
}


function board_delete(bno){
    $.ajax({
        url : "/board/delete",
        data : {"bno" : bno},
        method : "DELETE",
        success : function(result){
            if(result == true){
                alert("게시물 삭제 완료");
                location.href = "/board/list";
            }else{
                alert("게시물 삭제 실패");
            }
        } // success end
    }); // ajax end
}