/* 2022년 04.24 김시웅 
*/
$(function () {
    var moreNum = 2;
    $(".list li:nth-child(n + " + (moreNum + 1) + ")").addClass("is-hidden");
    $(".btn-more").on("click", function () {
      $(".list li.is-hidden").slice(0, moreNum).removeClass("is-hidden");
      if ($(".list li.is-hidden").length == 0) {
        $(".btn-more").fadeOut();
      }
    });


 //별점 
 $('.rating').each(function(){
  var rate = $(this).data("rate");
  $(this).find('.star').each(function(n) {
    if (n < rate) $(this).show();
  });
});

});
    