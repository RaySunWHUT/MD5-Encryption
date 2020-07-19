<template>
  <div>
    <v-symbol></v-symbol>
    <div class="theme">
      <img class="banner" src="../assets/user_register.jpg" alt="">

      <div class="title">
        <div class="name">Welcome to TexEncrySystem</div>
        <p>enter your details belows</p>
      </div>

      <div class="input">
        <p class="code1p">Username</p>

        <el-input class="code1" v-model="username" spellcheck="false"></el-input>

        <p class="code2p">Password</p>

        <el-input class="code2" v-model="password" spellcheck="false" show-password></el-input>

        
        <p class="code3p">Confirm Password</p>

        <el-input class="code3" v-model="confirm_password" spellcheck="false" show-password></el-input>

      </div>

      <div class="button1">
        <el-button type="warning" round @click="userRegister">REGISTER</el-button>
      </div>

    </div>

  </div>
</template>

<script>

import http from '@/api/http'
import vSymbol from '@/components/Symbol.vue'
import container from '@/util/container'

export default {

  name: 'UserRegister',

  components: {

    vSymbol

  },
  
    data () {

    return {

      username: null,

      password: null,
      
      confirm_password: null

    }
  
  },

  created: function () {

    var _this = this

    document.onkeydown = function (e) {
      
      let key = window.event.keyCode

      if (key == 13) {

        _this.userRegister()
      
      }
    
    }
  
  },

  methods: {
    userRegister() {

        if (this.username == null || this.username == "" || this.password == "" || this.password == null || this.confirm_password == null) {

          this.$message.warning("username and password can't be empty!");

        } else if (this.password.length < 8 || this.username.length < 3) {
          
          this.$message.warning("username or password is too short!");
          
        } else if (!(container.hasDigit(this.password) && container.hasLetter(this.password))) {  // 密码必须包含字母(letter)和数字(digit)

          this.$message.warning("password must contain letter and digit!");

        } else if (this.password != this.confirm_password) {  // 密码输入不一致

          this.$message.warning("password and confirm password is different!");

        } else {  // 输入合法
          
          var _this = this;

          http({
					// 假设后台需要的是表单数据这里你就可以更改
            headers: {

                "Content-Type": "application/json;charset=UTF-8"
            
            },

            method: 'post',
          
            url: 'http://localhost:8080/encryption/userRegister',

            data: {

                username: _this.username,
                password: _this.password

            },

            responseType: 'json'

            // 直接使用function, 而不使用箭头函数时, 不可以直接使用this
            // function有独立的作用域
            }).then(function (res) {

                console.log(res);

                var code = res.code;
                var info = res.info;

                if (res.code == 200) {
                
                    _this.$message.success(info);
                    _this.$router.push("/login");
					
                } else {
						
                _this.$message.warning(info);

                }
          
            // 使用箭头函数时, 则可以直接使用this
            // 箭头函数没有独立的作用域
            }).catch((err) => {

                console.log(err);
                
                this.$message.error("global error!");
				
            });

        }

    }

  }


}
</script>

<style>
.input .el-input__inner {

    border-radius: 1%;
    background: none;
    height: 55px;
    border: 1.8px solid rgb(138, 136, 136);
    color: white;
    padding: 0.5%;
}

.input .el-input__inner:focus {

    border-radius: 1%;
    background: none;
    height: 55px;
    border: 1.8px solid rgb(86, 171, 241);
    color: white;
    padding: 0.5%;
}


</style>

<style lang="less" scoped>

.name {

  font-size: 65px;
  color: rgb(255, 255, 255);
  position: absolute;
  top: 60px;
  left: 410px;
  z-index: 1;

}


.title {

    height: auto;
    letter-spacing: 0.1em; 
    font-family: Sitka Subheading;

}


.title p {

  font-size: 42px;
  color: rgb(212, 209, 209);
  position: absolute;
  top: 80px;
  left: 410px;
  z-index: 1;

}

.button1 .el-button.is-round {
    
    font-family: Microsoft Jhenghei UI;
    border-radius: 32px;
    padding: 14px 70px;
    font-size: 30px;
    letter-spacing: 0cm;
    font-weight: normal;

}

.el-button--warning {
    background-color: #C70000;
    border-color: #FF3366;

}

.el-button--warning:hover {

    background-color: #FF3345;
    border-color: #fa88a5;

}


.button1 {

  font-family: Sitka Subheading;
  color: white;
  position: absolute;
  left: 574px;
  top: 558px;

}

.code1p {

  font-size: 28px;
  color: rgb(238, 234, 234);
  height: auto;
  position: absolute;
  top: 185px;
  left: 410px;
  z-index: 1;

}

.code1 {

  font-family: Sitka Subheading;
  font-size: 28px;
  color: white;
  width: 580px;
  text-indent: 0%;
  position: absolute;
  left: 410px;
  top: 250px;

}


.code2p {

  font-size: 28px;
  color: rgb(238, 234, 234);
  height: auto;
  position: absolute;
  top: 305px;
  left: 410px;
  z-index: 1;

}


.code2 {

  font-family: Sitka Subheading;
  font-size: 28px;
  color: white;
  width: 580px;
  position: absolute;
  left: 410px;
  top: 365px;

}

.code3p {

  font-size: 28px;
  color: rgb(238, 234, 234);
  height: auto;
  position: absolute;
  top: 415px;
  left: 410px;
  z-index: 1;

}


.code3 {

  font-family: Sitka Subheading;
  font-size: 28px;
  color: white;
  width: 580px;
  position: absolute;
  left: 410px;
  top: 475px;

}


.theme {

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