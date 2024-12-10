import { createRouter, createWebHistory } from 'vue-router';
import BoardList from '../views/BoardList.vue';
import BoardDetail from '../views/BoardDetail.vue';
import BoardForm from '../views/BoardForm.vue';

const routes = [
  { path: '/', component: BoardList },
  { path: '/board/:id', component: BoardDetail },
  { path: '/create', component: BoardForm },
  { path: '/edit/:id', component: BoardForm },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
