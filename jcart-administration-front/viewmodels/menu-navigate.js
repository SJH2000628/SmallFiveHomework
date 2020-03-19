var app = new Vue({
    router: router,
    el: '#app',
    data: {
        // jcProductId:'',
        // selectMainPage: '',
        menus:[
            { 
                    name: "商品管理", index: "1", icon: 'el-icon-goods',menus:[
                    { name: "商品列表", index: "1-1", route:'/product/search'},
                ] 
            },
            {
                name: "客户管理", index: "2", icon: 'el-icon-s-custom', menus: [
                    { name: "客户列表", index: "2-1", route: '/customer/search'}
                ]  
            },
            {
                name: "销售管理", index: "3", icon: 'el-icon-s-order', menus: [
                    { name: "订单列表", index: "3-1", route: '/order/search'},
                    { name: "退货列表", index: "3-2", route: '/return/search' }
                ]
            },
            {
                name: "用户管理", index: "4", icon: 'el-icon-user', menus: [
                    { name: "用户列表", index: "4-1", route: '/administrator/index'}
                ] 
            }
        ]
    },
    methods:{

    }
})