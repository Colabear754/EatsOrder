function buttonClick(bt) {
    alert(bt.value + "적용되었습니다")
}

$(function () {
    $("#salesop").change(function () {
        var v = $("#salesop").val();
        alert("셀렉트값 : " + v);

    });

});