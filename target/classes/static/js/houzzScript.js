//const GAS_AMOUNT=500000;


window.addEventListener("load", function() {
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));
	console.log(Web3);
	web3.eth.getAccounts()
						  .then(accounts => console.log(accounts))
						  .catch(error => console.log(error));
	web3.eth.defaultAccount = $("#accountAddress").val();
	console.log($("#accountAddress").val());
	
	getDefault();
});

let account;
function getDefault() {
	web3.eth.getAccounts(function(e, r) {
		account = $("#accountAddress").val();
		console.log("data.account : " + account);
	});
	contractInstance = new web3.eth.Contract(MYNFT_ABI,MYNFT_CA);
	auContractInstance = new web3.eth.Contract(HOUZZ_ABI,HOUZZ_CA);
	tokenId = _getRandomInt(123456789,999999999);
}

let privateKey;

function onFileSelected(event){
	const file = event.target.files[0];
	const reader = new FileReader();
	reader.onload = function(event){
		console.log(event.target.result);
		privateKey = event.target.result;
	};
	reader.readAdText(file);
}

var dataURI;
function UploadIMG(){
		var formData = new FormData();
		const imageInput = $("#contractPDF")[0];
		formData.append("contractPDF", imageInput.files[0]);
		const projectId = '2Jkqzx8dF6daV4V1fn5fBir0hCJ';
		const projectSecret = 'cea502289b0e4bd94874f1a402b2a11f';
		const authorization = "Basic " + btoa(projectId + ":" + projectSecret);
		var option = {
			type: "POST",
			dataType: 'json',
			url: "https://ipfs.infura.io:5001/api/v0/add", 
			headers: {authorization},
			data:formData,
			contentType: false,
			processData: false, 
			enctype: 'multipart/form-data', 
			success : function(response){
				
				dataURI = response.Hash;
				console.log(dataURI);
				$("#uploadedImg").attr("src","https://rhee.infura-ipfs.io/" + dataURI);
				$("#Data_URI").text(dataURI);
				execute();
			},
			error : function(res){
				console.log(res);
				alert("에러가 발생했습니다.");
			}
		};
		$.ajax(option);	
}
function execute(){
	console.log(dataURI);
	registerUniqueToken(contractInstance, account, tokenId, dataURI);
		
		///////////////////////////////
	transferToCA(contractInstance, account, tokenId, dataURI);
		
		//////////////////////////////
	createHouzz();
}
function createHouzz(){				
	console.log("dataURI : " + dataURI);
	const message = dataURI;
	const privateKey =  privateKey1;
	console.log("privateKey : " + privateKey);
	// 메시지 해시 생성
	const messageHash = web3.utils.sha3(message);
	console.log("messageHash : " + messageHash);
	// 서명 생성
	const signature = web3.eth.accounts.sign(messageHash, privateKey);

	// 결과 출력
	console.log("signature.messageHash : " + signature.messageHash);
	console.log("signature.v : " + signature.v);
	console.log("signature.r : " + signature.r);
	console.log("signature.s : " + signature.s);
	const price = web3.utils.toWei("1", 'ether');
	console.log("$('#estateName').val() :"+ $("#estateName").val());
	console.log("tokenId : " + tokenId);
	console.log("MYNFT_CA : " + MYNFT_CA);
	auContractInstance.methods.createHouzz(MYNFT_CA, tokenId, $("#estateName").val()
		, dataURI, price, signature.v, signature.r, signature.s)
		.send({ from: account, gas: GAS_AMOUNT })
		.then((transactionHash) => {
			if (transactionHash) {
				console.log(transactionHash);
			}

		});
}

function registerUniqueToken(contractInstance, account, tokenId, dataURI){
        contractInstance.methods.registerUniqueToken(account, tokenId, dataURI)
        .send({from: account, gas: GAS_AMOUNT}).then(
            function(result){
                console.log("result",result);
                if(result.transactionHash){
                    alert("Token registered...!");
                    $("#isRegistered").css("display", "");
                }
            });
}
    
function transferToCA(contractInstance,account,tokenId){
	contractInstance.methods.transferFrom(account,HOUZZ_CA, tokenId).send({from:account,gas:GAS_AMOUNT})
    .then(
		function(result){
			 console.log("result",result)    ;
		});
}

function _getRandomInt(min, max){
	return Math.floor(Math.random()*(max - min)) +min;
}

