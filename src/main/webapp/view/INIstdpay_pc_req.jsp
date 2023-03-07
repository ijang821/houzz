<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@page import="java.util.*"%>
<%

	String mid					= "INIpayTest";		                    // 상점아이디					
	String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
	
	String mKey = SignatureUtil.hash(signKey, "SHA-256");

	String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
	String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
	String price				= "100";								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


	Map<String, String> signParam = new HashMap<String, String>();

	signParam.put("oid", orderNumber);
	signParam.put("price", price);
	signParam.put("timestamp", timestamp);

	String signature = SignatureUtil.makeSignature(signParam);
	
%>
<!DOCTYPE html>
<html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport"
            content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <title>KG이니시스 결제샘플</title>
        <link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		
		<!--테스트 JS--><script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
		<!--운영 JS> <script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script> -->
        <script type="text/javascript">
            function paybtn() {
                INIStdPay.pay('SendPayForm_id');
            }
        </script>
    </head>

    <body class="wrap">

      
		
    </body>
</html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>regist form</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
body {font-size:16px;}
.w3-half img{margin-bottom:-6px;margin-top:16px;opacity:0.8;cursor:pointer}
.w3-half img:hover{opacity:1}
@font-face {
    font-family: 'BMJUA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
a{
	text-decoration-line : none;
}
</style>
<script>
// Script to open and close sidebar
function w3_open() {
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("myOverlay").style.display = "none";
}

</script>
</head>
<body>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-green w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container">
    <h3 class="w3-padding-64"><b><a href="/">HOUZZ</a></b></h3>
  </div>
  <div class="w3-bar-block">
    
  </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-green w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-green w3-margin-right" onclick="w3_open()">☰</a>
  <span>Company Name</span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:340px;margin-right:40px">

  <!-- Header -->
  <div class="w3-container" style="margin-top:80px" id="showcase">
    <h1 class="w3-jumbo"><b>HOUZZ</b></h1>
    <h1 class="w3-xxxlarge w3-text-green"><b>PAYMENT TO HOUZZ</b></h1>
    <hr style="width:50px;border:5px solid green" class="w3-round">
    
  </div>
  


  <!-- 본문 -->
        <main class="col-8 cont" id="bill-01">
      
            <!-- 카드CONTENTS -->
            <section class="menu_cont mb-5">
                <div class="card">
                    <div class="card_tit">
                     <h2>&nbsp;&nbsp;&nbsp;PC 일반결제</h2>
                    </div>

                    <!-- 유의사항 -->
                    <div class="card_desc">
                        <h4></h4>
                        <ul>
                            <li>결제 요청을 하시면 다음 페이지로 이동합니다</li>
							
                        </ul>
                    </div>
                    <!-- //유의사항 -->
                    <button onclick="paybtn()" class="btn_solid_pri col-6 mx-auto btn_lg" style="margin-top:50px">결제 요청</button>

                    <form name="" id="SendPayForm_id" method="post" class="mt-5">
                        <div class="row g-3 justify-content-between" style="--bs-gutter-x:0rem;">
				    
                            <!--label class="col-10 col-sm-2 gap-2 input param" style="border:none;">version</label>
                            <label class="col-10 col-sm-9 input"-->
                                <input type="hidden" name="version" value="1.0">
                            <!--/label-->
				    
                            <label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="gopaymethod" value="Card:Directbank:vbank">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="mid" value="<%=mid%>">
                            </label> <br/>
				    
                            <label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="oid" value="<%=orderNumber%>">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="price" value="<%=price%>">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="timestamp" value="<%=timestamp%>">
                            </label> <br/>
				    
				    
                            <input type="hidden" name="signature" value="<%=signature%>">
				    		<input type="hidden" name="mKey" value="<%=mKey%>">
                            <input type="hidden" name="currency" value="WON">
				    		
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="goodname" value="테스트상품">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="buyername" value="테스터">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="buyertel" value="01012345678">
                            </label> <br/>
				    		
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="buyeremail" value="test@test.com">
                            </label> <br/>
				    		
				    		<input type="hidden" name="returnUrl" value="http://localhost:8080/INIstdpay_pc_return">
                            <input type="hidden" name="closeUrl" value="http://localhost:8080/close">
                            
				    		<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                            <label class="col-10 col-sm-9 input">
                                <input type="hidden" name="acceptmethod" value="HPP(1):below1000:va_receipt">
                            </label> <br/>
							
                        </div>
                    </form>		
                </div>
            </section>
			
        </main>


<!-- End page content -->
</div>

<!-- W3.CSS Container -->
<div class="w3-light-grey w3-container w3-padding-32" style="margin-top:75px;padding-right:58px"><p class="w3-right">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></p></div>



</body>
</html>