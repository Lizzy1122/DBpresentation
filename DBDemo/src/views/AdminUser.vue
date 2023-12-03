<script>
export default {
  name: "AdminUser",
  data() {
    return {
      tableData: [],
      quary: {
        page: 1,
        size: 6,
        username: "",
        address: "",
        email: "",
        phone: "",
        usertype: null,
      },
      options: [
        {
          usertype: null,
          name: "全部",
        },
        {
          usertype: 1,
          name: "买家",
        },
        {
          usertype: 2,
          name: "卖家",
        },
        {
          usertype: 3,
          name: "管理员",
        },
      ],
      total: 0,
      dialogFormVisible: false,
      form: {
        userid: "",
        username: "",
        address: "",
        email: "",
        phone: "",
        usertype: null,
      },
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      this.axios
        .get("http://localhost:3312/user/getList", {
          params: {
            page: this.quary.page,
            size: this.quary.size,
            username: this.quary.username,
            address: this.quary.address,
            email: this.quary.email,
            phone: this.quary.phone,
            usertype: this.quary.usertype,
          },
        })
        .then((resp) => {
          console.log(resp);
          this.tableData = resp.data.content.list;
          this.total = resp.data.content.total;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条 `);
      this.quary.size = val;
      this.getList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.quary.page = val;
      this.getList();
    },
    handleDelete(scope) {
      console.log(`删除：${scope.row.username}`);
      console.log(scope);
      let userid = scope.row.userid;
      this.axios
        .delete("http://localhost:3312/user/Delete/" + userid)
        .then((resp) => {
          //let data =resp.data;
          if (resp.data.success) {
            this.getList();
            this.$message({
              message: `成功删除：${scope.row.username}`,
              type: "success",
            });
          }
        });
    },
    handleCreate() {
      this.form = {}; //清空表单
      this.dialogFormVisible = true; //true显示输入框，false隐藏输入框
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    SubmitFrom() {
      this.axios
        .post("http://localhost:3312/user/Create/", this.form)
        .then((resp) => {
          let data = resp.data;
          if (data.success) {
            this.dialogFormVisible = false;
            this.form = {}; //清空表单
            //this.getList();//初始化查询
            this.$message({
              message: `操作成功`,
              type: "success",
            });
          }
        });
    },
  },
};
</script>

<template>
  <div style="display: flex; flex-direction: column">
    <div class="navigator">
      <router-link class="router-link" to="./AdminOrder">
        <pre>管理订单  </pre>
      </router-link>
      <router-link class="router-link" to="./AdminUser">
        <pre>管理用户  </pre>
      </router-link>
      <router-link class="router-link" to="./AdminProduct">
        <pre>管理商品  </pre>
      </router-link>
      <router-link class="router-link" to="./AdminCart">
        <pre>管理购物车  </pre>
      </router-link>
    </div>

    <div class="upperdiv" style="margin-top: 5px">
      <el-input
        v-model="quary.username"
        style="width: 150px"
        placeholder="请输入姓名"
      />
      <el-input
        v-model="quary.address"
        style="width: 150px"
        placeholder="请输入地址"
      />
      <el-input
        v-model="quary.email"
        style="width: 150px"
        placeholder="请输入邮箱"
      />
      <el-input
        v-model="quary.phone"
        style="width: 150px"
        placeholder="请输入电话"
      />
      <el-select
        v-model="quary.usertype"
        class="m-2"
        placeholder="请选择用户类型"
      >
        <el-option
          v-for="option in options"
          :label="option.name"
          :value="option.usertype"
        />
      </el-select>
      <!--    <el-input v-model="quary.usertype" style="width: 150px" placeholder="请输入用户类型"/>-->

      <el-button @click="getList()" type="primary">查询</el-button>
      <el-button @click="handleCreate()" type="primary">新增</el-button>
    </div>

    <div class="middlediv" style="width: 100%">
      <el-table
        :data="this.tableData"
        stripe="true"
        :header-cell-style="{
          backgroundColor: 'aliceblue',
          fontWeight: 'bold',
          color: '#666',
        }"
        style="width: 100%"
      >
        <el-table-column prop="username" label="姓名" width="180" />
        <el-table-column prop="address" label="地址" width="180" />
        <el-table-column prop="email" label="邮箱" width="180" />
        <el-table-column prop="phone" label="电话" width="180" />
        <el-table-column prop="usertype" label="用户类型" width="180" />

        <el-table-column style="width: 200px" label="操作">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)"
              >编辑</el-button
            >
            <el-button size="small" type="danger" @click="handleDelete(scope)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="demo-pagination-block">
      <div class="demonstration"></div>
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

    <el-dialog v-model="dialogFormVisible" title="新增表单">
      <el-form :model="form">
        <el-form-item label="姓名">
          <el-input v-model="form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="用户类型">
          <el-input v-model="form.usertype" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="SubmitFrom()">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.upperdiv {
  width: 1500px;
  height: 50px;
  display: inline-block;
}

.middlediv {
  height: 50%;
  width: 100% !important;
}

.demonstration {
  margin-bottom: 16px;
}
.container {
  align-items: center;
  justify-content: center;
}
.navigator {
  background-color: #f1f1f1;
  border: 1px solid #ccc;
  padding: 10px;
  float: left;
  clear: both;
}

.content {
  background-color: #f1f1f1;
  border: 1px solid #ccc;
  padding: 10px;
  float: left;
  clear: both;
}
.middlediv[data-v-c77d6d5f] {
  width: 100%;
  height: 50%;
}
.router-link {
  font-size: large;
  color: white;
  text-decoration: none;
}
.router-link pre {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  padding: 0;
}
.navigator {
  background-color: rgb(60, 60, 198);
  display: flex;
  flex-direction: row;
}
</style>
