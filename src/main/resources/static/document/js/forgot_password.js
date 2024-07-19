document.addEventListener("DOMContentLoaded", function() {
    const submitBtn = document.getElementById('submit-btn')
    submitBtn.readOnly = true;
})

document.getElementById('email-tab').addEventListener('click', function() {
    document.getElementById('email-content').classList.add('active');
    document.getElementById('password-content').classList.remove('active');
    this.classList.add('active');
    document.getElementById('password-tab').classList.remove('active');
});

document.getElementById('password-tab').addEventListener('click', function() {
    document.getElementById('email-content').classList.remove('active');
    document.getElementById('password-content').classList.add('active');
    this.classList.add('active');
    document.getElementById('email-tab').classList.remove('active');
});

function applyInputContainerMargin() {
    const inputContainers = document.querySelectorAll('.input-container');
    inputContainers.forEach(container => {
        container.style.marginBottom = '8px';
    });
}

function validateForm() {
    const emailInput = document.getElementById('email');
    const errorMessage = document.getElementById('error-message');
    const modalBtn = document.getElementById('modal-btn')
    const email = emailInput.value;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    applyInputContainerMargin();

    if (!emailPattern.test(email)) {
        errorMessage.textContent = '이메일 주소를 올바르게 입력해주세요.';
        errorMessage.classList.add('error');
        errorMessage.classList.remove('success');
        errorMessage.style.display = 'block';
        modalBtn.style = "display:none"
    } else {
        // POST 요청
        fetch("/user/forgotID", {
            method:'POST',
            headers:{
                'Content-Type':'application/json'
            },
            body:JSON.stringify(email)
        })
        .then(res => {
            if(res.ok) {
                console.log("성공")
                modalBtn.style = "display:block";
                showSuccessModal(email);
                emailInput.readOnly = true;
            }
            else {
                console.log("실패")
                modalBtn.style = "display:none"
                showFailModal(email);
            }
        })
    }
}

function requestVerification() {
    const emailInput = document.getElementById('email-verification');
    const errorMessage = document.getElementById('error-message-email-verification');
    const email = emailInput.value;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    applyInputContainerMargin();

    if (!emailPattern.test(email)) {
        errorMessage.textContent = '이메일 주소를 올바르게 입력해주세요.';
        errorMessage.classList.add('error');
        errorMessage.classList.remove('success');
        errorMessage.style.display = 'block';
    } else {
        // 인증 요청 보내기
        fetch("/user/verify?email="+email)
        .then(res => res.text()).then(data => {
            errorMessage.textContent = '인증요청을 하였습니다.';
            errorMessage.classList.add('success');
            errorMessage.classList.remove('error');
            errorMessage.style.display = 'block';
            emailInput.readOnly = true;
        })
        .catch(err => {
            console.log("인증코드 발송 오류 : " + err)
            errorMessage.textContent = '이메일 주소를 올바르게 입력해주세요.';
            errorMessage.classList.add('error');
            errorMessage.classList.remove('success');
            errorMessage.style.display = 'block';
        })
    }
}

function validateVerificationCode() {
    const inputEmail = document.getElementById('email-verification').value;
    const codeInput = document.getElementById('verification-code');
    const errorMessage = document.getElementById('error-message-verification-code');
    const submitBtn = document.getElementById('submit-btn')
    const code = codeInput.value;

    applyInputContainerMargin();

    fetch("/user/verify", {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            email: inputEmail,
            verify_code: code
        })
    })
    .then(res => {
        if(res.ok) {
            errorMessage.textContent = '확인되었습니다.';
            errorMessage.classList.add('success');
            errorMessage.classList.remove('error');
            errorMessage.style.display = 'block';
            codeInput.readOnly = true;
            submitBtn.readOnly = false;
        } else {
            throw new Error('Verification Failed')
        }
    })
    .catch((error) => {
        console.log("인증 코드 오류 : ", error)
        errorMessage.textContent = '인증 코드를 올바르게 입력해주세요.';
        errorMessage.classList.add('error');
        errorMessage.classList.remove('success');
        errorMessage.style.display = 'block';
        submitBtn.readOnly = true;
    })
}


function showSuccessModal(email) {
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modal-title');
    const modalMessage = document.getElementById('modal-message');

    modalTitle.innerHTML = '<b>같은 주소로 가입한 계정이 있어요!</b>';
    modalMessage.innerHTML = `이 메일 주소(${email})는 <br> 저희 서비스에 가입되어있어요!<br>해당 계정으로 로그인 해 주세요.`;

    modal.style.display = 'flex';
}

function showFailModal(email) {
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modal-title');
    const modalMessage = document.getElementById('modal-message');

    modalTitle.innerHTML = '<b>해당 이메일로 가입된 계정이 없어요!</b>';
    modalMessage.innerHTML = `이 메일 주소(${email})는 <br> 저희 서비스에 가입되어 있지 않습니다!<br>다시 시도해주세요.`;

    modal.style.display = 'flex';
}


function closeModal() {
    const modal = document.getElementById('modal');
    modal.style.display = 'none';
    const inputContainers = document.querySelectorAll('.input-container');
    inputContainers.forEach(container => {
        container.style.marginBottom = '1.5rem';
    });
}


function startLogin() {
    window.location.href = '/user/login';
}
