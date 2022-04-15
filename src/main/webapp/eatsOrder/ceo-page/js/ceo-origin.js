//2022.04.04 김시웅
//버튼클릭시 팝업
function buttonClick(bt) {
    alert(bt.value + "원산지 등록되었습니다")

}
//글자수 카운팅 
$(document).ready(function () {
    $('#country-orign').on('keyup', function () {
        $('#test_cnt').html("(" + $(this).val().length + " / 5000)");

        if ($(this).val().length > 5000) {
            $(this).val($(this).val().substring(0, 5000));
            $('#test_cnt').html("(5000 / 5000)");
        }
    });
});

$(document).ready(function () {
    $("#save_btn").click(function () {
        $("#country-orign").val();
        console.log("댓글내용을 가져옵니다")
    });
});