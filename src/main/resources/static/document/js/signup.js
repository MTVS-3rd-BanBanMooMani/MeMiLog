document.addEventListener("DOMContentLoaded", function() {
    const emailInput = document.getElementById('email-local-part');
    const verificationMessage = document.getElementById('send-verification-message');
    const sendCodeButton = document.querySelector('.check-btn.duplicate-check-btn');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('password-confirm');
    const nicknameInput = document.getElementById('nickname');
    const yearSelect = document.getElementById('year');
    const monthSelect = document.getElementById('month');
    const daySelect = document.getElementById('day');

    console.log(emailInput, verificationMessage, sendCodeButton);
    passwordInput.addEventListener('input', validatePassword);
    confirmPasswordInput.addEventListener('input', validatePassword);
    confirmPasswordInput.addEventListener('input', validateConfirmPassword);
    nicknameInput.addEventListener('input', validateNickname);
    yearSelect.addEventListener('change', validateBirthday);
    monthSelect.addEventListener('change', validateBirthday);
    daySelect.addEventListener('change', validateBirthday);

    populateYearOptions();
    populateMonthOptions();
    yearSelect.addEventListener('change', populateDayOptions);
    monthSelect.addEventListener('change', populateDayOptions);
});

let timerInterval;

function sendVerificationCode() {
    const emailInput = document.getElementById('email-local-part').value.trim();
    const sendCodeButton = document.querySelector('.check-btn.duplicate-check-btn');
    if (!validateEmail(emailInput)) {
        const messageElement = document.getElementById('send-verification-message');
        messageElement.textContent = '유효한 이메일 주소를 입력해주세요.';
        messageElement.style.color = 'red';
        messageElement.style.display = 'block';
        messageElement.style.marginRight = '150px';
        document.querySelector('.input-group.email').style.marginBottom = '0';

        return;
    }

    const messageElement = document.getElementById('send-verification-message');
    const timerElement = document.getElementById('timer');
    messageElement.textContent = '인증코드를 보냈습니다.';
    messageElement.style.color = 'blue';
    messageElement.style.display = 'block';
    timerElement.style.display = 'block';
    messageElement.style.marginRight = '150px';
    document.querySelector('.input-group.email').style.marginBottom = '0';
    document.getElementById('email-local-part').readOnly = true;
    sendCodeButton.disabled = true;
    startTimer(300);
}

function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[a-zA-Z]{2,}$/;
    const tldRe = /\.(com|net|org|edu|gov|mil|int|biz|info|name|museum|coop|aero|asia|cat|jobs|mobi|tel|travel|xxx|pro|arpa|post|geo|gouv)$/i;
    return re.test(email) && tldRe.test(email);
}

function validatePassword() {
    const password = document.getElementById('password').value;
    const messageElement = document.getElementById('password-message');

    const passwordRegex = /^(?=.*[a-z])(?=.*\d)(?=.*[\W_]).{8,}$/;
    if (!passwordRegex.test(password)) {
        messageElement.textContent = '영어 소문자, 숫자, 특수문자 조합 8자 이상 입력해주세요.';
        messageElement.style.color = 'red';
        messageElement.style.display = 'block';
        document.querySelector('.input-group.password').style.marginBottom = '0';
    } else {
        messageElement.textContent = '';
        messageElement.style.display = 'none';
        document.querySelector('.input-group.password').style.marginBottom = '15px';
    }
}

function validateConfirmPassword() {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('password-confirm').value;
    const messageElement = document.getElementById('confirm-password-message');
    const confirmPasswordGroup = document.querySelector('.input-group.check');

    if (password !== confirmPassword) {
        messageElement.textContent = '비밀번호를 다시 입력해 주세요.';
        messageElement.style.color = 'red';
        messageElement.style.display = 'block';
        confirmPasswordGroup.style.marginBottom = '0';
    } else {
        messageElement.textContent = '';
        messageElement.style.display = 'none';
        confirmPasswordGroup.style.marginBottom = '15px';
    }
}

function validateNickname() {
    const nickname = document.getElementById('nickname').value.trim();
    const messageElement = document.getElementById('nickname-message');
    const nicknameRegex = /^[가-힣a-zA-Z0-9]{2,10}$/;

    if (!nicknameRegex.test(nickname)) {
        messageElement.textContent = '닉네임은 2~10글자의 한글, 영어, 소문자, 숫자만 이용 가능합니다.';
        messageElement.style.color = 'red';
        messageElement.style.display = 'block';
        document.querySelector('.input-group.nickname').style.marginBottom = '0';
    } else {
        messageElement.textContent = '';
        messageElement.style.display = 'none';
        document.querySelector('.input-group.nickname').style.marginBottom = '15px';
    }
}

function populateYearOptions() {
    const yearSelect = document.getElementById('year');
    const currentYear = new Date().getFullYear();
    for (let year = currentYear; year >= 1900; year--) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearSelect.appendChild(option);
    }
}

function populateMonthOptions() {
    const monthSelect = document.getElementById('month');
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.textContent = month;
        monthSelect.appendChild(option);
    }
}

function populateDayOptions() {
    const year = document.getElementById('year').value;
    const month = document.getElementById('month').value;
    const daySelect = document.getElementById('day');
    daySelect.innerHTML = '';

    if (year && month) {
        const daysInMonth = new Date(year, month, 0).getDate();
        for (let day = 1; day <= daysInMonth; day++) {
            const option = document.createElement('option');
            option.value = day;
            option.textContent = day;
            daySelect.appendChild(option);
        }
    }
}

function validateBirthday() {
    const year = document.getElementById('year').value;
    const month = document.getElementById('month').value;
    const day = document.getElementById('day').value;
    const messageElement = document.getElementById('birthday-message');

    if (!year || !month || !day) {
        messageElement.textContent = '날짜를 정확히 선택해주세요.';
        messageElement.style.color = 'red';
        messageElement.style.display = 'block';
        document.querySelector('.input-group.birthday').style.marginBottom = '0';
    } else {
        messageElement.textContent = '';
        messageElement.style.display = 'none';
        document.querySelector('.input-group.birthday').style.marginBottom = '15px';
    }
}

function verifyCode() {
    const inputCode = document.getElementById('email-code').value;
    const message = document.getElementById('verify-message');
    const correctCode = '123456'; // 예시용 인증코드

    if (inputCode === correctCode) {
        clearInterval(timerInterval);
        message.textContent = '인증코드가 확인되었습니다.';
        message.className = 'verification-message success';
        message.style.marginRight = '150px';
        document.querySelector('.input-group.email-code').style.marginBottom = '0';

        document.getElementById('email-local-part').readOnly = true;
        document.getElementById('email-code').disabled = true;
    } else {
        message.textContent = '인증코드가 일치하지 않습니다.';
        message.className = 'verification-message error';
        message.style.marginRight = '150px';
        document.querySelector('.input-group.email-code').style.marginBottom = '0';

    }
    message.style.display = 'block';
}

function startTimer(duration) {
    let timer = duration, minutes, seconds;
    const timerElement = document.getElementById('timer');

    timerInterval = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        timerElement.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            clearInterval(timerInterval);
            timerElement.textContent = "인증코드 시간이 만료되었습니다.";
            document.querySelector('.check-btn.duplicate-check-btn').disabled = false;
        }
    }, 1000);
}