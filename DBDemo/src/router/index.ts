//这个文件是当前的路由模块

import {createRouter, createWebHashHistory} from 'vue-router';
// @ts-ignore
import HOME from "../components/HOME.vue";
// @ts-ignore
import BLOG from "../components/BLOG.vue";
// @ts-ignore
import AdminProduct from "../views/AdminProduct.vue";
// @ts-ignore
import AdminUser from "../views/AdminUser.vue";
// @ts-ignore
import AdminOrder from "../views/AdminOrder.vue";
// @ts-ignore
import AdminCart from "../views/AdminCart.vue";



const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/AdminUser",
            component:AdminUser
        },
        {
            path:"/AdminOrder",
            component:AdminOrder
        },
        {
            path:"/AdminProduct",
            component:AdminProduct
        },
        {
            path:"/AdminCart",
            component:AdminCart
        },
        {
            path:"/",
            component:AdminProduct
        }
    ]
});

export default router;
