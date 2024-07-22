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

            <div class="form-group mb-3 d-flex align-items-center">
              <label for="email" class="form-label me-2">이메일</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-envelope input-icon"></i>
                <input type="email" class="form-control" id="email" v-model="email"
                       placeholder="해당 메일로 인증 코드를 전송합니다"
                       data-placeholder="해당 메일로 인증 코드를 전송합니다"
                       @focus="clearPlaceholder"
                       @blur="validateEmailOnBlur"
                       ref="emailInput">
              </div>
            </div>

            <div class="form-group mb-3 d-flex align-items-center">
              <label for="name" class="form-label me-2">이름</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-user input-icon"></i>
                <input type="text" class="form-control" id="name" v-model="name" ref="nameInput">
              </div>
            </div>
            <div class="form-group mb-3 d-flex align-items-center">
              <label for="password" class="form-label me-2">비밀번호</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control" id="password" v-model="password" ref="passwordInput">
              </div>
            </div>

            <div class="form-group mb-3 d-flex align-items-center">
              <label for="confirmPassword" class="form-label me-2">비밀번호 확인</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-lock input-icon"></i>
                <input type="password" class="form-control" id="confirmPassword" v-model="confirmPassword" ref="confirmPasswordInput">
              </div>
            </div>

            <div class="form-group mb-3 d-flex align-items-center">
              <label for="phone" class="form-label me-2">전화번호</label>
              <div class="input-group flex-grow-1">
                <i class="fas fa-phone input-icon"></i>
                <input type="text" class="form-control" id="phone" v-model="phone" placeholder="01012345678" data-placeholder="01012345678" maxlength="11" @focus="clearPlaceholder" @blur="restorePlaceholder" ref="phoneInput">
              </div>
            </div>

            <hr class="separator">
            <div class="form-group mb-2">
              <label for="terms" class="form-label">약관 동의</label>
            </div>
            <div class="form-group mb-4">
              <textarea id="terms" readonly class="form-control terms">[개인정보 동의 및 사이트 가입 동의 내용]</textarea>
            </div>

            <div class="form-group mb-2">
              <div class="form-check">
                <input type="checkbox" class="form-check-input" id="agreeTerms" v-model="agreeTerms">
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

  <!-- 알림 모달 -->
  <div class="modal fade" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="alertModalLabel">알림</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          {{ alertMessage }}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" @click="closeAlertModal">확인</button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { Modal } from 'bootstrap'

export default {
  data() {
    return {
      email: '',
      name: '',
      password: '',
      confirmPassword: '',
      phone: '',
      agreeTerms: false,
      alertMessage: '',
      focusField: null,
      modal: null
    }
  },
  mounted() {
    this.modal = new Modal(document.getElementById('alertModal'))
  },
  methods: {
    showAlert(message, field) {
      this.alertMessage = message
      this.focusField = field
      this.modal.show()
    },
    closeAlertModal() {
      this.modal.hide()
      if (this.focusField) {
        this.$nextTick(() => {
          this.$refs[this.focusField].focus()
        })
      }
    },
    validateEmail() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/
      if (!emailRegex.test(this.email)) {
        this.showAlert('유효한 이메일 주소를 입력해주세요.', 'emailInput')
        return false
      }
      return true
    },
    validateEmailOnBlur(event) {
      this.restorePlaceholder(event);
      this.validateEmail();
    },
    validateName() {
      const koreanRegex = /^[가-힣]+$/
      const englishRegex = /^[a-zA-Z]{2,}$/
      const nameAlert = '이름은 한글 또는 영문으로 두 글자 이상 입력해주세요.'
      if (this.name.length == '') {
        this.showAlert(nameAlert, 'nameInput')
        return false
      }

      if (!(koreanRegex.test(this.name) && this.name.length >= 1) &&
          !(englishRegex.test(this.name) && this.name.length >= 2)) {
        this.showAlert(nameAlert, 'nameInput')
        return false
      }
      return true
    },

    handleSubmit() {
      if (!this.validateEmail()) return
      if (!this.validateName()) return

      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
      if (!passwordRegex.test(this.password)) {
        this.showAlert('비밀번호는 최소 8자리 이상이고 문자와 숫자의 조합이어야 합니다.', 'passwordInput')
        return
      }

      if (this.password !== this.confirmPassword) {
        this.showAlert('비밀번호가 일치하지 않습니다.', 'confirmPasswordInput')
        return
      }

      const phoneRegex = /^\d{11}$/
      if (!phoneRegex.test(this.phone)) {
        this.showAlert('전화번호는 11자리 숫자여야 합니다.', 'phoneInput')
        return
      }
      if (!this.agreeTerms) {
        this.showAlert('약관에 동의해주세요.', 'agreeTermsInput')
        return
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

.modal-dialog-centered {
  display: flex;
  align-items: center;
  min-height: calc(100% - 1rem);
}

@media (min-width: 576px) {
  .modal-dialog-centered {
    min-height: calc(100% - 3.5rem);
  }
}
</style>
