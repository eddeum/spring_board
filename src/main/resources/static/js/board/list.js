
board_list(1);
category_list();

function board_list(cno){
    $.ajax({
        url : "/board/getboardlist",
        data : {"cno" : cno},
        method : "GET",
        success : function(boardlist){
            console.log(boardlist);
            let html =
                  '<tr>'+
                      '<th>번호</th> <th>제목</th> <th>작성자</th>'+
                  '</tr>';

            for(let i = 0; i < boardlist.length; i++){
                html +=
                       '<tr>'+
                          '<td>'+boardlist[i].bno+'</td>'+
                          '<td> <a href="/board/view/'+boardlist[i].bno+'">'+boardlist[i].btitle+' </a> </td>'+
                          '<td>'+boardlist[i].bwriter+'</td>'+
                      '</tr>';
            } // for end
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
                    '<button onclick="board_list('+categorylist[i].cno+')"> '+categorylist[i].cname+' </button>';
            } // for end
            $("#categorybox").html(html);
        } // success end
    }); // ajax end
}