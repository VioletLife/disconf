<template>
  <div id="userPermission">
    <page-sequence :data="page" @pageChange="pageChange"></page-sequence>
  </div>
</template>
<script>
  export default {
    name: 'UserPermission',
    data() {
      return {
        page: {
          allCount: 112,
          pages: [],
          pageCount: 1,
          pageNumber: 1,
          pageSize: 50
        }
      }
    },
    methods: {
      pageChange(pageNumber) {
        console.info(pageNumber)
      }
    }
  }
</script>
<style lang="stylus">
  #userPermission
    width 100%
</style>
