<template>
  <div>
    <h2>체크박스 예제</h2>
    <input type="checkbox" id="testCheckbox" v-model="checked">
    <label for="testCheckbox">{{ checked ? '선택됨' : '선택되지 않음' }}</label>
    <p>체크박스 상태: {{ checked }}</p>
  </div>
</template>

<script>
export default {
  name: 'CheckboxExample',
  data() {
    return {
      checked: false
    }
  }
}
</script>