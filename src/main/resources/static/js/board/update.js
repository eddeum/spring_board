

function board_update(){
alert("테스트");
    let form = $("#updateform")[0];
    let formdata = new FormData(form);

    $.ajax({
        url : "/board/update",
        data : formdata,
        method : "PUT",
        processData : false,
        contentType : false,
        success : function(result){
            if(result){
                alert("수정 완료되었습니다.");
                location.href = "/board/list";
            }else{
                alert("수정 실패");
            }
        } // success end
    }); // ajax end
}