var app = new Vue({
    el: '#app',
    data: {
        myProducts:[]
    },
    mounted(){
        console.log('view mounted');
        var myShoppingCart = localStorage['myShoppingCart'];
        this.myProducts = JSON.parse(myShoppingCart);
        console.log(myShoppingCart);
    }
})