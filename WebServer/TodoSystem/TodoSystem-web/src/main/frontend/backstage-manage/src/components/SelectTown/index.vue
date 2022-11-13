<template>
  <!-- <div>  -->
  <el-cascader
    :options="options"
    :props="propsSet"
    :clearable="clearable"
    v-model="selectedOptions"
    @change="handleChange"
    style="width: 100%">
  </el-cascader>

</template>

<script>
import {townData} from "@/components/SelectTown/data";

export default {
  props: {
    isThreeLevel: {
      type: Boolean,
      default: false
    },
    address:{},
    propsSet:{
      type:Object,
      default:function() {
        return {
          value: 'text',
          label: 'text',
          children: 'children'
        }
      }
    },
    clearable:{
      type:Boolean,
      default:false
    }
  },
  watch: {
    address (val) {
      this.selectedOptions = val
    }
  },
  computed: {
  },
  data () {
    return {
      options: [],
      townData,
      selectedOptions: this.address
    }
  },
  methods: {
    handleChange (val) {
      this.$emit('getAddress', val)
    }
  },
  mounted(){
    setTimeout(()=>{
      const options = this.townData

      /*三级实现*/
      if (this.isThreeLevel) {
        options.forEach(item => {
          if (item['children']) {
            item['children'].forEach(item1 => {
              if (item1['children']) {
                item1['children'].forEach(item2 => {
                  delete item2['children']
                })
              }
            })
          }
        })
      }
      this.options = options
    },1000)
  }
  // created: function () {
  //   let getData = JSON.parse(this.townData)
  //   const t1 = new Date()  // t1 - 开始时间
  //   // 一级地址
  //   const arr1 = []
  //   for (const [k1, v1] of Object.entries(getData)) {
  //     const arr2 = []
  //     // 二级地址
  //     for (const [k2, v2] of Object.entries(v1)) {
  //       // 三级地址
  //       const arr3 = []
  //       for (const [k3, v3] of Object.entries(v2)) {
  //         // arr3.push({ text: k3 })
  //         // 四级地址
  //         const arr4 = []
  //         v3.map((v4, k4) => {
  //           arr4.push({ text: v4 })
  //         })
  //         arr4.length === 0
  //         ? arr3.push({ text: k3 })
  //         : arr3.push({ text: k3, children: arr4 })
  //       }
  //       arr3.length === 0
  //       ? arr2.push({ text: k2 })
  //       : arr2.push({ text: k2, children: arr3 })
  //     }
  //     arr2.length === 0
  //     ? arr1.push({ text: k1 })
  //     : arr1.push({ text: k1, children: arr2 })
  //   }
  //   this.options = arr1
  //   const t2 = new Date()  // t2 - 结束时间
  //   console.log(arr1);
  // }
}
</script>
