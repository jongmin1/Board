<template>
  <div>
    <h1>{{ isEdit ? "게시글 수정" : "게시글 작성" }}</h1>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="title">제목:</label>
        <input id="title" v-model="board.title" required />
      </div>
      <div>
        <label for="content">내용:</label>
        <textarea id="content" v-model="board.content" required></textarea>
      </div>
      <div>
        <label for="author">작성자:</label>
        <input id="author" v-model="board.author" required />
      </div>
      <button type="submit">{{ isEdit ? "수정" : "작성" }}</button>
      <button type="button" @click="cancel">취소</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { createBoard, getBoardById, updateBoard } from "../api/board";

interface Board {
  id?: number;
  title: string;
  content: string;
  author: string;
}

const route = useRoute();
const router = useRouter();
const board = ref<Board>({
  title: "",
  content: "",
  author: "",
});
const isEdit = ref(false);

const fetchBoard = async () => {
  try {
    const id = Number(route.params.id);
    const response = await getBoardById(id);
    if (response) {
      board.value = response;
    }
  } catch (error) {
    console.error("게시글 조회 실패:", error);
  }
};

const handleSubmit = async () => {
  try {
    if (isEdit.value) {
      await updateBoard(Number(route.params.id), board.value);
    } else {
      await createBoard(board.value);
    }
    router.push("/");
  } catch (error) {
    console.error("게시글 저장 실패:", error);
  }
};

const cancel = () => {
  router.push("/");
};

onMounted(async () => {
  if (route.params.id) {
    isEdit.value = true;
    await fetchBoard();
  }
});
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
}
div {
  margin-bottom: 1rem;
}
label {
  display: block;
  font-weight: bold;
}
input,
textarea {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
}
button {
  margin-right: 1rem;
}
</style>
