<template>
  <div id="app">
    <transition name="fade" >
      <router-view/>
    </transition>
  </div>
</template>
<style>
*{
  padding:0px;
  margin:0px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  padding:0px;
  margin:0px;
}
</style>
