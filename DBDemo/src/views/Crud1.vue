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
      this.axios.get('http://localhost:5566/user/getList', {
        params: {
          page: this.quary.page,
          size: this.quary.size
        }
      }).then((resp) => {
        console.log(resp);
        this.tableData = resp.data.content.list;
        this.total = resp.data.content.total;
      }).catch((error) => {
        console.error('Error fetching data:', error);
      })
    },
    handleSizeChange(val){
      console.log(`每页 ${val} 条 `);
      this.quary.size = val;
      this.getList();
    },
    handleCurrentChange(val){
      console.log(`当前页: ${val}`)
      this.quary.page = val;
      this.getList();
    }
  }
}

</script>

<template>
  <div>
    <el-table :data="this.tableData" style="width: 100%">
      <el-table-column prop="username" label="姓名" width="180" />
      <el-table-column prop="gender" label="性别" width="180" />
      <el-table-column prop="addr" label="地址" />
    </el-table>
  </div>

  <div class="demo-pagination-block">
    <div class="demonstration">All combined</div>
    <el-pagination
        v-model:current-page="quary.page"
        v-model:page-size="quary.size"
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