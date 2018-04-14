<template>
  <div></div>
</template>
<script>

  export default {
    name: 'Index',
    data() {
      return {}
    },
    mounted() {
      /**
       * 重定向到登录页
       */
      window.location.href = this.$store.state.htmlPath.appLogin
    }
  }
</script>
<style lang="stylus">

</style>
