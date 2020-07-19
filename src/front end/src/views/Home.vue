<template>
  <div>
    <v-symbol></v-symbol>
    
    <img @click="go" src="@/assets/logout1.png" class="img">

    <div class="home">
      <img class="banner" src="../assets/home.jpg" alt="">

      <div class="title">
        <div class="name">Encrypt Text Better. Faster. Greater.</div>
        <p>The digital text encryption system powered by MD5 algorithm.</p>
      </div>

      <el-input class = "area" type="textarea" rows="1" placeholder="encryption shows here..." 
                v-model="text_output" resize="none" autofocus spellcheck="false" maxlength="35" show-word-limit></el-input>

      <el-input class = "area_input" type="textarea" rows="7" placeholder="input something here..." 
                v-model="text_input" resize="none" maxlength="255" show-word-limit autofocus spellcheck="false"></el-input>

      <div class="button1">
        <el-button type="primary" @click="encrypt" round><b>Encrypt</b></el-button>
      </div>

      <el-upload
        class="upload-demo"
        action="string"
        name="file"
        accept=".txt"
        :http-request="uploadFile"
        :limit="1"
        :on-exceed="exceedFn">

        <div class="button3">
          <el-button type="success" slot="trigger" round><b>Upload</b></el-button>
        </div>

      </el-upload>

    </div>

  </div>
</template>


<script>

import http from '@/api/http';
import vSymbol from '@/components/Symbol.vue';

export default {

  name: 'Home',
  
  components: {

    vSymbol

  },

  data () {

    return {

      text_input: null,
      text_output: null

    }
  
  },

  methods: {

    go() {

      this.$router.replace('/login');
      this.$message.success("logout successful!");

    },

    uploadFile(fileObj) {

      let param = new FormData();
      
      param.append("file", fileObj.file);

      var _this = this;
      
      this.axios({

        method: 'post',

        url: 'http://localhost:8080/encryption/upload',

        headers: {

          'Content-Type': 'multipart/form-data'

        },

        data: param

      }).then(function (res) {

        var code = res.data.code;
        var info = res.data.info;
        var msg = res.data.msg;

        if (code == 200) {
          
          _this.$message.success(msg);

          _this.text_output = info.encryption;
          
          _this.text_input = info.content;

        } else {
        
          _this.$message.warning(msg);

        }
        
      }).catch(function (err) {
        
          _this.$message.error(msg);
        
      })

    },

    exceedFn(files, fileList) {
      
      alert('每次只能上传 1 个文件');
    
    },

    encrypt() {

        var _this = this;

        http({

					// 假设后台需要的是表单数据这里你就可以更改
					headers: {

					"Content-Type": "application/json;charset=UTF-8"
					
					},

          method: 'post',
          
					url: 'http://localhost:8080/encryption/encrypt',

					data: {

            message: _this.text_input

					},

					responseType: 'json'

        // 直接使用function, 而不使用箭头函数时, 不可以直接使用this
        // function有独立的作用域
				}).then(function (res) {

					var code = res.code;
          var info = res.info;
          var msg = res.msg;

					if (res.code == 200) {
            
            _this.$message.success(msg);

            _this.text_output = info;
            
					
					} else {
						
            _this.$message.warning(msg);

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

</script>


<style lang="less" scoped>


.img {

  position: relative;
  left: 710px;
  top: -40px;
  width: 60px;
  height: 60px;
  z-index: 10;

}

.img:hover {
  
  cursor: pointer;

  opacity: 90%;

}


.upload-demo {

  position: absolute;
  top: 440px;
  left: 920px;
  z-index: 10;

}

.button3 {

  font-family: Sitka Subheading;
  color: white;

}

.button3 .el-button.is-round {
    
    font-family: Microsoft Jhenghei UI;
    border-radius: 32px;
    padding: 14px 70px;
    font-size: 30px;
    letter-spacing: 0cm;
    font-weight: normal;

}


.button1 .el-button.is-round {
    
    font-family: Microsoft Jhenghei UI;
    border-radius: 32px;
    padding: 14px 70px;
    font-size: 30px;
    letter-spacing: 0cm;
    font-weight: normal;

}


.button1 {

  font-family: Sitka Subheading;
  color: white;
  position: absolute;
  left: 920px;
  top: 350px;

}

.area_input {

  position: absolute;
  left: 332px;
  top: 280px;
  font-size: 20px;
  height: 200px;
  width: 450px;

}

.area {

  position: absolute;
  left: 850px;
  top: 280px;
  font-size: 20px;
  width: 400px;

}


.code {

  font-family: Microsoft Jhenghei UI;
  font-size: 24px;
  color: white;
  width: 500px;

  position: absolute;
  left: 332px;
  top: 400px;

}


.title {

    height: auto;
    letter-spacing: 0.1em; 
    font-family: Microsoft Jhenghei UI;

}

.title p {

  font-size: 25px;
  color: rgb(255, 255, 255);
  position: absolute;
  top: 155px;
  left: 360px;
  z-index: 1;

}

.name {

  font-size: 50px;
  font-weight: bold;
  color: rgb(255, 255, 255);
  position: absolute;
  top: 105px;
  left: 332px;
  z-index: 1;

}

.home {

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