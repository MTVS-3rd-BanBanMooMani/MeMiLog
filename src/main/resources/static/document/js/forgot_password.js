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
    const email = emailInput.value;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    applyInputContainerMargin();

    if (!emailPattern.test(email)) {
        errorMessage.textContent = '이메일 주소를 올바르게 입력해주세요.';
        errorMessage.classList.add('error');
        errorMessage.classList.remove('success');
        errorMessage.style.display = 'block';
    } else {
        errorMessage.textContent = '아이디가 이메일로 전송되었습니다.';
        errorMessage.classList.add('success');
        errorMessage.classList.remove('error');
        errorMessage.style.display = 'block';
        emailInput.disabled = true;
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
        errorMessage.textContent = '인증요청을 하였습니다.';
        errorMessage.classList.add('success');
        errorMessage.classList.remove('error');
        errorMessage.style.display = 'block';
        emailInput.disabled = true;
    }
}

function validateVerificationCode() {
    const codeInput = document.getElementById('verification-code');
    const errorMessage = document.getElementById('error-message-verification-code');
    const code = codeInput.value;
    const codePattern = /^\d{6}$/;

    applyInputContainerMargin();

    if (!codePattern.test(code)) {
        errorMessage.textContent = '인증 코드를 올바르게 입력해주세요.';
        errorMessage.classList.add('error');
        errorMessage.classList.remove('success');
        errorMessage.style.display = 'block';
    } else {
        errorMessage.textContent = '확인되었습니다.';
        errorMessage.classList.add('success');
        errorMessage.classList.remove('error');
        errorMessage.style.display = 'block';
        codeInput.disabled = true;
    }
}
