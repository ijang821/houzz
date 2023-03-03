<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.inicis.std.util.ParseUtil"%>
<%@ page import="com.inicis.std.util.SignatureUtil"%>
<%@ page import="com.inicis.std.util.HttpUtil"%>
<%@ page import="java.util.*"%>
<% 

	Map<String, String> resultMap = new HashMap<String, String>();

	try{

		//#############################
		// 인증결과 파라미터 일괄 수신
		//#############################
		request.setCharacterEncoding("UTF-8");

		Map<String,String> paramMap = new Hashtable<String,String>();

		Enumeration elems = request.getParameterNames();

		String temp = "";

		while(elems.hasMoreElements())
		{
			temp = (String) elems.nextElement();
			paramMap.put(temp, request.getParameter(temp));
		}
		
		System.out.println("paramMap : "+ paramMap.toString());
		
		
		if("0000".equals(paramMap.get("resultCode"))){

			System.out.println("####인증성공/승인요청####");

			//############################################
			// 1.전문 필드 값 설정(***가맹점 개발수정***)
			//############################################
			
			String mid 		= paramMap.get("mid");
			String timestamp= SignatureUtil.getTimestamp();
			String charset 	= "UTF-8";
			String format 	= "JSON";
			String authToken= paramMap.get("authToken");
			String authUrl	= paramMap.get("authUrl");
			String netCancel= paramMap.get("netCancelUrl");	
			String merchantData = paramMap.get("merchantData");
			
			//#####################
			// 2.signature 생성
			//#####################
			Map<String, String> signParam = new HashMap<String, String>();

			signParam.put("authToken",	authToken);		// 필수
			signParam.put("timestamp",	timestamp);		// 필수

			// signature 데이터 생성 (모듈에서 자동으로 signParam을 알파벳 순으로 정렬후 NVP 방식으로 나열해 hash)
			String signature = SignatureUtil.makeSignature(signParam);


			//#####################
			// 3.API 요청 전문 생성
			//#####################
			Map<String, String> authMap = new Hashtable<String, String>();

			authMap.put("mid"			,mid);			// 필수
			authMap.put("authToken"		,authToken);	// 필수
			authMap.put("signature"		,signature);	// 필수
			authMap.put("timestamp"		,timestamp);	// 필수
			authMap.put("charset"		,charset);		// default=UTF-8
			authMap.put("format"		,format);	    // default=XML


			HttpUtil httpUtil = new HttpUtil();

			try{
				//#####################
				// 4.API 통신 시작
				//#####################

				String authResultString = "";

				authResultString = httpUtil.processHTTP(authMap, authUrl);
				
				//############################################################
				//5.API 통신결과 처리(***가맹점 개발수정***)
				//############################################################
				
				String test = authResultString.replace(",", "&").replace(":", "=").replace("\"", "").replace(" ","").replace("\n", "").replace("}", "").replace("{", "");
				
							
				resultMap = ParseUtil.parseStringToMap(test); //문자열을 MAP형식으로 파싱
				
				
			  // 수신결과를 파싱후 resultCode가 "0000"이면 승인성공 이외 실패

			  //throw new Exception("강제 Exception");
			} catch (Exception ex) {

				//####################################
				// 실패시 처리(***가맹점 개발수정***)
				//####################################

				//---- db 저장 실패시 등 예외처리----//
				System.out.println(ex);

				//#####################
				// 망취소 API
				//#####################
				String netcancelResultString = httpUtil.processHTTP(authMap, netCancel);	// 망취소 요청 API url(고정, 임의 세팅 금지)

				out.println("## 망취소 API 결과 ##");

				// 취소 결과 확인
				out.println("<p>"+netcancelResultString.replaceAll("<", "&lt;").replaceAll(">", "&gt;")+"</p>");
			}

		}else{
			
			resultMap.put("resultCode", paramMap.get("resultCode"));
			resultMap.put("resultMsg", paramMap.get("resultMsg"));
		}

	}catch(Exception e){

		System.out.println(e);
	}
%>
<!DOCTYPE html>
<html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport"
            content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <title>KG이니시스 결제샘플</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		
		<script language="javascript" type="text/javascript" src="https://stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
        <script type="text/javascript">
            function paybtn() {
                INIStdPay.pay('SendPayForm_id');
            }
        </script>
    </head>

   
		
  

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
    <h1 class="w3-xxxlarge w3-text-green"><b>PAYMENT END TO HOUZZ</b></h1>
    <hr style="width:50px;border:5px solid green" class="w3-round">
    
  </div>
  

 <body class="wrap">

        <!-- 본문 -->
        <main class="col-8 cont" id="bill-01">
            <!-- 페이지타이틀 -->
            <section class="mb-5">
                <div class="tit">
                    <h2>&nbsp;&nbsp;&nbsp;결제완료</h2>
                    
                </div>
            </section>
            <!-- //페이지타이틀 -->


            <!-- 카드CONTENTS -->
            <section class="menu_cont mb-5">
                <div class="card">
                   

                    <!-- 유의사항 -->
                    <div class="card_desc">
                        
                        <ul>
                            <li>결제가 정상적으로 처리되었습니다.</li>
						
                        </ul>
                    </div>
                    <!-- //유의사항 -->


                    <form name="" id="result" method="post" class="mt-5">
                    <div class="row g-3 justify-content-between" style="--bs-gutter-x:0rem;">
 
                        <label class="col-10 col-sm-2 gap-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("resultCode") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("resultMsg") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("tid") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("MOID") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("TotPrice") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("goodName") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("applDate") %>
                        </label>
						
						<label class="col-10 col-sm-2 input param" style="border:none;"></label>
                        <label class="col-10 col-sm-9 reinput">
                            <%= resultMap.get("applTime") %>
                        </label>
 
                    </div>
                </form>
				
				<button onclick="location.href='/'" class="btn_solid_pri col-6 mx-auto btn_lg" style="margin-top:50px">돌아가기</button>
					
                </div>
            </section>
			
        </main>


<!-- End page content -->
</div>

<!-- W3.CSS Container -->
<div class="w3-light-grey w3-container w3-padding-32" style="margin-top:75px;padding-right:58px"><p class="w3-right">Powered by <a href="https://www.w3schools.com/w3css/default.asp" title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a></p></div>



</body>
</html>

