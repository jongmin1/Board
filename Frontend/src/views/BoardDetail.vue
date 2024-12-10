<template>
  <div>
    <h1>{{ board.title }}</h1>
    <p>{{ board.content }}</p>
    <p>작성자: {{ board.author }}</p>
    <button @click="editBoard">수정</button>
    <button @click="goBack">목록으로</button>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getBoardById } from "../api/board";

interface Board {
  id?: number;
  title: string;
  content: string;
  author: string;
}

const board = ref<Board>({
  title: "",
  content: "",
  author: "",
});
const route = useRoute();
const router = useRouter();

const fetchBoard = async () => {
  try {
    const id = Number(route.params.id);
    const response = await getBoardById(id);
    board.value = response;
  } catch (error) {
    console.error("게시글 조회 실패:", error);
  }
};

const editBoard = () => {
  router.push(`/edit/${route.params.id}`);
};

const goBack = () => {
  router.push("/");
};

onMounted(fetchBoard);
</script>
