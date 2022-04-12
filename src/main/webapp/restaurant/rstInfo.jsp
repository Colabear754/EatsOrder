<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 페이지명: 매장상세페이지 작성일: 2022-04-04 작성자: 김시웅 
				 					jsp변환일: 2022-04-09 작성자:허우림-->
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>매장상세페이지</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="viewport"
        content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link href="css/store.final.css" rel="stylesheet" type="text/css">
    <link href="css/menupopup.css" rel="stylesheet" type="text/css">
    <script src="js/store.menudetail.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script src="./js/store.menudetail.js"></script>
</head>

<body>

    <div class="container" style="width: 100%;">
        <header>
            <div class="box">
            </div>
            <div class="search_addr">
                <div class="white_box"></div>
                <div class="search_input">
                    <input class="search1" type="text" placeholder="주소를 입력하세요.">
                    <div class="search_btn"><i class="fa-solid fa-magnifying-glass fa-lg"></i><b class="find_addr">주소
                            찾기</b></div>
                </div>
            </div>
            <div class="grid">
                <div class="category"><a href="#">전체보기</a></div>
                <div class="category"><a href="#">1인분 주문</a></div>
                <div class="category"><a href="#">프랜차이즈</a></div>
                <div class="category"><a href="#">치킨</a></div>
                <div class="category"><a href="#">피자/양식</a></div>
                <div class="category"><a href="#">중국집</a></div>
                <div class="category"><a href="#">한식</a></div>
                <div class="category"><a href="#">일식/돈까스</a></div>
                <div class="category"><a href="#">족발/보쌈</a></div>
                <div class="category"><a href="#">야식</a></div>
                <div class="category"><a href="#">분식</a></div>
                <div class="category"><a href="#">카페/디저트</a></div>
                <div class="category"><a href="#">편의점/마트</a></div>
            </div>
            <hr style="border:1px color= silver; margin-top: 0; margin-bottom: 0;" width="100%;">

        </header>
    </div>
    <div class="main">


        <div class="row" style="border:none">

            <div class="col-sm-8">
                <div class="row" style=" border-radius: 6px; border: 1px solid gray;">
                    <img class="img-responsive col-sm-3" src="../img/sand.jpg"
                        style="border-right: 1px dotted gray; height: 110px;">
                    <ul class="list-unstyled col-sm-9">
                        <li>매장명 ${restaurant.getRst_name}</li>
                        <li>별점 ${rstProcess.getRating}</li>
                        <li>최소주문금액 ${restaurant.getMin_order}</li>
                        <li>배달시간 ${restaurant.getHours}</li>
                    </ul>
                </div>

                <div class="restaurant-title row">
                    <ul class="nav nav-pills nav-justified">
                        <li class="nav-item"><a href="#tmenu" class="nav-link active" data-toggle="tab">메뉴</a></li>
                        <li class="nav-item"><a href="#tcleanreview" class="nav-link" data-toggle="tab">클린리뷰</a></li>
                        <li class="nav-item"><a href="#tinfo" class="nav-link" data-toggle="tab">정보</a></li>
                    </ul>

                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="tmenu">




                            <div class="container-fluid">
                                <div id="carouselExample" class="carousel slide" data-ride="carousel"
                                    data-interval="12000">
                                    <div class="carousel-inner row w-100 mx-auto flex-nowrap" role="listbox">
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=4" alt="slide 4" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=5" alt="slide 5" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=6" alt="slide 6" />
                                        </div>
                                        <div class="carousel-item col-md-3 active">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=7" alt="slide 7" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=8" alt="slide 8" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400/000/fff?text=1" alt="slide 1" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=2" alt="slide 2" />
                                        </div>
                                        <div class="carousel-item col-md-3">
                                            <img class="img-fluid mx-auto d-block"
                                                src="//via.placeholder.com/600x400?text=3" alt="slide 3" />
                                        </div>
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselExample" role="button"
                                        data-slide="prev">
                                        <i class="carousel-control-prev-icon"></i>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next text-faded" href="#carouselExample" role="button"
                                        data-slide="next">
                                        <i class="carousel-control-next-icon"></i>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>

                            <div class="dropdown-menu-box">

                                <div class="btn-group-vertical">
                                    <!-- 추가 버튼태그 -->
                                    <c:forEach var="categoryMenuList" items="${categoryMenuList}">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-expanded="false">
                                        ${categoryMenuList.category_name} <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                    <c:forEach var="detail" items="${categoryMenuList}">
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../restaurant-detail/img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>메뉴 이름${menuInfoList.menu_name }</li>
                                                        <li>메뉴 설명${menuInfoList.menu_info }</li>
                                                        <li id="amount">메뉴 가격${menuInfoList.price}</li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>
                                      </c:forEach>
                                      </ul>
                                      </c:forEach>
                                        
                                <div class="btn-group-vertical">
                                    <!-- 추가 버튼태그 -->
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-expanded="false">
                                        카테고리이름 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal">
                                                <div class="row">

                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마버거 오리지널</li>
                                                        <li>자마버거오리지널</li>
                                                        <li>11000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>

                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>

                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>

                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div class="btn-group-vertical">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-expanded="false">
                                        사이드메뉴<span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2"></a>
                                            <div class="row">
                                                <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                <ul class="list-unstyled col-sm-9">
                                                    <li>자마버거오리지널</li>
                                                    <li>11000원</li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a data-toggle="modal" data-target="#myModal2">
                                                <div class="row">
                                                    <img class="img-responsive col-sm-3" src="../img/sand.jpg">
                                                    <ul class="list-unstyled col-sm-9">
                                                        <li>자마 치즈버거</li>
                                                        <li>치즈가 폭포수처럼 흘러내려요</li>
                                                        <li>13000원 </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                            </div>


                        </div>
                        <div class="tab-pane fade" id="tcleanreview">



                            <div class="row" style="padding: 0; margin: 0; ">

                                <div class="col-sm-12" style="padding:0;">
                                    <div class="row" style=" padding: 15px; border: 1px solid gray;">

                                        <div class="restaurant-star-point col-sm-6">
                                            <strong>
                                                <h3>4.8</h3>
                                            </strong>
                                            <div class="rating rating-large">
                                                <span class="star voted" rel="1">★</span>
                                                <span class="star voted" rel="2">★</span>
                                                <span class="star voted" rel="3">★</span>
                                                <span class="star voted" rel="4">★</span>
                                                <span class="star" rel="5"></span>

                                            </div>
                                        </div> <!-- restaurant-star-point -->
                                        <div class="restaurant-star-point col-sm-6">
                                            <ul>
                                                <li>맛
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                </li>
                                                <li>양
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                </li>
                                                <li>배달
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                    <span>★</span>
                                                </li>

                                            </ul>
                                        </div><!-- restaurant-star-point-->
                                    </div> <!-- row-->
                                    <div class="row" style="height: 50px; border: 1px solid gray;">
                                        <div class="review-count" style="top:10px; width: 100%; margin-top: 10px;">
                                            <div class="review-c">
                                                리뷰
                                                <strong class="ng-binding">2000</strong>
                                                개. 사장님댓글
                                                <strong class="ng-binding">900</strong>
                                                개
                                                <div class="opt-btn"
                                                    style="float: right; position:relative; right: 65px;">
                                                    <span class="reviewbar" style="float: left;">
                                                        사진리뷰만</span>
                                                    <input type="checkbox" id="switch">
                                                    <label for="switch" class="switch_label">
                                                        <span class="onf_btn"></span>
                                                    </label>

                                                </div>
                                            </div>
                                        </div> <!-- review-count-->
                                    </div> <!-- row-->

                                    <div class="row" style="display: block;">
                                        <ul id="review" class="list-group review-list">
                                            <li class="list-group-item star-point" style="display: block;">
                                                <div>
                                                    <span class="riview-id">ki**님</span>
                                                    <span class="review-time">9시간전</span>
                                                    <a href class="btn-report"
                                                        style="position: absolute; right: 16px; color:#999">신고</a>
                                                </div>
                                                <div>
                                                    <div class="star-point" style="display:block;">
                                                        <span class="total">
                                                            <span class="full ">★</span>
                                                        </span>
                                                        <span class="total">
                                                            <span class="full ">★</span>
                                                        </span>
                                                        <span class="total">
                                                            <span class="full ">★</span>
                                                        </span>
                                                        <span class="total">
                                                            <span class="full ">★</span>
                                                        </span>
                                                        <span class="total">
                                                            <span class="full ">★</span>
                                                        </span>
                                                        <span class="category" style="border-left: 1px solid gray;">
                                                            <span class="category-tit"
                                                                style="margin-left: 10px;">맛</span>
                                                            <span class="full">★</span>
                                                            <span class="points ">5</span>

                                                            <span class="category-tit">양</span>
                                                            <span class="full">★</span>
                                                            <span class="points">5</span>

                                                            <span class="category-tit">배달</span>
                                                            <span class="full">★</span>
                                                            <span class="points ">5</span>
                                                        </span>
                                                    </div>
                                                </div>

                                                <table class="info-images" style="width:100%">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <div class="reimg">
                                                                    <img src="../img/sand.jpg"
                                                                        style="max-width: 100%; width:100%; height:300px; ">
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>

                                                <div class="order-items" style="width: 100%;">자마버거 오리지날</div>
                                                <p class="ng-binding">맛있게 잘먹었습니다</p>

                                            <li class="list-group-item btn-more"
                                                style="display: block; padding: 10px; text-align: center; color: #FC6E4D; border-top: 1px solid gray;">
                                                <a ng-cilck="get_next_reviews()">
                                                    <span><strong>더 보기 </strong>
                                                        <i class="arr-down"></i>
                                                    </span>
                                                </a>
                                            </li>

                                            </li>
                                        </ul>


                                    </div> <!-- row-->
                                </div> <!-- col-sm-8-->
                            </div> <!-- row-->


                        </div>
                        <div class="tab-pane fade" id="tinfo">
                            <div class="info-d" style="border: 1px solid grey; width:100%">
                                <div id="info" class="info-list">
                                    <div class="info-item">
                                        <div class="info-item-title">사장님알림</div>
                                        <table class="info-images" style="margin: 0; width: 100%; ">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <div>
                                                            <img src="../img/sand.jpg"
                                                                style="width:100%; height:280px; ">
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="info-item" style="display: block; border-bottom: 1px solid gray;">
                                    <div class="info-item-title">업체정보</div>
                                    <p>
                                        <i>영업시간</i>
                                        <span>00:00~23:59</span>
                                    </p>
                                    <p>
                                        <i>전화번호</i>
                                        <span>050352902988(요기요 제공 번호)</span>
                                    </p>
                                    <p>
                                        <i>주소</i>
                                        <span>서울시 서대문구 대현동 110-53 1층</span>
                                    </p>
                                </div>

                                <div class="info-item" style="display: block; border-bottom: 1px solid gray;">
                                    <div class="info-item-title">결제정보</div>
                                    <p>
                                        <i>최소주문금액</i>
                                        <span>15,000원</span>
                                    </p>
                                    <p>
                                        <i>결제수단</i>
                                        <span>신용카드 , 현금 , 요기서결제</span>
                                    </p>

                                </div>

                                <div class="info-item" style="display: block; border-bottom: 1px solid gray;">
                                    <div class="info-item-title">사업자정보</div>
                                    <p>
                                        <i>상호명</i>
                                        <span>자마버거</span>
                                    </p>
                                    <p>
                                        <i>사업자등록번호</i>
                                        <span>672-86-57821</span>
                                    </p>
                                </div>
                                <div class="info-item">
                                    <div class="info-item-title">원산지정보</div>
                                    <p></p>
                                    <pre>
