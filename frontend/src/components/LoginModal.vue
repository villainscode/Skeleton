<template>
  <div class="modal-overlay" @click.self="$emit('close')">
    <div class="modal-container">
      <div class="modal-content">
        <button class="close-btn" @click="$emit('close')">X</button>

        <div class="modal-body">
          <h2 class="text-center mb-4">로그인</h2>
          <form @submit.prevent="handleSubmit">
            <div class="mb-3">
              <label for="email" class="form-label">이메일</label>
              <input type="email" class="form-control" id="email" v-model="email" required :class="{ 'is-invalid': emailError }">
              <div class="invalid-feedback">{{ emailError }}</div>
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password" class="form-control" id="password" v-model="password" required :class="{ 'is-invalid': passwordError }">
              <div class="invalid-feedback">{{ passwordError }}</div>
            </div>
            <div class="mb-4 form-check">
              <input type="checkbox" class="form-check-input" id="autoLogin" v-model="autoLogin">
              <label class="form-check-label" for="autoLogin">자동 로그인</label>
            </div>
            <div class="d-flex justify-content-between mb-3">
              <button type="submit" class="btn btn-primary flex-fill me-1">로그인하기</button>
              <button type="button" class="btn btn-secondary flex-fill ms-1">아이디/비밀번호 찾기</button>
            </div>

          </form>
          <hr>
          <div class="text-center">
            <p>소셜 계정으로 로그인</p>
            <button v-for="btn in socialButtons" :key="btn.name" :class="['social-btn', btn.class]" @click="socialLogin(btn.name)">
              <img :src="btn.imgSrc" :alt="btn.name" style="width: 18px; height: 18px; margin-right: 8px; vertical-align: middle;">
              {{ btn.text }}
            </button>
            <button class="social-btn signup-btn" @click="goToSignup">회원가입하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'LoginModal',
  data() {
    return {
      email: '',
      password: '',
      autoLogin: false,
      emailError: '',
      passwordError: '',
      socialButtons: [
        { name: 'Google', class: 'google-btn', text: 'Google로 로그인', imgSrc: 'https://www.gstatic.com/firebasejs/ui/2.0.0/images/auth/google.svg' },
        { name: 'Kakao', class: 'kakao-btn', text: '카카오로 로그인', imgSrc: 'https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png' },
        { name: 'Naver', class: 'naver-btn', text: '네이버로 로그인', imgSrc: 'https://static.nid.naver.com/oauth/button_g.PNG' },
      ]
    }
  },
  methods: {
    handleSubmit() {
      this.emailError = '';
      this.passwordError = '';

      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(this.email)) {
        this.emailError = '유효한 이메일 주소를 입력해주세요.';
        return;
      }

      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$/;
      if (!passwordRegex.test(this.password)) {
        this.passwordError = '비밀번호는 영문, 숫자, 특수문자를 각각 1개 이상 포함하여 6자리 이상이어야 합니다.';
        return;
      }

      console.log('Login validation passed');
      // 여기에 로그인 로직을 추가하세요
    },
    socialLogin(provider) {
      console.log(`${provider} 로그인 시도`);
      // 소셜 로그인 로직을 여기에 추가하세요
    },
    signup() {
      console.log('회원가입 시도');
      // 회원가입 로직을 여기에 추가하세요
    }
  }
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-container {
  background-color: white;
  border-radius: 8px;
  max-width: 400px;
  width: calc(100% - 40px); /* 양쪽 20px씩 여백 */
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  position: relative; /* X 버튼 위치 조정을 위해 추가 */
}


.modal-content {
  padding: 20px;
}

.text-center {
  text-align: center;
}

.form-label {
  font-weight: bold;
  display: block;
  text-align: left;
  margin-bottom: 0.5rem;
}

.form-control {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  box-sizing: border-box; /* 추가: padding과 border를 포함하도록 */
}

.mb-3 {
  margin-bottom: 1rem !important;
}

.mb-4 {
  margin-bottom: 1.5rem !important;
}

.form-check {
  display: flex;
  align-items: center;
}

.form-check-input {
  margin-right: 10px;
}

.d-flex {
  display: flex;
}

.justify-content-between {
  justify-content: space-between;
}

.flex-fill {
  flex: 1;
}

.me-1 {
  margin-right: 0.5rem;
}

.ms-1 {
  margin-left: 0.5rem;
}

.close-btn {
  position: absolute; /* 닫기 버튼 위치 조정 */
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  color: black; /* 버튼 색상을 명확히 지정 */
}

.btn {
  padding: 10px;
  font-size: 16px;
  cursor: pointer;
}

.btn-primary {
  background-color: #007bff;
  border: none;
  color: white;
}

.btn-secondary {
  background-color: #6c757d;
  border: none;
  color: white;
}

.social-btn {
  width: 100%;
  height: 40px;
  margin-bottom: 10px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  line-height: 40px;
  cursor: pointer;
}

.google-btn {
  background-color: #ffffff;
  color: #757575;
  border: 1px solid #dadce0;
}

.kakao-btn {
  background-color: #fee500;
  color: #000000;
}

.naver-btn {
  background-color: #03c75a;
  color: #ffffff;
}

.signup-btn {
  background-color: #6c757d;
  color: #ffffff;
}



</style>