<template>
  <div>
    <h1>동적 이벤트 바인딩 예제</h1>

    <select v-model="selectedEvent">
      <option value="click">Click</option>
      <option value="mouseover">Mouseover</option>
      <option value="mouseout">Mouseout</option>
    </select>

    <button
        @[selectedEvent]="handleEvent"
        :style="{ marginLeft: '10px', padding: '5px' }"
    >
      {{ buttonText }}
    </button>

    <p>선택된 이벤트: {{ selectedEvent }}</p>
    <p>이벤트 발생 횟수: {{ eventCount }}</p>
  </div>
</template>

<script>
export default {
  name: 'DynamicEventExample',
  data() {
    return {
      selectedEvent: 'click',
      eventCount: 0,
      buttonText: '이벤트 테스트'
    }
  },
  methods: {
    handleEvent(event) {
      this.eventCount++;
      console.log(`${this.selectedEvent} 이벤트 발생!`);

      // 이벤트 타입에 따라 다른 동작 수행
      switch(this.selectedEvent) {
        case 'click':
          this.buttonText = '클릭됨!';
          break;
        case 'mouseover':
          this.buttonText = '마우스 오버!';
          break;
        case 'mouseout':
          this.buttonText = '마우스 아웃!';
          break;
      }

      // 1초 후 버튼 텍스트 원래대로 복구
      setTimeout(() => {
        this.buttonText = '이벤트 테스트';
      }, 1000);
    }
  }
}
</script>