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
function validateForm() {
    var name = document.getElementById('name').value;
    var nickname = document.getElementById('nickname').value;
    var errorMessage = document.getElementById('error-message');

    // 여기에 실제 유효성 검사 로직을 추가하세요.
    // 예를 들어, 이름과 닉네임이 특정 값이 아니면 오류 메시지를 표시합니다.
    if (name !== 'expectedName' || nickname !== 'expectedNickname') {
        errorMessage.style.display = 'block';
    } else {
        errorMessage.style.display = 'none';
        // 유효한 경우의 처리 로직을 여기에 추가하세요.
    }
}
