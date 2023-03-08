
let privateKey;
window.addEventListener("load", function() {
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));
	web3.eth.getAccounts()
						  .then(accounts => console.log(accounts))
						  .catch(error => console.error(error));
	web3.eth.defaultAccount = $(".accountAddress").text();;
	ciMyNFT = new web3.eth.Contract(MYNFT_ABI,MYNFT_CA);
	ciHOUZZ = new web3.eth.Contract(HOUZZ_ABI,HOUZZ_CA);
	account = $(".accountAddress").text();
	console.log('$(".accountAddress").text() : ' +$(".accountAddress").text());
	getMyAuctions();
});

function getMyAuctions() {
	console.log("account :" + account);
	ciHOUZZ.methods.getHouzzsOf(account).call()
			.then(result => {
				console.log("result :" + result);
				auctionIds = result;
				select_option = "<option value='-1'>원하는 번호를 선택해주세요</option>";
				auctionIds.forEach((currentElement, index, array )=>{
					select_option += "<option>"+currentElement+"</option>";
				});
			$("#selectedAuction").html(select_option);
		});
}

function onFileSelected(event){
	const file = event.target.files[0];
	const reader = new FileReader();
	reader.onload = function(event){
		console.log(event.target.result);
		privateKey = event.target.result;
		console.log("privateKey :" + privateKey);
	};
	reader.readAsText(file);
}

function getAuctionById(target) {
	alert(target.value)
	ciHOUZZ.methods.getHouzzById(target.value).call({ from: account, gas: GAS_AMOUNT }).then(
		(result) => {
			if (privateKey === undefined) {
				alert("개인키를 선택해주세요.");
				location.reload();
			} else {
				console.log("result : " + result[2]);
				console.log("privateKey : " + privateKey);
				const privateKey1 = privateKey;
				const message = result[2];
				const messageHash = web3.utils.sha3(message);
				const signature = web3.eth.accounts.sign(messageHash, privateKey1);
				const signature1 = {
					messageHash: signature.messageHash,
					v: signature.v,
					r: signature.r,
					s: signature.s
				};
				const signerAddress = web3.eth.accounts.recover(signature1);
				if (signerAddress == web3.eth.accounts.recover(messageHash, result[6], result[7], result[8])) {
					$("#title").text(result[0]);
					$("#tokenId").text(result[3]);
					$("#price").text(web3.utils.fromWei(result[1], 'ether'));
					$("#owner").text(result[5]);
					$("#metadata").text(result[2]);
					$("#privateId").css("display", "none")
				} else {
					alert('서명이 유효하지 않습니다');
					location.reload();
				}
				
			}
		});
}

function onFileSelected1(event){
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = function(event) {
    console.log(event.target.result);
    privateKey = event.target.result;
  };
  reader.readAsText(file);
}

function finalizeHouzz(){
	if(!$("#toAddress").val()){
		alert("toAddress를 입력해 주세요");
		return;
	}
    console.log('$("#selectedAuction").val() : ' + $("#selectedAuction").val());
    console.log('$("#toAddress").val() : ' + $("#toAddress").val());
    
    
	const message = $("#metadata").text();

	// 메시지 해시 생성
	const messageHash = web3.utils.sha3(message);

	// 서명 생성
	const signature = web3.eth.accounts.sign(messageHash, privateKey);

    ciHOUZZ.methods.finalizeHouzz($("#selectedAuction").val(), $("#toAddress").val(), signature.v,signature.r,signature.s).send(
    	{from: account, gas: GAS_AMOUNT}).then((result) => {        
          console.log(result)
    })
}


function deleteHouzz() {
	console.log("$('#selectedAuction').val() ::::::::::   "+$("#selectedAuction").val());
	console.log("web3.eth.defaultAccount :::::"+web3.eth.defaultAccount);
	toAddress = "0x75f6d5D7E5D3C9b2FD312328537c6bE20765FDb6";
	ciHOUZZ.methods.deleteHouzz($("#selectedAuction").val(), toAddress)
					.call({ from: account, gas: GAS_AMOUNT })
					.then((result) => {
				console.log('deleteHouzzresult : ' + result);
				$.ajax({
					type: "post",
					url: "estateDelete",
					data: { "estateNum": $("#estateNum").val() },
					dataType: "text",
					success: function(result) {
						alert("삭제가 완료되었습니다.")
						location.href="/estate/estateList"
					},
					error: function() {
						alert("에러");
						return;
					}
				});
			}).catch((error) =>{
				console.log('error:' +error);
			});
}