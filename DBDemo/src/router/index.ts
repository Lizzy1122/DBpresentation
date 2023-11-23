//这个文件是当前的路由模块
// import Vue from 'vue'
import {createRouter, createWebHashHistory} from 'vue-router';
import HOME from "../components/HOME.vue";
import BLOG from "../components/BLOG.vue";
// Vue.use()
const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/HOME",
            component:HOME
        },
        {
            path:"/BLOG",
            component:BLOG
        }
    ]
});

export default router;
