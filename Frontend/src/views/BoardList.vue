<template>
  <div>
    <h1>게시글 목록</h1>
    <div class="search-container">
      <input type="text" v-model="searchKeyword" placeholder="검색어 입력" />
      <button @click="search">검색</button>
      <button @click="createBoard">게시글 작성</button>
    </div>
    <ul>
      <li v-for="board in boards" :key="board.id">
        <router-link :to="`/board/${board.id}`">{{ board.title }}</router-link>
        <button @click="deleteBoard(board.id)">삭제</button>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getBoards, deleteBoard as deleteBoardApi } from "../api/board";

interface Board {
  id: number;
  title: string;
  content: string;
  author: string;
}

const router = useRouter();
const boards = ref<Board[]>([]);
const searchKeyword = ref("");

// 게시글 목록 조회
const fetchBoards = async () => {
  try {
    const response = await getBoards();
    boards.value = response;
  } catch (error) {
    console.error("게시글 목록을 불러오는데 실패했습니다:", error);
    alert("게시글 목록을 불러오는데 실패했습니다.");
  }
};

// 게시글 검색
const search = async () => {
  try {
    const response = await getBoards(searchKeyword.value);
    boards.value = response;
  } catch (error) {
    console.error("게시글 검색에 실패했습니다:", error);
    alert("게시글 검색에 실패했습니다.");
  }
};

// 게시글 작성 페이지로 이동
const createBoard = () => {
  router.push("/create");
};

// 게시글 삭제
const deleteBoard = async (id: number) => {
  try {
    if (confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
      await deleteBoardApi(id);
      await fetchBoards();
      alert("게시글이 삭제되었습니다.");
    }
  } catch (error) {
    console.error("게시글 삭제에 실패했습니다:", error);
    alert("게시글 삭제에 실패했습니다.");
  }
};

// 컴포넌트 마운트 시 게시글 목록 조회
onMounted(() => {
  fetchBoards();
});
</script>

<style scoped>
.search-container {
  margin: 20px 0;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #eee;
}

button {
  margin-left: 10px;
  padding: 5px 10px;
  background-color: #555454;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #343333;
}

a {
  text-decoration: none;
  color: #333;
}

a:hover {
  color: #666;
}
</style>
