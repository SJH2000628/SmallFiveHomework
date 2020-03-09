var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1
    },
mounted(){
    console.log('view mounted')
    this.getMyReturn();
},
    methods:{
        handlePageChange(val){
            console.log('page change',val);
            this.pageNum = val;
        },
        getMyReturn(){
            axios.get('/return/getList', {
                params: {
                    pageNum: this.pageNum
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                })
               
        }
    }
})