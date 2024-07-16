<template>
  <div class="modal-overlay" @click.self="cancel">
    <div class="modal-container">
      <button class="close-btn" @click="cancel">X</button>
      <div class="modal-content">
        <div class="modal-body">
          <h2 class="text-center mb-4">회원 가입</h2>
          <form @submit.prevent="handleSubmit">

            <div class="form-group mb-4">
              <label for="email" class="form-label">이메일</label>
              <input type="email" class="form-control" id="email" v-model="email" required placeholder="해당 메일로 인증 코드를 전송합니다" @focus="clearPlaceholder" @blur="restorePlaceholder">
              <div v-if="emailError" class="invalid-feedback">{{ emailError }}</div>
            </div>

            <div class="form-group mb-4">
              <label for="name" class="form-label">이름</label>
              <input type="text" class="form-control" id="name" v-model="name" required>
            </div>
            <div class="form-group mb-4">
              <label for="password" class="form-label">비밀번호</label>
              <input type="password" class="form-control" id="password" v-model="password" required>
              <div v-if="passwordError" class="invalid-feedback">{{ passwordError }}</div> <!-- 추가 -->
            </div>
            <!-- ... 이전 내용 ... -->
            <div class="form-group mb-4">
              <label for="confirmPassword" class="form-label">비밀번호 확인</label>
              <input type="password" class="form-control" id="confirmPassword" v-model="confirmPassword" required>
              <div v-if="confirmPasswordError" class="invalid-feedback">{{ confirmPasswordError }}</div> <!-- 추가 -->
            </div>
            <div class="form-group mb-4">
              <label class="form-label">전화번호</label>
              <div class="d-flex">
                <input type="text" class="form-control me-1" v-model="phone1" placeholder="010" maxlength="3">
                <input type="text" class="form-control mx-1" v-model="phone2" maxlength="4">
                <input type="text" class="form-control ms-1" v-model="phone3" maxlength="4">
              </div>
            </div>
            <!--
            <div class="form-group mb-4">
              <label class="form-label">생년 월일</label>
              <div class="d-flex">
                <select v-model="birthYear" class="form-control me-1">
                  <option v-for="year in years" :key="year">{{ year }}</option>
                </select>
                <select v-model="birthMonth" class="form-control mx-1">
                  <option v-for="month in months" :key="month">{{ month }}</option>
                </select>
                <select v-model="birthDay" class="form-control ms-1">
                  <option v-for="day in days" :key="day">{{ day }}</option>
                </select>
              </div>
            </div>
            //-->
            <div class="form-group mb-4">
              <label for="terms" class="form-label">약관 동의</label>
              <textarea id="terms" readonly class="form-control terms">[개인정보 동의 및 사이트 가입 동의 내용]</textarea>
              <div class="form-check">
                <input type="checkbox" class="form-check-input" id="agreeTerms" v-model="agreeTerms" required>
                <label class="form-check-label" for="agreeTerms">약관에 동의합니다</label>
              </div>
            </div>
            <div class="d-flex justify-content-between mb-3">
              <button type="submit" class="btn btn-primary flex-fill me-1">가입 완료</button>
              <button type="button" class="btn btn-secondary flex-fill ms-1" @click="cancel">취소</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      emailError: '',
      name: '',
      password: '',
      passwordError: '',
      confirmPassword: '',
      confirmPasswordError: '',
      phone1: '',
      phone2: '',
      phone3: '',
      // birthYear: '',
      // birthMonth: '',
      // birthDay: '',
      agreeTerms: false,
      // years: Array.from({ length: 100 }, (v, k) => k + 1920),
      // months: Array.from({ length: 12 }, (v, k) => k + 1),
      // days: Array.from({ length: 31 }, (v, k) => k + 1)
    }
  },
  methods: {
    handleSubmit() {
      this.emailError = ''; // 초기화
      this.passwordError = ''; // 초기화
      this.confirmPasswordError = ''; // 초기화

      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(this.email)) {
        this.emailError = '유효한 이메일 주소를 입력해주세요.';
        return;
      }
      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      if (!passwordRegex.test(this.password)) {
        this.passwordError = '비밀번호는 최소 8자리 이상이고 문자와 숫자의 조합이어야 합니다.';
        return;
      }
      if (this.password !== this.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }
      // 여기서 회원 가입 로직을 처리합니다.
      console.log('회원 가입 완료');
      this.$router.push('/');
    },
    cancel() {
      this.$emit('close');
      this.$router.push('/');
    },
    clearPlaceholder(event) {
      event.target.placeholder = '';
    },
    restorePlaceholder(event) {
      event.target.placeholder = '해당 메일로 인증 코드를 전송합니다';
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

.text-center {
  text-align: center;
}

.form-label {
  font-weight: bold;
  display: block;
  text-align: left;
  margin-bottom: 5px;
  line-height: 1.5rem; /* 레이블 높이 설정 */
}

.form-control {
  width: 100%;
  padding: 10px;
  box-sizing: border-box; /* 추가: padding과 border를 포함하도록 */
  height: 2.2rem; /* 인풋 높이 설정 */
}

.form-group {
  margin-bottom: 0.5rem; /* 그룹 사이의 간격 조정 */
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

.mx-1 {
  margin-left: 0.5rem;
  margin-right: 0.5rem;
}

.ms-1 {
  margin-left: 0.5rem;
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

.terms {
  height: 100px;
  overflow-y: scroll;
  margin-top: 0; /* 약관 동의 텍스트 인풋의 여백 제거 */
}

.invalid-feedback {
  display: block;
  color: red;
  margin-top: 5px;
}
</style>
