<template>
  <div id="pageSequence">
    <el-row class="page-sequence">
      <el-col :span="12"><span>总</span><span
        class="ps-count">{{data.allCount}}</span><span>条</span>，<span>每页</span><span
        class="ps-count">{{data.pageSize}}</span><span>条</span></el-col>
      <el-col :span="12" style="text-align: right;padding-right: 4px;display: flex;justify-content: flex-end;">
        <div class="ps-basic ps-prev" @click="pagePrev"><i class="el-icon-arrow-left"></i></div>
        <div class="ps-basic ps-item" v-bind:class="{'ps-item-active':page===currentPageNumber}"
             @click="pageChange(page)" v-for="page in pageCount"><span>{{page}}</span></div>
        <div class="ps-basic ps-next" @click="pageNext"><i class="el-icon-arrow-right"></i></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  /**
   * data结构:
   * allCount
   *
   */
  export default {
    name: 'PageSequence',
    data() {
      return {
        pageCount: 1,
        currentPageNumber: 1
      }
    },
    mounted() {
      if (this.data.allCount > 0) {
        let isLastPage = this.data.allCount % this.data.pageSize > 0
        let computedPages = this.data.allCount / this.data.pageSize
        computedPages = isLastPage ? (computedPages + 1) : computedPages
        this.pageCount = parseInt(String(computedPages))
      }
      this.$emit('pageChange', this.currentPageNumber)
    },
    methods: {
      pageChange(pageNumber) {
        this.currentPageNumber = pageNumber
        this.$emit('pageChange', pageNumber)
      },
      pagePrev() {
        if (this.currentPageNumber >= 2) {
          this.currentPageNumber = this.currentPageNumber - 1
        }
        this.$emit('pageChange', this.currentPageNumber)
      },
      pageNext() {
        let nextPageNumber = this.currentPageNumber + 1
        if (nextPageNumber <= this.pageCount) {
          this.currentPageNumber = nextPageNumber
        }
        else {
          this.currentPageNumber = this.pageCount
        }
        this.$emit('pageChange', this.currentPageNumber)
      }
    },
    props: {
      data: {
        type: Object,
        default: function () {
          return {
            allCount: 0,
            pages: [],
            pageCount: 1,
            pageNumber: 1,
            pageSize: 50
          }
        }
      }
    }
  }
</script>
<style lang="stylus">
  #pageSequence
    width 100%
    padding-top 4px
    padding-bottom 4px
    .page-sequence
      width 100%
      padding 0
      margin 0
    .ps-basic
      width 20px
      height 22px
      background-color white
      border 1px #DCDFE6 solid
      text-align center
      color #606266
      cursor pointer
    .ps-basic:hover
      border 1px rgba(64, 158, 255, .5) solid
    .ps-prev
      font-size 14px
    .ps-next
      font-size 14px
      margin-left -1px
    .ps-count
      padding-left 2px
      padding-right 2px
    .ps-item:first-child
      margin-left 0
    .ps-item
      margin-left -1px
      font-size 13px
      span
        line-height 1.5
    .ps-item-active
      background-color #409EFF
      border 1px #409EFF solid
      color white


</style>
