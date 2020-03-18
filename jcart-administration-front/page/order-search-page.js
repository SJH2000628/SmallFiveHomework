Vue.component('jc-order-search-page', {
    template: `
   <div id="app">
        <el-input v-model="orderId" placeholder="请输入订单Id"></el-input>
        <el-input v-model="customerName" placeholder="请输入客户姓名"></el-input>
        <el-input v-model="totalPrice" placeholder="请输入总价"></el-input>
        <br>
        <el-date-picker v-model="startTimestamp" type="date" placeholder="选择开始日期"></el-date-picker>--
        <el-date-picker v-model="endTimestamp" type="date" placeholder="选择结束日期"></el-date-picker>
        <br>
        <el-select v-model="selectedStatus" placeholder="请选择状态">
            <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <br>
        <el-button type="primary" @click="handleFuzzyClick">查询</el-button>
        <el-button type="primary" @click="handleClearClick">清空条件</el-button>
        <el-table :data="pageInfo.list" style="width: 100%">
            <el-table-column prop="orderId" label="订单Id">
            </el-table-column>
            <el-table-column prop="customerName" label="客户姓名">
            </el-table-column>
            <el-table-column label="状态">
                <template slot-scope="scope">
                    {{statuses[scope.row.status].label}}
                </template>
            </el-table-column>
            <el-table-column prop="totalPrice" label="总价">
                <template slot-scope="scope">
                    {{scope.row.totalPrice.toFixed(2)}}
                </template>
            </el-table-column>
            <el-table-column prop="createTimestamp" label="下单时间">
                <template slot-scope="scope">
                    {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
                </template>
            </el-table-column>
            <el-table-column prop="updateTimestamp" label="修改时间">
                <template slot-scope="scope">
                    {{(new Date(scope.row.updateTimestamp)).toLocaleString()}}
                </template>
            </el-table-column>
        </el-table>

        <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
        </el-pagination>
    </div>
    `,
    data() {
        return{
            pageInfo: '',
            pageNum: 1,
            orderId: '',
            customerName: '',
            totalPrice: '',
            startTimestamp: '',
            endTimestamp: '',
            selectedStatus: '',
            statuses: [
                { value: 0, label: '待处理' },
                { value: 1, label: '处理中' },
                { value: 2, label: '待发货' },
                { value: 3, label: '已发货' },
                { value: 4, label: '待签收' },
                { value: 5, label: '已签收' },
                { value: 6, label: '待支付' },
                { value: 7, label: '已支付' },
                { value: 8, label: '取消' },
                { value: 9, label: '拒绝' },
                { value: 10, label: '完成' },
                { value: 11, label: '待评价' },
                { value: 12, label: '已评价' }
            ]
        }
       
    },
    mounted() {
        console.log('view mounted');
        this.searchOrder();
    },
    methods: {
        handleFuzzyClick() {
            console.log('fuzzy click')
            this.pageNum = 1;
            this.searchOrder();
        },
        handleClearClick() {
            console.log('clear click')
            this.orderId = '';
            this.customerName = '';
            this.totalPrice = '';
            this.startTimestamp = '';
            this.endTimestamp = '';
            this.selectedStatus = '';
        },
        handlePageChange() {
            console.log('page changed', val);
            this.pageNum = val;
            this.searchOrder();
        },
        searchOrder() {
            axios.get('/order/search', {
                params: {
                    orderId: this.orderId,
                    customerName: this.customerName,
                    totalPrice: this.totalPrice,
                    startTimestamp: this.startTimestamp ? this.startTimestamp.getTime() : '',
                    endTimestamp: this.endTimestamp ? this.endTimestamp.getTime() : '',
                    status: this.selectedStatus,
                    pageNum: this.pageNum
                }
            })
                .then((response)=> {
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }
})