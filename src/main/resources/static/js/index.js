const btnOtp = document.querySelector("#btnOtp");
btnOtp.addEventListener("click", () => {
    const email = document.querySelector("#email").value;
    if (email === "") {
        alert("입력 값을 확인해세요");
        return;
    }

    document.querySelector("#timer",)

    fetch("/email", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
        })
    })
        .then(res => {
            console.log(res);
            if (!res.ok) {
                alert("실패");
                return;
            }
            return res.json();
        })
        .then(data =>{
            document.querySelector("#code").innerText = data.verifyCode;
        })
});

const btnVerify = document.querySelector("#btnVerify");
btnVerify.addEventListener("click", ()=> {
    const email = document.querySelector("#email").value;
    const code = document.querySelector("#verify").value;
    fetch("/email/verify", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            code: code
        })
    })
        .then(res =>{
            if(res.ok){
                alert("인증 성공");
            }
            else{
                alert("높");
            }
        })
});