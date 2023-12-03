<script>
export default {
  name: 'AdminProduct',
  data() {
    return {
      tableData: [],//所有数据
      pageNum: 1,   //当前页码
      pageSize: 5,  //每页显示个数
      UserID:'',
      ProductID:'',
      ProductName: '',
      Description: '',
      total: 0,
      FormVisible: false,
      form: {
        productID: '',
        productName: '',
        description: '',
        price: '',
        stockQuantity: '',
        properties: '',
        productReview: '',
        productImages: '',
      },
      rules: {
        productName: [
          {required: true, message: "请输入商品名", trigger: 'blur'},
        ],
      },
      ids: [],

    }
  },

  mounted() {
    this.load()

  },
  methods: {

    handleAddToCart(id) {
      this.UserID = this.$route.query.userid;
      this.AddToCartQuantity=1;
      this.ProductID=id;
      console.log(this.UserID);
      console.log(this.AddToCartQuantity);
      console.log(this.ProductID);

        this.axios.post('http://localhost:3312/product/AddProductToCart', {
          AddToCartQuantity: this.AddToCartQuantity,
          UserID: this.UserID,
          ProductID: this.ProductID,
        }).then(res => {
          if (res.data.code === '200') {
            this.$message.success('已加入购物车')
          } else {
            this.$message.error(res.data.msg)
          }

      })
    },

    load(pageNum) {//分页查询
      if (pageNum) this.pageNum = pageNum;

      this.axios.get('http://localhost:3312/product/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          ProductName: this.ProductName,
          Description: this.Description,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.data.records
        this.total = res.data.data.total
      }).catch(error => {
        console.error('API Request Failed:', error);
      });
    },
    reset() {
      this.ProductName = '';
      this.Description = ''
      this.load();
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {

          if (this.form.productID) {
            console.log(this.form, 'axios前');
            this.axios.put('http://localhost:3312/product/update', {
              productID: this.form.productID,
              productName: this.form.productName,
              description: this.form.description,
              price: this.form.price,
              stockQuantity: this.form.stockQuantity,
              properties: this.form.properties,
              productReview: this.form.productReview,
              productImages: this.form.productImages,
            }).then(res => {
              if (res.data.code === '200') {
                console.log(this.form, 'axios后');
                this.$message.success('保存成功')
                this.load(1)
                this.FormVisible = false
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            console.log(this.form.productName, 666)
            console.log(this.form.description, 666)
            console.log(this.form.productID, 666)
            this.axios.post('http://localhost:3312/product/add', {
              productName: this.form.productName,
              description: this.form.description,
              price: this.form.price,
              stockQuantity: this.form.stockQuantity,
              properties: this.form.properties,
              productReview: this.form.productReview,
              productImages: this.form.productImages,
            }).then(res => {
              if (res.data.code === '200') {
                this.$message.success('保存成功')
                this.load(1)
                this.FormVisible = false
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
          // this.axios({
          //   data:{
          //     productID:this.form.productID,
          //     productName:this.form.productName,
          //     description:this.form.description,
          //     price: this.form.price,
          //     stockQuantity: this.form.stockQuantity,
          //     properties: this.form.properties,
          //     productReview: this.form.productReview,
          //     productImages: this.form.productImages,
          //   },
          //   url:this.form.productID?'http://localhost:3312/product/update':'http://localhost:3312/product/add',
          //   method:this.form.productID?'PUT':'POST',
          //   // data:this.form
          //       //form 里好像没有code
          // }).then(res =>{
          //   if (res.data.code === '200'){
          //     this.$message.success('保存成功')
          //     this.load(1)
          //     this.FormVisible=false
          //   }else {
          //     this.$message.error(res.data.msg)
          //   }
          // })
        }
      })
    },

    handleCurrentChange(pageNum) {
      console.log(`当前页: ${pageNum}`)
      // this.pageNum = pageNum;
      this.load(pageNum);
    },
    handleAdd() {
      this.form = {};
      this.FormVisible = true;
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row)); //给form对象赋值
      console.log(this.form, 'handleEdit');//Edit按钮能拿到
      this.FormVisible = true;
    },
    handleDelete(id) {
      this.$confirm('您确认删除吗？', '确认删除', {type: "warning"}).then(resp => {
        this.axios.delete('http://localhost:3312/product/delete/' + id).then(res => {
          if (res.data.code === '200') {
            this.$message.success('删除成功')
            this.load(1)
            this.FormVisible = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {
      this.ids = rows.map(val => val.productID)
    },
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确认批量删除这些数据吗？', '确认删除', {type: "warning"}).then(resp => {
        this.axios.delete('http://localhost:3312/product/delete/batch/', {
          data: this.ids
        }).then(res => {
          if (res.data.code === '200') {
            this.$message.success('删除成功')
            this.load(1)
            this.FormVisible = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(() => {
      })
    },

  }
}

</script>

<template>
  <div class="container">
    <div class="navigator">
      <router-link to="./AdminOrder">管理订单|</router-link>
      <router-link to="./AdminUser">管理用户|</router-link>
      <router-link to="./AdminProduct">管理商品</router-link>
      <router-link to="./AdminCart">管理购物车</router-link>
    </div>

    <div class="upperdiv">
      <el-input v-model="ProductName" style="width: 200px" placeholder="请输入产品名"/>
      <el-input v-model="Description" style="width: 200px; margin: 0 5px;" placeholder="请输入产品描述"/>
      <el-button @click="load(1)" type="primary">查询</el-button>
      <el-button @click="reset()" type="info">重置</el-button>
      <el-button @click="handleAdd()" type="primary">新增</el-button>
      <el-button @click="delBatch()" type="danger">批量删除</el-button>
    </div>

    <div class="middlediv">
      <el-table :data="tableData" style="width: 100%" stripe
                :header-cell-style="{backgroundColor:'aliceblue', fontWeight:'bold',color:'#666'}"
                @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="productName" label="商品名" width="180"/>
        <el-table-column prop="description" label="商品描述" width="180"/>
        <el-table-column label="操作" key="slot">
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.productID)">删除</el-button>
            <el-button size="small" type="primary" @click="handleAddToCart(scope.row.productID)">加入购物车</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="demo-pagination-block">
      <div class="demonstration"></div>
      <el-pagination
          v-model:current-page="this.pageNum"
          v-model:page-size="this.pageSize"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="8"
          layout="total, prev, pager, next"
          :total="this.total"
          @current-change="handleCurrentChange"
      />
    </div>

    <el-dialog v-model="FormVisible" title="新增表单">
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item label="商品名" prop="productName">
          <el-input v-model="form.productName" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="商品描述" prop="description">
          <el-input v-model="form.description" autocomplete="off"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="FormVisible = false">取消</el-button>-->
          <el-button type="primary" @click="save()">确定</el-button>
        </span>
      </template>

    </el-dialog>
  </div>
</template>


<style scoped>
.upperdiv {
  width: 1000px;
  height: 50px;
  display: inline-block;
  margin: 10px;

}

.middlediv {
  width: 70%;
  height: 50%;
}

.demonstration {
  margin-bottom: 16px;
}

.demo-pagination-block {
  margin: 10px 0;
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
</style>

