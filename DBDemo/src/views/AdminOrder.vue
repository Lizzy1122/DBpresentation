<script>
import moment from 'moment';

export default {
  name: 'AdminOrder',
  data() {
    return {
      tableData: [],//所有数据
      pageNum: 1,   //当前页码
      pageSize: 5,  //每页显示个数
      OrderID:'',
      PurchaserID:'',
      ProductID:'',
      ShippingAddress:'',
      TotalAmount:'',
      UnitPrice:'',
      OrdersQuantity:'',
      total: 0,
      FormVisible: false,
      form: {
        orderID:'',
        purchaserID:'',
        productID:'',
        shippingAddress:'',
        totalAmount:'',
        unitPrice:'',
        ordersQuantity:'',
      },
      shortcuts: [
        {
          text: 'Today',
          value: new Date(),
        },
        {
          text: 'Yesterday',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            return date
          },
        },
        {
          text: 'A week ago',
          value: () => {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
            return date
          },
        },
      ],
      rules: {
        purchaserID: [{required: true, message: "请输入买家ID", trigger: 'blur'}],
        productID: [{required: true, message: "请输入商品ID", trigger: 'blur'}],
        shippingAddress: [{required: true, message: "请输入收货地址", trigger: 'blur'}],
      },
      defaultTime: new Date(2000, 1, 1, 12, 0, 0),
      ids:[],

    }
  },
  mounted() {
    // this.load(),
    this.seleteAll()
  },
  methods: {
    seleteAll(){
      this.axios.get('http://localhost:3312/purchase/selectAll').then(res =>{
        console.log(res)
        this.tableData = res.data.data
        this.total = 1
      }).catch(error => {
        console.error('API Request Failed:', error);
      });
    },
    formatDate(dateTime) {
      // 使用 moment.js 格式化日期时间
      return moment(dateTime).utcOffset(8).format('YYYY/MM/DD HH:mm:ss');
    },
    load(pageNum) {//分页查询
      if (pageNum) this.pageNum = pageNum;
      this.axios.get('http://localhost:3312/purchase/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          OrderID:this.OrderID,
          PurchaserID:this.PurchaserID,
          ProductID:this.ProductID,
          ShippingAddress:this.ShippingAddress,
          TotalAmount:this.TotalAmount,
          UnitPrice:this.UnitPrice,
          OrdersQuantity:this.OrdersQuantity,
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
      this.OrderID=''
      this.PurchaserID=''
      this.ProductID=''
      this.ShippingAddress=''
      this.TotalAmount=''
      this.UnitPrice=''
      this.OrdersQuantity=''
      this.seleteAll();
    },
    handleCurrentChange(pageNum){
      console.log(`当前页: ${pageNum}`)
      // this.pageNum = pageNum;
      this.load(pageNum);
    },
    handleSelectionChange(rows){
      this.ids = rows.map(val=>val.orderID)
    },
    handleAdd() {
      this.form = {};
      this.FormVisible = true;
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          if (this.form.orderID) {
            console.log(this.form, 'axios前');
            this.axios.put('http://localhost:3312/purchase/update', {
              orderID: this.form.orderID,
              purchaserID: this.form.purchaserID,
              productID: this.form.productID,
              shippingAddres: this.form.shippingAddres,
              totalAmount: this.form.totalAmount,
              unitPrice: this.form.unitPrice,
              ordersQuantity: this.form.ordersQuantity,
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
            this.$message("订单ID不存在")
            // console.log(this.form.addToCartQuantity, 666)
            // console.log(this.form.addToCartTime, 666)
            // console.log(this.form.userID, 666)
            // console.log(this.form.productID, 666)
            // this.axios.post('http://localhost:3312/purchase/add', {
            //   purchaserID: this.form.purchaserID,
            //   productID: this.form.productID,
            //   shippingAddres: this.form.shippingAddres,
            //   totalAmount: this.form.totalAmount,
            //   unitPrice: this.form.unitPrice,
            //   ordersQuantity: this.form.ordersQuantity,
            // }).then(res => {
            //   if (res.data.code === '200') {
            //     this.$message.success('保存成功')
            //     this.load(1)
            //     this.FormVisible = false
            //   } else {
            //     this.$message.error(res.data.msg)
            //   }
            // })
          }
        }
      })
    },
    delBatch(){
      if(!this.ids.length){
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确认批量删除这些数据吗？','确认删除',{type:"warning"}).then(resp=>{
        this.axios.delete('http://localhost:3312/purchase/delete/batch/',{
          data:this.ids
        }).then(res=>{
          if (res.data.code === '200'){
            this.$message.success('删除成功')
            this.seleteAll()
            // this.load(1)
            this.FormVisible=false
          }else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(()=>{})
    },
    handleEdit(row){
      this.form =JSON.parse(JSON.stringify(row)); //给form对象赋值
      console.log(this.form,'handleEdit');//Edit按钮能拿到
      this.FormVisible=true;
    },
    handleDelete(id){
      this.$confirm('您确认删除吗？','确认删除',{type:"warning"}).then(resp=>{
        this.axios.delete('http://localhost:3312/purchase/delete/'+id).then(res=>{
          if (res.data.code === '200'){
            this.$message.success('删除成功')
            this.seleteAll()
            // this.load(1)
            this.FormVisible=false
          }else {
            this.$message.error(res.data.msg)
          }
        })
      }).catch(()=>{})
    },

  },
}
</script>

<template>
  <div class="container">
  <div class="navigator">
    <router-link to="./AdminOrder">管理订单|</router-link>
    <router-link to="./AdminUser1">管理用户|</router-link>
    <router-link to="./AdminProduct">管理商品</router-link>
    <router-link to="./AdminCart">管理购物车</router-link>
  </div>
  <div class="upperdiv">
    <el-input v-model="OrderID" style="width: 200px; margin: 0 5px;" placeholder="请输入订单ID"/>
    <el-input v-model="PurchaserID" style="width: 200px; margin: 0 5px;" placehsolder="请输入买家ID"/>
    <el-input v-model="ProductID" style="width: 200px" placeholder="请输入商品ID"/>
    <el-input v-model="ShippingAddress" style="width: 200px" placeholder="请输入收货地址"/>

<!--    <el-date-picker class="demo-datetime-picker"-->
<!--                    v-model="AddToOrderTime"-->
<!--                    type="datetime"-->
<!--                    placeholder="选择日期时间"-->
<!--                    :shortcuts="shortcuts"-->
<!--                    style="width: 200px; margin: 0 5px;"-->
<!--                    format="YYYY/MM/DD HH:mm:ss"-->
<!--                    value-format="YYYY-MM-DD HH:mm:ss"-->
<!--    />-->
    <el-button @click="load(1)" type="primary">查询</el-button>
    <el-button @click="reset()" type="info">重置</el-button>
<!--    <el-button @click="handleAdd()" type="primary">新增</el-button>-->
    <el-button @click="delBatch()" type="danger">批量删除</el-button>
  </div>

  <div class="middlediv">
    <el-table :data="tableData" style="width: 100%" stripe
              :header-cell-style="{backgroundColor:'aliceblue', fontWeight:'bold',color:'#666'}"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="orderID" label="订单ID" width="180"/>
      <el-table-column prop="purchaserID" label="买家ID" width="180"/>
      <el-table-column prop="productID" label="商品ID" width="180"/>
      <el-table-column prop="shippingAddress" label="收货地址" width="180"/>
<!--      <el-table-column prop="addToOrderTime" label="订单时间" width="180">-->
<!--        <template v-slot="">-->
<!--          {{ formatDate(AddToCartTime) }}-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="操作" key="slot">
        <template #default="scope">
          <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.orderID)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <div class="lower">
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

  <el-dialog v-model="FormVisible" title="编辑表单">
    <el-form :model="form" :rules="rules" ref="formRef">

      <el-form-item label="买家ID" prop="PurchaserID">
        <el-input v-model="form.purchaserID" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="商品ID" prop="ProductID">
        <el-input v-model="form.productID" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="收货地址" prop="ShippingAddress">
        <el-input v-model="form.shippingAddress" autocomplete="off"/>
      </el-form-item>

<!--      <el-form-item label="添加到购物车时间" prop="AddToCartTime">-->
<!--        <el-date-picker class="demo-datetime-picker"-->
<!--                        v-model="form.addToCartTime"-->
<!--                        type="datetime"-->
<!--                        placeholder="选择日期时间"-->
<!--                        :shortcuts="shortcuts"-->
<!--                        style="width: 200px; margin: 0 5px;"-->
<!--                        format="YYYY/MM/DD HH:mm:ss"-->
<!--                        value-format="YYYY-MM-DD HH:mm:ss"-->
<!--        />-->
<!--      </el-form-item>-->


    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="FormVisible = false">取消</el-button>-->
          <el-button type="primary" @click="save()">确定</el-button>
        </span>
    </template>
  </el-dialog>
  </div>>
</template>

<style scoped>

.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-datetime-picker .block:last-child {
  border-right: none;
}

.demo-datetime-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
.container{
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