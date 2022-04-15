function buttonClick(bt) {
    alert(bt.value + "댓글이 등록되었습니다")
}

$(document).ready(function () {
    $("#save_btn").click(function () {
        $("#country-review").val();
        console.log("댓글내용을 가져옵니다")
    });
});