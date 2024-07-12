<template>
  <div>
    <input v-model="name" @kayup.enter="sendGreeting" placeholder="Enter your name(이름)">
    <button @click="sendGreeting">인사하기</button>
    <p>{{ greeting }}</p>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      name: "",
      greeting: ""
    }
  },
  methods: {
    sendGreeting() {
      if (this.name.trim()) {
        axios.post('/api/greet', {name: this.name})
        .then(response => {
          this.greeting = response.data.message;
        })
        .catch(error => {
          console.log("Error : ", error);
          this.greeting = 'An error occurred';
        });
      }
    }
  }
}
</script>