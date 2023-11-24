<script lang="ts" setup>
import {onMounted, ref} from 'vue'
  import axios from 'axios';


  const data = {
    tableData: [],
    quary: {
      page: 1,
      size: 6
    },
    total: 0
  }
  // const tableDate = []
  // const quary = {
  //   page:1,
  //   size:6
  // }
  // const total:0


  const currentPage1 = ref(5)
  const currentPage2 = ref(5)
  const currentPage3 = ref(5)
  const currentPage4 = ref(4)
  const pageSize2 = ref(100)
  const pageSize3 = ref(100)
  const pageSize4 = ref(100)
  const small = ref(false)
  const background = ref(false)
  const disabled = ref(false)


  const getList = () => {
    axios.get('http://localhost:3307/user/getList', {
      params: {
        page: data.quary.page,
        size: data.quary.size
      }
    }).then((resp) => {
      console.log(resp);
    })
  }
  const handleSizeChange = (val: number) => {
    console.log(`每页 ${val} 条 `)
  }
  const handleCurrentChange = (val: number) => {
    console.log(`当前页: ${val}`)
  }
  onMounted()

</script>

<template>
  <div>
    <el-table :data="data.tableData" style="width: 100%">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
    </el-table>
  </div>

  <div class="demo-pagination-block">
    <div class="demonstration">All combined</div>
    <el-pagination
        v-model:current-page="this.data.quary.page"
        v-model:page-size="this.data.quary.size"
        :page-sizes="[5, 10, 20, 30]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.data.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>