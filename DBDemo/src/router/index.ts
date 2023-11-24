//这个文件是当前的路由模块

import {createRouter, createWebHashHistory} from 'vue-router';
// @ts-ignore
import HOME from "../components/HOME.vue";
// @ts-ignore
import BLOG from "../components/BLOG.vue";
// @ts-ignore
import Crud from "../views/Crud1.vue";

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
        },
        {
            path:"/Crud1",
            component:Crud
        },
        {
            path:"/",
            component:Crud
        }
    ]
});

export default router;
