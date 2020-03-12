var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1,
        productCode:'',
        ProductName: '',
        price: '',
        stockQuantity: '',
        selectedStatus: '',
        statuses:[
            { value: 0, label: '下架' },
            { value: 1, label: '上架' },
            { value: 2, label: '待审核' }
        ]
    },
    mounted(){
        console.log('view mounted');
        this.searchProduct();
    },
    methods:{
        handleFuzzyClick(){
            console.log('fuzzy click')
            this.pageNum = 1;
            this.searchProduct();
        },
        handleClearClick(){
            console.log('clear click')
            this.productCode = '';
            this.ProductName = '';
            this.price = '';
            this.stockQuantity = '';
            this.selectedStatus = '';
        },
        handlePageChange(val){
            console.log('page change');
            this.pageNum = val;
            this.searchProduct();
        },
        searchProduct(){
            axios.get('/product/search', {
                params: {
                    productCode: this.productCode,
                    ProductName: this.ProductName,
                    price: this.price,
                    stockQuantity: this.stockQuantity,
                    status: this.selectedStatus,
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