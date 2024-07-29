<template>
  <div class="modal-overlay" @click.self="cancel">
    <div class="modal-container">
      <button class="close-btn" @click.stop="cancel">
        <i class="fas fa-times"></i>
      </button>
      <div class="modal-content">
        <div class="modal-body">
          <h2 class="text-center mb-3">회원 가입</h2>
          <form @submit.prevent="handleSubmit">

            <div class="form-group d-flex align-items-center">
              <label for="email" class="form-label me-2">이메일</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-envelope input-icon"></i>
                <input type="email" class="form-control" id="email" v-model="email"
                       placeholder="해당 메일로 인증 코드를 전송합니다"
                       data-placeholder="해당 메일로 인증 코드를 전송합니다"
                       @focus="clearPlaceholder"
                       @blur="validateEmailOnBlur"
                       :class="{ 'is-invalid': emailError }">
              </div>
            </div>
            <div v-if="emailError" class="error-message">{{ emailError }}</div>
            <div class="form-group d-flex align-items-center">
              <label for="name" class="form-label me-2">이름</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-user input-icon"></i>
                <input type="text" class="form-control"
                       id="name"
                       v-model="name"
                       :class="{ 'is-invalid': nameError }">
              </div>
            </div>
            <div v-if="nameError" class="error-message">{{ nameError }}</div>
            <div class="form-group d-flex align-items-center">
              <label for="password" class="form-label me-2">비밀번호</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control" id="password" v-model="password" :class="{ 'is-invalid': passwordError }">
              </div>
            </div>
            <div v-if="passwordError" class="error-message">{{ passwordError }}</div>

            <div class="form-group d-flex align-items-center">
              <label for="confirmPassword" class="form-label me-2">비밀번호 확인</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control" id="confirmPassword" v-model="confirmPassword" :class="{ 'is-invalid': confirmPasswordError }">
              </div>
            </div>
            <div v-if="confirmPasswordError" class="error-message">{{ confirmPasswordError }}</div>

            <div class="form-group d-flex align-items-center">
              <label for="phone" class="form-label me-2">전화번호</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-phone input-icon"></i>
                <input type="text" class="form-control" id="phone" v-model="phone"
                       placeholder="01012345678"
                       data-placeholder="01012345678"
                       maxlength="11"
                       @input="validatePhoneInput"
                       @focus="clearPlaceholder"
                       @blur="restorePlaceholder" :class="{ 'is-invalid': phoneError }">
              </div>
            </div>
            <div v-if="phoneError" class="error-message">{{ phoneError }}</div>

            <hr class="separator">
            <div class="form-group mb-2">
              <label for="terms" class="form-label">약관 동의</label>
            </div>
            <div class="form-group mb-2">
              <textarea id="terms" readonly class="form-control terms">[개인정보 동의 및 사이트 가입 동의 내용]</textarea>
            </div>

            <div class="form-group mb-2">
              <div class="form-check">
                <input type="checkbox" class="form-check-input" id="agreeTerms" v-model="agreeTerms" :class="{ 'is-invalid': agreeTermsError }">
                <label class="form-check-label" for="agreeTerms">약관에 동의합니다</label>
              </div>
            </div>
            <div v-if="agreeTermsError" class="error-message">{{ agreeTermsError }}</div>

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
import axios from "axios";
import CryptoJS from 'crypto-js';

const SECRET_KEY = '9fdfdc8392f4619e';

const REGEX = {
  KOREAN: /^[가-힣]+$/,
  ENGLISH: /^[a-zA-Z]{3,}$/,
  PASSWORD: /^(?=.*[A-Za-z])|(?=.*[A-Za-z])(?=.*\d)|(?=.*[A-Za-z])(?=.*[@$!%*?&])|(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])|(?=.*\d)(?=.*[@$!%*?&]).{6,}$/,
  PHONE: /^(01[016789])\d{7,8}$/,
  EMAIL: /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/
};

const ERROR_MESSAGES = {
  NAME: '이름은 한글 두글자 혹은 영문 세글자 이상 입력해주세요.',
  PASSWORD: '비밀번호는 6자리 이상(숫자,특수문자 가능) 입력해주세요.',
  CONFIRM_PASSWORD: '비밀번호가 일치하지 않습니다.',
  PHONE: '전화번호는 11자리 숫자여야 합니다.',
  AGREE_TERMS: '약관에 동의해주세요.',
  EMAIL: '유효한 이메일 주소를 입력해주세요.'
};


