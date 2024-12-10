<template>
  <div id="app">
    <header>
      <h1>게시판</h1>
      <nav>
        <router-link to="/">홈</router-link>
      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <p>&copy; Good Bye 2024</p>
    </footer>
  </div>
</template>

<script lang="ts">
export default {
  name: "App",
};
</script>

<style scoped>
#app {
  text-align: center;
  max-width: 800px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}

header {
  background-color: #4caf50;
  color: white;
  padding: 1rem;
}

header nav {
  margin-top: 0.5rem;
}

header nav a {
  margin: 0 1rem;
  color: white;
  text-decoration: none;
}

header nav a:hover {
  text-decoration: underline;
}

main {
  padding: 2rem;
}

footer {
  margin-top: 2rem;
  color: #555;
}
</style>
