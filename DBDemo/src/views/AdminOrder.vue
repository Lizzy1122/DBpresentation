<!--<script>-->
<!--export default {-->
<!--  name: 'AdminOrder',-->
<!--  data() {-->
<!--    return {-->
<!--      tableData: [],-->
<!--      quary: {-->
<!--        page: 1,-->
<!--        size: 6,-->
<!--        // username: '',-->
<!--        // gender: '',-->
<!--        // addr: ''-->
<!--      },-->
<!--      total: 0,-->
<!--      dialogFormVisible: false,-->
<!--      form: {-->
<!--        // id: '',-->
<!--        // username: '',-->
<!--        // gender: '',-->
<!--        // addr: ''-->
<!--      },-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    this.getList();-->
<!--  },-->
<!--  methods: {-->
<!--    getList() {-->
<!--      this.axios.get('http://localhost:3312/user/getList', {-->
<!--        params: {-->
<!--          page: this.quary.page,-->
<!--          size: this.quary.size,-->
<!--          // username: this.quary.username,-->
<!--          // gender: this.quary.gender,-->
<!--          // addr: this.quary.addr-->
<!--        }-->
<!--      }).then((resp) => {-->
<!--        console.log(resp);-->
<!--        this.tableData = resp.data.content.list;-->
<!--        this.total = resp.data.content.total;-->
<!--      }).catch((error) => {-->
<!--        console.error('Error fetching data:', error);-->
<!--      })-->
<!--    },-->
<!--    handleSizeChange(val) {-->
<!--      console.log(`每页 ${val} 条 `);-->
<!--      this.quary.size = val;-->
<!--      this.getList();-->
<!--    },-->
<!--    handleCurrentChange(val) {-->
<!--      console.log(`当前页: ${val}`)-->
<!--      this.quary.page = val;-->
<!--      this.getList();-->
<!--    },-->
<!--    handleDelete(scope) {-->
<!--      console.log(`删除：${scope.row.id}`);-->
<!--      let id = scope.row.id;-->
<!--      this.axios.delete('http://localhost:3312/user/Delete/' + id)-->
<!--          .then((resp) => {-->
<!--            //let data =resp.data;-->
<!--            if (resp.data.success) {-->
<!--              this.getList();-->
<!--              this.$message({-->
<!--                message: `成功删除：${scope.row.id}`,-->
<!--                type: 'success',-->
<!--              })-->
<!--            }-->
<!--          })-->
<!--    },-->
<!--    handleCreate() {-->
<!--      this.form = {};//清空表单-->
<!--      this.dialogFormVisible = true;//true显示输入框，false隐藏输入框-->
<!--    },-->
<!--    handleEdit(row) {-->
<!--      this.form = row;-->
<!--      this.dialogFormVisible = true;-->
<!--    },-->
<!--    SubmitFrom() {-->
<!--      this.axios.post('http://localhost:3312/user/Create/', this.form)-->
<!--          .then((resp) => {-->
<!--            let data = resp.data;-->
<!--            if (data.success) {-->
<!--              this.dialogFormVisible = false;-->
<!--              this.form = {};//清空表单-->
<!--              //this.getList();//初始化查询-->
<!--              this.$message({-->
<!--                message: `操作成功`,-->
<!--                type: 'success',-->
<!--              })-->
<!--            }-->
<!--          })-->
<!--    }-->

<!--  }-->
<!--}-->

<!--</script>-->

<!--<template>-->
<!--  <div class="upperdiv">-->
<!--    <el-input v-model="quary.username" style="width: 200px" placeholder="请输入姓名"/>-->
<!--    <el-input v-model="quary.gender" style="width: 200px" placeholder="请输入性别"/>-->
<!--    <el-input v-model="quary.addr" style="width: 200px" placeholder="请输入地址"/>-->
<!--    <el-button @click="getList()" type="primary">查询</el-button>-->
<!--    <el-button @click="handleCreate()" type="primary">新增</el-button>-->
<!--  </div>-->

<!--  <div class="middlediv">-->
<!--    <el-table :data="this.tableData" style="width: 100%">-->
<!--      <el-table-column prop="username" label="姓名" width="180"/>-->
<!--      <el-table-column prop="gender" label="性别" width="180"/>-->
<!--      <el-table-column prop="addr" label="地址" width="180"/>-->

<!--      <el-table-column label="操作">-->
<!--        <template #default="scope">-->
<!--          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>-->
<!--          <el-button size="small" type="danger" @click="handleDelete(scope)">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--    </el-table>-->
<!--  </div>-->

<!--  <div class="demo-pagination-block">-->
<!--    <div class="demonstration"></div>-->
<!--    <el-pagination-->
<!--        v-model:current-page="quary.page"-->
<!--        v-model:page-size="quary.size"-->
<!--        :page-sizes="[5, 10, 20, 30]"-->
<!--        layout="total, sizes, prev, pager, next, jumper"-->
<!--        :total="this.total"-->
<!--        @size-change="handleSizeChange"-->
<!--        @current-change="handleCurrentChange"-->
<!--    />-->
<!--  </div>-->

<!--  <el-dialog v-model="dialogFormVisible" title="新增表单">-->
<!--    <el-form :model="form">-->
<!--      <el-form-item label="姓名">-->
<!--        <el-input v-model="form.username" autocomplete="off"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="性别">-->
<!--        <el-input v-model="form.gender" autocomplete="off"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="地址">-->
<!--        <el-input v-model="form.addr" autocomplete="off"/>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
<!--    <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="dialogFormVisible = false">取消</el-button>&ndash;&gt;-->
<!--        <el-button type="primary" @click="SubmitFrom()">确定</el-button>-->
<!--      </span>-->
<!--    </template>-->

<!--  </el-dialog>-->

<!--</template>-->


<!--<style scoped>-->
<!--.upperdiv {-->
<!--  width: 1000px;-->
<!--  height: 50px;-->
<!--  display: inline-block;-->

<!--}-->

<!--.middlediv {-->
<!--  width: 70%;-->
<!--  height: 50%;-->
<!--}-->

<!--.demonstration {-->
<!--  margin-bottom: 16px;-->
<!--}-->
<!--</style>-->
<template></template>