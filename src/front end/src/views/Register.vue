<template>
  <div>
    <v-symbol></v-symbol>
    <div class="register">
      <img class="banner" src="../assets/register.jpg" alt="">

      <div class="title">
        <div class="name">Text Encryption System</div>
        <p>Get Started it!</p>
      </div>

      <div class="input">
        <p>REGISTER CODE</p>

        <el-input class="code" v-model="registerCode" placeholder="XX-XX-XX-XX-XX-XX" spellcheck="false"></el-input>

      </div>

      <div class="button" v-loading="loading">
        <el-button type="warning" round @click="register"><b>REGISTER</b></el-button>
      </div>

    </div>

  </div>
</template>


<script>

import http from '@/api/http';
import vSymbol from '@/components/Symbol.vue';

export default {

  name: 'Register',
  
  components: {

    vSymbol

  },

  data () {

    return {

      registerCode: null,

      loading: false

    }
  
  },

  created: function () {

    var _this = this

    document.onkeydown = function (e) {
      
      let key = window.event.keyCode

      if (key == 13) {

        _this.register()
      
      }
    
    }
  
  },

  methods: {

    register() {

        if (this.registerCode == null || this.registerCode == "") {

          this.$message.warning("register code can't be empty!");

        } else if (this.registerCode.length > 50)  {
          
          this.$message.warning("register code is too long!");

        } else {  // 输入合法

          // 显示加载中
          this.loading = true;
          
          var _this = this;

          http({

					// 假设后台需要的是表单数据这里你就可以更改
					headers: {

					"Content-Type": "application/json;charset=UTF-8"
					
					},

          method: 'post',
          
					url: 'http://localhost:8080/encryption/register',

					data: {

            registerCode: _this.registerCode

					},

					responseType: 'json'

        // 直接使用function, 而不使用箭头函数时, 不可以直接使用this
        // function有独立的作用域
				}).then(function (res) {

					console.log(res);

					var code = res.code;
					var info = res.info;

					if (res.code == 200) {
            
            // 取消加载中覆盖
            _this.loading = false;

            _this.$message.success(info);
            
						_this.$router.replace("/userRegister");
					
					} else {

            // 取消加载中覆盖
            _this.loading = false;
						
            _this.$message.warning(info);

          }
          
        // 使用箭头函数时, 则可以直接使用this
        // 箭头函数没有独立的作用域
				}).catch((err) => {

          console.log(err);
          
          this.$message.error("global error!");

          // 取消加载中覆盖
          _this.loading = false;
				
				});

        }

    }

  }

}

</script>

<style>
.code .el-input__inner {

    border-radius: 0%;
    background: none;
    height: 55px;
    border: 1.8px solid rgb(138, 136, 136);
    color: white;
    
}
</style>
<style lang="less" scoped>

.button .el-button.is-round {

    border-radius: 32px;
    padding: 21px 33px;
    font-size: 20px;
    letter-spacing: 0cm;

}

</style>

<style lang="less" scoped>

.button {

  font-family: Sitka Subheading;
  color: white;

  position: absolute;
  left: 880px;
  top: 396px;

}


.code {

  font-family: Sitka Subheading;
  font-size: 24px;
  color: white;
  width: 500px;

  position: absolute;
  left: 332px;
  top: 400px;

}

.input p {

    font-size: 28px;
    color: rgb(238, 234, 234);
    height: auto;
    position: absolute;
    top: 340px;
    left: 332px;
    z-index: 1;

}


.title {

    height: auto;
    letter-spacing: 0.1em; 
    font-family: Sitka Subheading;

}

.title p {

  font-size: 55px;
  color: rgb(255, 255, 255);
  position: absolute;
  top: 140px;
  left: 550px;
  z-index: 1;

}

.name {

  font-size: 80px;
  color: rgb(255, 255, 255);
  position: absolute;
  top: 100px;
  left: 22%;
  z-index: 1;

}

.register {

    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
    width: 100%;
    min-height: 100%;
    height: auto;
    background:rgba(7, 16, 26, 0.459);
    .banner {

        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: -1;
    
    }
    
}



</style>