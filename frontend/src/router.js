import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue';       // @ 절대 경로 혹은 ./components/Home.vue 처럼 상대 경로로 가능
import Greeting from "@/components/Greeting.vue";
import CheckboxExample from "@/components/CheckboxExample.vue";
import DynamicEventExample from "@/components/DynamicEventExample.vue";
import LoginModal from "@/components/LoginModal.vue";
import Signup from "@/components/Signup.vue";

const routes = [
    { path: '/', name: 'Home', component: Home},
    { path: '/greeting', name: 'Greeting', component: Greeting},
    { path: '/checkbox', name: 'Checkbox', component: CheckboxExample},
    { path: '/event', name: 'DynamicEvent', component: DynamicEventExample},
    { path: '/login', name: 'Login', component: LoginModal},
    { path: '/signup', name: 'Signup', component: Signup} // 회원 가입 라우트 추가

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router