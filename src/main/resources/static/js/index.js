window.addEventListener("load", function() {
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));
	//encrypt();
});


function encrypt() {
	const message = "23a87964bba8761734409bf8e2ff9cf1a6f57bd96776e3b9e286fe2ca340cabc";
    const password = "hello blockchain";
    const salt = CryptoJS.lib.WordArray.random(128 / 8);
    const key = CryptoJS.PBKDF2(password, salt, { keySize: 256 / 32, iterations: 1000 });
    const iv = CryptoJS.lib.WordArray.random(128 / 8);
    const ciphertext = CryptoJS.AES.encrypt(message, key, { iv: iv });
    const encrypted = salt.toString() + iv.toString() + ciphertext.toString();
    document.write(encrypted);
}
// 복호화
function decrypt(message,pass) {
 	const encrypted = message;
    const password = pass;
    const salt = CryptoJS.enc.Hex.parse(encrypted.substr(0, 32));
    const iv = CryptoJS.enc.Hex.parse(encrypted.substr(32, 32));
    const ciphertext = encrypted.substring(64);
    const key = CryptoJS.PBKDF2(password, salt, { keySize: 256 / 32, iterations: 1000 });
    const decrypted = CryptoJS.AES.decrypt({ ciphertext: CryptoJS.enc.Base64.parse(ciphertext) }, key, { iv: iv }).toString(CryptoJS.enc.Utf8);
    //$('#decrypted-message-input').val(decrypted);
  	return decrypted;
}

/// 자바스크립트를 이용해서 파일 읽어오기
function onFileSelected(event) {
	const file = event.target.files[0];
	const reader = new FileReader();
	reader.onload = function(event) {
		console.log("파일 내용 :" + event.target.result);
		const file = event.target.result;
		const privateKey = decrypt(file, "hello blockchain");
		/// 지갑
		const account = web3.eth.accounts.privateKeyToAccount(privateKey);
		const address = account.address;
		console.log("address : "+address);
		$.ajax({
			type: "post",
			dataType: "text",
			url: "/login/privateLogin",
			data: { "address": address },
			success: function(result){
				if(result == "1"){
					location.href="/";
				}else{
					alert("인증서에 해당하는 사용자가 없어 로그인되지 않았습니다.");
					location.reload();
				}				
			},
			error: function() {
				alert("오류입니다.");
				return false;
			}
		});
	}
	reader.readAsText(file);
}
