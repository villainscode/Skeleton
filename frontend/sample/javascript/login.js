document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const emailError = document.getElementById('emailError');
    const passwordError = document.getElementById('passwordError');

    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Reset error messages
        emailError.textContent = '';
        passwordError.textContent = '';
        emailInput.classList.remove('is-invalid');
        passwordInput.classList.remove('is-invalid');

        // Validate email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(emailInput.value)) {
            emailInput.classList.add('is-invalid');
            emailError.textContent = '유효한 이메일 주소를 입력해주세요.';
            return;
        }

        // Validate password
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$/;
        if (!passwordRegex.test(passwordInput.value)) {
            passwordInput.classList.add('is-invalid');
            passwordError.textContent = '비밀번호는 영문, 숫자, 특수문자를 각각 1개 이상 포함하여 6자리 이상이어야 합니다.';
            return;
        }

        // If validation passes, you can proceed with login logic here
        console.log('Login validation passed');
    });
});