export default {
  data() {
    return {
      email: '',
      emailError: '',
      name: '',
      nameError: '',
      password: '',
      passwordError: '',
      confirmPassword: '',
      confirmPasswordError: '',
      phone: '',
      phoneError: '',
      agreeTerms: false,
      agreeTermsError: ''
    }
  },
  methods: {
    validateField(field, value, regex, errorMessage) {
      if (!regex.test(value)) {
        this[`${field}Error`] = errorMessage;
        return false;
      }
      this[`${field}Error`] = '';
      return true;
    },
    validateEmail() {
      return this.validateField('email', this.email, REGEX.EMAIL, ERROR_MESSAGES.EMAIL);
    },
    validateEmailOnBlur(event) {
      this.validateEmail();
    },
    validateName() {
      console.log("validateName");
      if (this.name.length == '' || this.name.length === 0) {
        this.nameError = ERROR_MESSAGES.NAME;
        return false;
      } else {
        const resultKorean = this.validateField('name', this.name, REGEX.KOREAN, ERROR_MESSAGES.NAME);
        const resultEnglish = this.validateField('name', this.name, REGEX.ENGLISH, ERROR_MESSAGES.NAME);
        const result = resultKorean || resultEnglish;

        if (!result) {
          this.nameError = ERROR_MESSAGES.NAME;
          return false;
        }
        this.nameError = '';
        return true;
      }
    },
    validatePassword() {
      return this.validateField('password', this.password, REGEX.PASSWORD, ERROR_MESSAGES.PASSWORD);
    },
    validatePassConfirm() {
      if (this.password !== this.confirmPassword) {
        this.confirmPasswordError = ERROR_MESSAGES.CONFIRM_PASSWORD;
        return false;
      } else {
        this.confirmPasswordError = '';
        return true;
      }
    },
    validatePhoneNumber() {
      return this.validateField('phone', this.phone, REGEX.PHONE, ERROR_MESSAGES.PHONE);
    },
    validateAgree() {
      if (!this.agreeTerms) {
        this.agreeTermsError = ERROR_MESSAGES.AGREE_TERMS;
        return false;
      }

      this.agreeTermsError = '';
      return true;
    },
    validatePhoneInput(event) {
      this.phone = event.target.value.replace(/\D/g, '');
    },

    async handleSubmit() {
      if (!this.validateEmail()) return
      if (!this.validateName()) return
      if (!this.validatePassword()) return
      if (!this.validatePassConfirm()) return
      if (!this.validatePhoneNumber()) return
      if (!this.validateAgree()) return

      // 비밀번호 암호화
      const encryptedPassword = CryptoJS.AES.encrypt(this.password, CryptoJS.enc.Utf8.parse(SECRET_KEY), {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
      }).toString();

      const userRegister = {
        email: this.email,
        name: this.name,
        password: encryptedPassword,
        phone: this.phone
      }

      console.log('# user = ' + userRegister.password);
      // 여기서 회원 가입 로직을 처리합니다.
      try {
        const response = await axios.post('/api/register', userRegister);
        // 회원 가입 성공 시 추가 처리
        console.log('서버 응답:', response.data);
      } catch (error) {
        console.error('회원 가입 중 오류 발생:', error);
      }
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
      if (!event.target.value) {
        event.target.placeholder = event.target.getAttribute('data-placeholder');
      }
    }
  }
}
</script>

<style scoped>
@import '@fortawesome/fontawesome-free/css/all.css';

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
  background-color: #f9f9f9;
  border-radius: 8px;
  max-width: 450px;
  width: calc(100% - 40px);
  padding: 20px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  position: relative;
  z-index: 1001;
}

.modal-content {
  padding: 20px;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #6c757d;
  opacity: 0.5;
  transition: opacity 0.15s ease-in-out;
  z-index: 1002;
}

.text-center {
  text-align: center;
}

.form-group {
  margin-bottom: 0.1rem;
  display: flex;
  align-items: center;
}

.form-label {
  font-weight: bold;
  display: block;
  text-align: left;
  margin-bottom: 0;
  line-height: 1.5rem;
  color: #333;
  font-size: 1.0rem;
  width: 120px; /* 레이블의 너비를 설정하여 정렬을 일관되게 만듦 */
}

.form-control {
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
  height: 2.0rem;
  border: 1px solid #ced4da;
  border-radius: 5px;
  font-size: 0.9rem; /* 폰트 크기를 크게 조정 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 바깥쪽으로 그림자 효과 */
  background-color: #f8f9fa;
  transition: box-shadow 0.3s ease, border-color 0.3s ease;
}

.form-control:focus {
  border-color: #80bdff;
  box-shadow: 0 0 6px rgba(0, 123, 255, 0.25); /* 포커스 시 그림자와 테두리 색상 변경 */
  outline: none;
}

.input-group {
  display: flex;
  align-items: center;
  border-radius: 5px;
  padding: 5px;
  background-color: #f9f9f9;
  flex-grow: 1;
}

.input-icon {
  margin-right: 10px;
  color: #007bff;
}

.btn {
  padding: 10px;
  font-size: 16px;
  cursor: pointer;
  border-radius: 5px;
}

.btn-primary {
  background-color: #007bff;
  border: none;
  color: white;
  transition: background-color 0.3s;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  border: none;
  color: white;
  transition: background-color 0.3s;
}

.btn-secondary:hover {
  background-color: #5a6268;
}

.terms {
  height: 100px;
  overflow-y: scroll;
  margin-top: 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  font-size: 0.8rem; /* 폰트 사이즈 조정 */
}

.separator {
  border: 0;
  border-top: 2px solid #87a5c4;
  margin: 1rem 0;
}

.error-message {
  color: #dc3545;
  font-size: 0.875rem;
  width: 100%;
  margin-bottom: 0.75rem;
}
.is-invalid {
  border-color: #dc3545;
}

</style>
