import { createApp } from 'vue';
import App from './App.vue';
import router from './router/index'; // 라우터 설정 가져오기

const app = createApp(App);

app.use(router); // 라우터 적용
app.mount('#app'); // 애플리케이션 마운트
