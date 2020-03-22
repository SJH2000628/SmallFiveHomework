var app = new Vue({
    el: '#app',
    data: {
        email: '',
        loading:false,
        buttonEnable:true,
        counter:60
    },
    methods: {
        handleFindBackPwdClick() {
            console.log('find back pwd click');
            this.loading=true;
            this.buttonEnable = false;
            this.counter = 60;
            setInterval(function () {
                console.log('count down')
                app.counter--;
                if(app.counter<0){
                    app.buttonEnable = true;
                }
            }, 1000);
            this.getPwdResetCode();
        },
        getPwdResetCode() {
            axios.get('/administrator/getPwdResetCode', {
                params: {
                    email: this.email,
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.loading = false;
                    // alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})