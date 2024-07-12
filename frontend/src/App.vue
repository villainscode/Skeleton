
<template>
  <div id="app">
    <h1>Vue.js 예제 앱</h1>
    <Greeting />
    <CheckboxExample />
    <DynamicEventExample />
  </div>
</template>

<script>
import Greeting from "./components/Greeting.vue";
import CheckboxExample from "./components/CheckboxExample.vue";
import DynamicEventExample from "./components/DynamicEventExample.vue";

export default {
  name: 'App',
  components: {
    Greeting,
    CheckboxExample,
    DynamicEventExample
  }
}
</script>