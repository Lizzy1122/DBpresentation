<template>
  <div
    style="
      height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: #0f9876;
    "
  >
    <div
      style="
        display: flex;
        background-color: white;
        width: 50%;
        border-radius: 5px;
        overflow: hidden;
        height: 400px;
      "
    >
      <div style="flex: 1">
        <img src="../assets/image/register.png" alt="" style="width: 100%" />
      </div>
      <div style="flex: 1">
        <div
          style="
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
            margin-top: 80px;
          "
        >
          <p>用户登录</p>
        </div>
        <div style="text-align: center; margin: 20px 80px 20px 80px">
          <el-input v-model="username" placeholder="请输入账号" />
        </div>
        <div style="text-align: center; margin: 20px 80px 20px 80px">
          <el-input
            v-model="password"
            type="password"
            placeholder="请输入密码"
            show-password
          />
        </div>
        <div style="text-align: center; margin: 20px 80px 20px 80px">
          <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
              <span style="float: left">{{ item.label }}</span>
            </el-option>
          </el-select>
        </div>
        <div style="text-align: center">
          <el-button style="width: 220px" type="primary" @click="login"
            >登录</el-button
          >
        </div>
        <div style="text-align: left; margin: 20px 80px 20px 80px">
          还没有账号？请
          <span
            style="color: #0f9861; cursor: pointer"
            @click="$router.push('/register')"
            >注册</span
          >
        </div>
      </div>
    </div>
  </div>

  <div>hello</div>
</template>

<script lang="ts">
import axios from "axios"; // 引入axios
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      username: "",
      password: "",
      value: "",
      options: [
        {
          value: "1",
          label: "用户",
        },
        {
          value: "2",
          label: "商家",
        },
        {
          value: "0",
          label: "管理员",
        },
      ],
    };
  },
  methods: {
    login() {
      console.log("点击登录" + this.value);

      // 构造请求参数
      const loginData = {
        Username: this.username,
        Password: this.password,
        UserType: this.value,
      };

      // 发送登录请求
      axios.post("http://localhost:3312/login", loginData).then((response) => {
        // 处理登录成功的情况
        if (response.data.code == 200) {
          console.log("登录成功", response.data);
          ElMessage.success("登录成功");
          if (this.value == "0") this.$router.push("/AdminCart"); //不同用户跳转不同页面
          if (this.value == "1") this.$router.push("/AdminUser"); //1代表用户
          if (this.value == "2") {
            this.$router.push({
              path: "/seller",
              query: {
                Username: response.data.data.Username,
                UserType: response.data.data.UserType,
              },
            });
          }
        } else {
          ElMessage.error(response.data.msg);
          console.log("登录失败", response.data);
        }
        // 可以根据后端返回的数据进行相应的操作，例如存储 token、跳转页面等
      });
    },
    mounted() {
      // 不要在挂载时自动执行登录操作，让用户点击按钮时触发
      this.login();
    },
  },
};
</script>

<style>
/* 样式代码 */
</style>
