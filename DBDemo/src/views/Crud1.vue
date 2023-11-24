<script>
export default {
  name:'curd',
  data(){
    return{
      tableData: [],
      quary: {
        page: 1,
        size: 6
      },
      total: 0
    }
  },
  mounted() {
    this.getList();
  },
  methods:{
    getList(){
      this.axios.get('http://localhost:3312/user/getList', {
        params: {
          page: this.quary.page,
          size: this.quary.size
        }
      }).then((resp) => {
        console.log(resp);
      })
    },
    handleSizeChange(val){
      console.log(`每页 ${val} 条 `)
    },
    handleCurrentChange(val){
      console.log(`当前页: ${val}`)
    }
  }
}

</script>

<template>
  <div>
    <el-table :data="this.tableData" style="width: 100%">
      <el-table-column prop="date" label="Date" width="180" />
      <el-table-column prop="name" label="Name" width="180" />
      <el-table-column prop="address" label="Address" />
    </el-table>
  </div>

  <div class="demo-pagination-block">
    <div class="demonstration">All combined</div>
    <el-pagination
        v-model:current-page="this.quary.page"
        v-model:page-size="this.quary.size"
        :page-sizes="[5, 10, 20, 30]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<style scoped>

</style>