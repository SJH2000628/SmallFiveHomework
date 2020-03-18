Vue.component('jc-return-search-page', {
    template: `
     <div id="app">
        <el-input v-model="returnId" placeholder="请输入退货编号"></el-input>
        <el-input v-model="orderId" placeholder="请输入订单编号"></el-input>
        <el-input v-model="customerName" placeholder="请输入用户名称"></el-input>
        <el-input v-model="productCode" placeholder="请输入商品编号"></el-input>
        <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
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
            <el-table-column prop="returnId" label="退货Id">
            </el-table-column>
            <el-table-column prop="orderId" label="订单Id">
            </el-table-column>
            <el-table-column prop="customerName" label="客户姓名">
            </el-table-column>
            <el-table-column prop="productCode" label="商品代号">
            </el-table-column>
            <el-table-column prop="productName" label="商品名称">
            </el-table-column>
            <el-table-column prop="status" label="状态">
                <template slot-scope="scope">
                    {{statuses[scope.row.status].label}}
                </template>
            </el-table-column>
            <el-table-column label="申请日期">
                <template slot-scope="scope">
                    {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
                </template>
            </el-table-column>
            <el-table-column label="修改日期">
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
            returnId: '',
            orderId: '',
            customerName: '',
            productCode: '',
            productName: '',
            startTimestamp: '',
            endTimestamp: '',
            selectedStatus: '',
            statuses: [
                { value: 0, label: '待处理' },
                { value: 1, label: '待取货' },
                { value: 2, label: '正在处理' },
                { value: 3, label: '完成' },
                { value: 4, label: '拒绝' }
            ]
        }
        
    },
    mounted() {
        console.log('view mounted');
        this.searchReturn();
    },
    methods: {
        handleFuzzyClick() {
            console.log('fuzzy click')
            this.pageNum = 1;
            this.searchReturn();
        },
        handleClearClick() {
            console.log('clear click')
            this.returnId = '';
            this.orderId = '';
            this.customerName = '';
            this.productCode = '';
            this.productName = '';
            this.startTimestamp = '';
            this.endTimestamp = '';
            this.selectedStatus = '';
        },
        handlePageChange(val) {
            console.log('page changed', val);
            this.pageNum = val;
            this.searchReturn();
        },
        searchReturn() {
            axios.get('/return/search', {
                params: {

                    returnId: this.returnId,
                    orderId: this.orderId,
                    customerName: this.customerName,
                    productCode: this.productCode,
                    productName: this.productName,
                    startTimestamp: this.startTimestamp ? this.startTimestamp.getTime() : '',
                    endTimestamp: this.endTimestamp ? this.endTimestamp.getTime() : '',
                    status: this.selectedStatus,
                    pageNum: this.pageNum

                }
            })
                .then((response) =>{
                    console.log(response);
                    this.pageInfo = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }
})