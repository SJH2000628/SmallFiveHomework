var app = new Vue({
    el: '#app',
    data: {
        administratorId:'',
        username:'',
        realName:'',
        email:'',
        avatarUrl:'',
        password:'',
        selectedStatus: 1,
        statuses: [
            { value: 0, label: '禁用' },
            { value: 1, label: '启用' },
        ],
    },
    mounted() {
        console.log('view mounted');
        var url = new URL(location.href);
        this.administratorId = url.searchParams.get("administratorId");
        if (!this.administratorId) {
            alert('administratorId id Null');
            return;
        }
        this.getAdministratorById();
    },
    methods: {
        handleUpdateClick() {
            console.log('create click');
            this.updateAdministrator();
        },
        updateAdministrator() {
            axios.post('/administrator/update', {
                administratorId:this.administratorId,
                username:this.username,
                password:this.password,
                realName:this.realName,
                email:this.email,
                avatarUrl:this.avatarUrl,
                status: this.selectedStatus,
            })
                .then(function (response) {
                    console.log(response);
                    alert('更新成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getAdministratorById() {
            axios.get('/administrator/getById', {
                params: {
                    administratorId: this.administratorId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var administrator = response.data;
                    app.administratorId = administrator.administratorId;
                    app.username = administrator.username;
                    app.realName = administrator.realName;
                    app.email = administrator.email;
                    app.avatarUrl = administrator.avatarUrl;
                    app.selectedStatus = administrator.status;

                })
                .catch(function (error) {
                    console.log(error);
                })

        }
    }
})