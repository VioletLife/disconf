import PageSequence from '@/components/PageSequence'

let _pageSequence = {
  installTo: function (Vue) {
    Vue.component('page-sequence', PageSequence)
  }
}

export default _pageSequence
