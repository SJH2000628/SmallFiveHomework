var app = new Vue({
    el: '#app',
    data: {
        selectMainPage: '',
        menus:[
            { 
                    name: "商品管理", index: "1", icon: 'el-icon-goods',menus:[
                    { name: "商品列表", index: "1-1"}
                ] 
            },
            {
                name: "客户管理", index: "2", icon: 'el-icon-s-custom', menus: [
                    { name: "客户列表", index: "2-1" }
                ]  
            },
            {
                name: "销售管理", index: "3", icon: 'el-icon-s-order', menus: [
                    { name: "订单列表", index: "3-1" },
                    { name: "退货列表", index: "3-2" }
                ]
            },
            {
                name: "用户管理", index: "4", icon: 'el-icon-user', menus: [
                    { name: "用户列表", index: "4-1" }
                ] 
            }
        ]
    },
    methods:{
        // handleOpen(){
        //     console.log('open click');
        // },
        // handleClose(){ 
        //     console.log('close click');
        // },
        handleMenuItemSelect(index,indexPath){
            console.log('menu item click',index,indexPath);
            app.selectMainPage = index;
        }
    }
})