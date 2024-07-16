<template>
  <div class="signup-container">
    <h2>회원 가입</h2>
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="email">이메일</label>
        <input type="email" id="email" v-model="email" required class="form-control">
      </div>
      <div class="form-group">
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" required class="form-control">
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" required class="form-control">
      </div>
      <div class="form-group">
        <label for="confirmPassword">비밀번호 확인</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required class="form-control">
      </div>
      <div class="form-group">
        <label for="phone1">전화번호</label>
        <input type="text" id="phone1" v-model="phone1" placeholder="010" maxlength="3" class="form-control">
        <input type="text" v-model="phone2" maxlength="4" class="form-control">
        <input type="text" v-model="phone3" maxlength="4" class="form-control">
      </div>
      <div class="form-group">
        <label for="birthdate">생년 월일</label>
        <select v-model="birthYear" class="form-control">
          <option v-for="year in years" :key="year">{{ year }}</option>
        </select>
        <select v-model="birthMonth" class="form-control">
          <option v-for="month in months" :key="month">{{ month }}</option>
        </select>
        <select v-model="birthDay" class="form-control">
          <option v-for="day in days" :key="day">{{ day }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="terms">약관 동의</label>
        <textarea id="terms" readonly class="form-control terms">
          [개인정보 동의 및 사이트 가입 동의 내용]
        </textarea>
        <div class="form-check">
          <input type="checkbox" id="agreeTerms" v-model="agreeTerms" required>
          <label for="agreeTerms">약관에 동의합니다</label>
        </div>
      </div>
      <div class="form-group d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">가입 완료</button>
        <button type="button" @click="cancel" class="btn btn-secondary">취소</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      name: '',
      password: '',
      confirmPassword: '',
      phone1: '',
      phone2: '',
      phone3: '',
      birthYear: '',
      birthMonth: '',
      birthDay: '',
      agreeTerms: false,
      years: Array.from({ length: 100 }, (v, k) => k + 1920),
      months: Array.from({ length: 12 }, (v, k) => k + 1),
      days: Array.from({ length: 31 }, (v, k) => k + 1)
    }
  },
  methods: {
    handleSubmit() {
      if (this.password !== this.confirmPassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }
      // 여기서 회원 가입 로직을 처리합니다.
      console.log('회원 가입 완료');
      this.$router.push('/');
    },
    cancel() {
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
.signup-container {
  max-width: 600px;
  margin: auto;
  padding: 20px;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  box-sizing: border-box;
}

.d-flex {
  display: flex;
}

.justify-content-between {
  justify-content: space-between;
}

.terms {
  height: 100px;
  overflow-y: scroll;
}
</style>
