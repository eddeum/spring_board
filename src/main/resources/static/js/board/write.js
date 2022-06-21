
function board_write(){
    let form = $("#boardform")[0];
    let formdata = new FormData(form);
        // *form 전송시 id값이 아닌 name값으로 값을 받아옴

    $.ajax({
        url : "/board/write",
        method : "POST",
        data : formdata,
        contentType : false,
        processData : false,
        success : function(result){
            if(result == true){
                alert("게시물등록 완료");
                location.href = "/board/list";
            }else{
                alert("게시물등록 실패");
            }
        } // success end
    }); // ajax end
}