소불고기(호주산,미국산),핫토핑:닭고기(미국,네덜란드등),비프토핑(국산,호주산),
페로니(국산),베이컨:돼지고기(미국,아일랜드,스페인등),비엔나:돼지고기(국산,미국등등),
차돌박이(미국산,호주산),김치(중국산)쌀(국내산)닭발(덴마크산)닭(국내산).순살(국내산),
순살(브라질산)닭똥집(국내산)허브치킨(국내산)카나디안햄(미국,벨기에,스페인등)
베이컨:돼지고기(미국,아일랜드,스페인등),비엔나:돼지고기(국산,미국등등)차돌박이(미국산,호주산)
김치(중국산)고추가루(케냐산)토시살(미국산:프라임등급.냉장)                                                    
                                                </pre>
                                </div>
                            </div>





                        </div>

                    </div>
                </div>
            </div><!-- restaurant-title row-->



            <div class="col-sm-4 ">
                <div class="sticky">
                    <ul class="orderlist ">
                        <li class="aorder">주문표</li>
                        <li class="border"></li>
                        <li class="corder">배달금액 </li>
                        <li class="dorder" id="total_amt">0</li>
                    </ul>
                    <button type="button" class="btn btn-warning" onclick="buttonClick(this)" value="확인">주문하기</button>
                    </li>
                </div>
            </div>

        </div>
    </div> <!-- ./row -->

    <footer>
        <div class="all_footer">
            <div class="hr1">
                <hr>
            </div>
            <div class="footer_list">
                <ul>
                    <div class="footer_menu">
                        <li><a href="#">이벤트</a></li>
                        <li><a href="#">공지사항</a></li>
                        <li><a href="#">자주묻는 질문</a></li>
                        <li><a href="#">이용약관</a></li>
                        <li><a href="#">개인정보처리방침</a></li>
                        <li><a href="#">사장님 로그인</a></li>
                        <li><a href="#">관리자 페이지</a></li>
                    </div>
                    <div class="footer_sns">
                        <li><a href="#"><i class="fa-brands fa-instagram"></i></a></li>
                        <li><a href="#"><i class="fa-brands fa-facebook-square"></i></a></li>
                        <li><a href="#"><i class="fa-brands fa-twitter"></i></a></li>
                    </div>
                </ul>
            </div>
            <hr>
            <div class="fooer_script">
                <h3>회사 이름</h3>
                <p>
                    서울시 강남구 역삼로123 잇츠오더타워2 3층 | 대표자 : 홍길동 |
                    사업자등록번호:123-45-67891 | 사업자정보확인통신판매업신고:제 2022-강남역삼-1234호
                    개인정보담당자 : privacy@itsorder.com
                </p>
                <p>
                    제휴문의 : partnership@itsorder.com |
                    고객만족센터 : support@itsorder.com | 호스팅 제공자: 잇츠12 주식회사
                </p>
            </div>
            <div class="cs">
                <h1>고객만족센터 123-1234(유료)</h1>
                <p>24시간 연중무휴</p>
            </div>
            <hr>
            <div class="fooer_script2">
                <p>(주)잇츠오더는 통신판매중개자이며 통신판매의 당사자가 아닙니다. 따라서 상품/ 거래정보 및 거래와 관련하여
                    잇츠오더에 등록된 판매자의 고의 또는 과실로 소비자에게 발생하는 손해에 대해
                </p>
                <p>
                    (주)잇츠오더는 책임을 지지 않습니다. 상품 및 거래에 관하여 보다 정확한 정보는 해당 판매자에게 직접 확인하여 주시기 바랍니다.
                    Copyright ITSORDER. All Rights Reserved.
                </p>
            </div>
        </div>
    </footer>









    <div class="footer"></div>





    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">메뉴상세</h4>
                    <button type="button" class="close" data-dismiss="modal">×</button>
                </div>

                <div class="modal-body">
                    <div class="detail-image">
                        <img src="../img/sand.jpg">
                    </div>
                    <!--detail-image-->
                    <div class="detail-text" id="menu">자마버거오리지널</div>
                    <div class="detail-price">
                        <strong>가격</strong>
                        <div class="price" id="amt">11000</div>
                    </div>
                    <!--detail-price-->
                    <div class="menu-list-wrap">
                        <div class="menu-list-tit">
                            <strong>추가메뉴</strong>
                        </div>
                        <!--menu-list-tit-->
                        <div class="menu-list">
                            <input type="hidden" id="opts" value="0" />
                            <lable class="menu-container">
                                <div>
                                    <label><input type="checkbox" id="m1" name="check_1"
                                            value="13000" />&nbsp;자마치즈버거</label>
                                    <span class="price">13000</span>

                                </div>
                                <div>
                                    <label><input type="checkbox" id="m2" name="check_1"
                                            value="1000" />&nbsp;치즈추가</label>
                                    <span class="price">1000</span>
                                </div>
                            </lable>
                        </div>
                        <!--menu-list-->
                    </div><!-- menu-list-wrap-->
                    <div class="quantity-control">
                        <strong>수량</strong>
                        <input type="button" id="plus" value='+' style="float: right; width: 25px;" />
                        <div id='result' style="float: right; padding-left: 10px; padding-right: 10px;">1</div>
                        <input type="button" id="minus" value='-' style="float: right; width: 25px;" />

                    </div>
                    <!--quantity-control-->
                    <div class="detail-price">
                        <strong>총주문금액</strong>
                        <div class="price" id="totAmt">11000</div>
                    </div>
                    <!--detail-price-->
                </div>
                <!--modal-body-->
                <div class="modal-footer">
                    <div class="detail btn-wrap">
                        <form action="" method="post"></form>
                        <button class="btn-add-cart" onclick="orderList(this)" value="주문표">주문표에추가</button>
                        <button class="btn-order" onclick="buttonClick(this)" value="확인">주문하기</button>
                        </from>
                        <p id="result"></p>
                    </div><!-- detail btn-wrap -->

                </div><!-- modal-footer -->
            </div> <!-- content-->
        </div> <!-- modal-dialog-->
    </div><!-- moda fade-->

</body>

</html>
