function toggleEmailDomainInput(select) {
    const input = document.getElementById('email-domain-input');
    const message = document.getElementById('send-verification-message');
    if (select.value === "") {
        input.style.display = 'inline-block';
        select.style.display = 'none';
        input.focus();
        message.textContent = '';
    }
}

function handleDuplicateCheck() {
    showVerification();
    document.querySelector('.input-group').classList.add('no-margin');
    document.querySelector('.verification-input-group').classList.add('no-margin');

    document.querySelector('.verification-code-btn').style.display = 'inline-block';
    document.querySelector('.verify-btn').style.display = 'inline-block';

    document.querySelector('.duplicate-check-btn').style.marginTop = '20px';
}

function restoreEmailDomainSelect() {
    const select = document.getElementById('email-domain-select');
    const input = document.getElementById('email-domain-input');
    if (input.value === "") {
        input.style.display = 'none';
        select.style.display = 'inline-block';
        select.value = "";
    }
}

function showVerification() {
    document.querySelector('.verification-input-group').style.display = 'flex';
    document.querySelector('.verification-code-btn').style.display = 'inline-block';
    document.querySelector('.verify-btn').style.display = 'inline-block';
}

function sendVerificationCode() {
    // 인증코드 전송 로직 (예: 이메일 전송)
    const message = document.getElementById('send-verification-message');
    message.textContent = '인증코드가 이메일로 발송되었습니다.';
    message.className = 'verification-message success';
    message.style.display = 'block';

    document.querySelector('.verify-btn').style.marginTop = '26px';

    document.getElementById('email-local-part').disabled = true;
    document.getElementById('email-domain-select').disabled = true;
    document.getElementById('email-domain-input').disabled = true;

    document.querySelector('.duplicate-check-container').style.marginTop = '-5px';
}

function verifyCode() {
    const inputCode = document.getElementById('verification-code').value;
    const message = document.getElementById('verify-message');
    // 실제 인증코드 확인 로직 (예: 서버에서 확인)
    const correctCode = '123456'; // 예시용 인증코드

    if (inputCode === correctCode) {
        message.textContent = '인증코드가 확인되었습니다.';
        message.className = 'verification-message success';

        document.getElementById('email-local-part').disabled = true;
        document.getElementById('email-domain-select').disabled = true;
        document.getElementById('email-domain-input').disabled = true;
        document.getElementById('verification-code').disabled = true;
    } else {
        message.textContent = '인증코드가 일치하지 않습니다.';
        message.className = 'verification-message error';
    }
    message.style.display = 'block';

    document.querySelector('.duplicate-check-container').style.marginBottom = '455px';
}

document.addEventListener("DOMContentLoaded", function() {
    const select = document.getElementById('email-domain-select');
    select.addEventListener('change', function() {
        toggleEmailDomainInput(select);
    });
});
document.addEventListener("DOMContentLoaded", function() {
    const select = document.getElementById('email-domain-select');
    select.addEventListener('change', function() {
        toggleEmailDomainInput(select);
    });

    populateBirthdaySelects();
});

function populateBirthdaySelects() {
    const yearSelect = document.getElementById('year');
    const monthSelect = document.getElementById('month');
    const daySelect = document.getElementById('day');

    const currentYear = new Date().getFullYear();
    for (let i = currentYear; i >= 1900; i--) {
        const option = document.createElement('option');
        option.value = i;
        option.textContent = i;
        yearSelect.appendChild(option);
    }

    for (let i = 1; i <= 12; i++) {
        const option = document.createElement('option');
        option.value = i;
        option.textContent = i;
        monthSelect.appendChild(option);
    }

    yearSelect.addEventListener('change', updateDays);
    monthSelect.addEventListener('change', updateDays);

    function updateDays() {
        const year = parseInt(yearSelect.value);
        const month = parseInt(monthSelect.value);
        daySelect.innerHTML = '<option value="">일</option>'; // Reset day options
        if (!isNaN(year) && !isNaN(month)) {
            const daysInMonth = new Date(year, month, 0).getDate();
            for (let i = 1; i <= daysInMonth; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.textContent = i;
                daySelect.appendChild(option);
            }
        }
    }
}

