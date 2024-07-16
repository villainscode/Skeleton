<template>
  <div id="app">
    <h1>Vue.js 예제 앱</h1>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/greeting">Greeting</router-link> |
      <router-link to="/checkbox">CheckBoxExample</router-link> |
      <router-link to="/event">DynamicEventExample</router-link> |
      <a href="#" @click.prevent="showLoginModal">Login</a>
    </nav>
    <router-view></router-view>

    <LoginModal v-if="isLoginModalVisible" @close="hideLoginModal" />
  </div>
</template>

<script>
import LoginModal from './components/LoginModal.vue'

export default {
  name: 'App',
  components: {
    LoginModal
  },
  data() {
    return {
      isLoginModalVisible: false
    }
  },
  methods: {
    showLoginModal() {
      this.isLoginModalVisible = true
      document.body.style.overflow = 'hidden' // 배경 스크롤 방지
    },
    hideLoginModal() {
      this.isLoginModalVisible = false
      document.body.style.overflow = 'auto' // 배경 스크롤 복원
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>