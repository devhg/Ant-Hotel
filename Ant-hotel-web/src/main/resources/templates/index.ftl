<!DOCTYPE html>
<html>

<head>
    <title>Ant Hotel 住宿信息人员登记系统</title>
    <script src="/js/utils.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
<div class="header">
    <h1>Ant Hotel 住宿信息人员登记系统</h1>
    <h1>${name}</h1>
</div>
<div class="main">
    <div class="video">
        <video id="webcam" width="450" height="400" autoplay loop></video>
        <canvas id="overlay" width="450" height="400"></canvas>
        <div class="protocol">
            请确保本人手持有肖6身份证，通过摄像头人证识别。本酒店郑重承诺将对一切用户信息全过程加密处理，防止隐私泄露。
            客户同意以上几点后，勾选阅读通过按钮完成登记。
        </div>
    </div>
    <div class="form">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="pic" class="col-sm-2 control-label">照&emsp;&emsp;片</label>
                <div class="col-sm-10">
                    <img src="/images/1.png" id="pic" alt="">
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">姓&emsp;&emsp;名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" value="辉辉辉" placeholder="姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="id" class="col-sm-2 control-label">身&ensp;份&ensp;证</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="id" value="372324200001018888" placeholder="身份证号">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-2 control-label">住&emsp;&emsp;址</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="address" value="山东省淄博市张店区xxx小区2单元11栋233号"
                           placeholder="住址">
                </div>
            </div>
            <div class="form-group">
                <label for="date" class="col-sm-2 control-label">有效期至</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="date" value="2020-12-31" placeholder="住址">
                </div>
            </div>
            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">手&ensp;机&ensp;号</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" value="15277688888" placeholder="住址">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input id="check" type="checkbox"> 已阅读通过协议
                        </label>
                    </div>
                </div>
            </div>

        </form>
        <div class="col-sm-offset-2 col-sm-10">
            <button onclick="submitForm()" class="btn btn-primary">
                立即住宿登记
            </button>
        </div>
    </div>
</div>
<div class="footer">
    <p><a href="#">Ant Hotel 技术组 </a>版权所有</p>
    <p>Copyright © 2020 Ant Hotel. All Rights Reserved.</p>
</div>
<!-- 导入相关的js文件 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/js/main.js"></script>
<script>
    function submitForm() {
        if ($('#check').prop('checked')) {
            let formData = {
                idImage: getBase64ImageDataURL(document.getElementById("pic")),
                name: $('#name').val(),
                userId: $('#id').val(),
                address: $('#address').val(),
                dateTo: $('#date').val(),
                phone: $('#phone').val(),
                videoImage: getBase64ImageDataURL(document.getElementById("webcam"))
            }
            // console.log(formData)
            $.ajax({
                type: "post",
                url: "/faceAdd",
                data: JSON.stringify(formData),
                dataType: 'json', //服务器返回json格式数据
                type: 'post', //HTTP请求类型
                timeout: 10000, //超时时间设置为10秒；
                contentType: 'application/json;charset=utf-8',
                async: false,
                success: function (text) {
                    console.log(text);
                    alert(JSON.stringify(text))
                },
                error: function (error) {
                    alert(JSON.stringify(error))
                }
            });
        } else {
            alert("请阅读并通过相关协议")
        }

    }

    /**
     * 获取img、video标签中的图片 base64数据
     * @param src
     * @returns {string}
     */
    function getBase64ImageDataURL(src) {
        let canvas = document.createElement('canvas');
        canvas.width = src.width;
        canvas.height = src.height;
        let ctx = canvas.getContext('2d');
        ctx.drawImage(src, 0, 0, src.width, src.height);
        return canvas.toDataURL();
    }
</script>
<style>
    body {
        background-image: url(https://gw.alipayobjects.com/zos/rmsportal/TVYTbAXWheQpRcWDaDMu.svg);
    }

    h1 {
        color: rgb(88, 139, 216);
    }

    .header {
        margin: 100px auto;
        text-align: center;
    }

    .main {
        width: 1150px;
        margin: 0 auto;
        position: relative;
    }

    .video {
        position: relative;
        float: left;
        width: 500px;
        height: 470px;
    }

    .protocol {
        position: absolute;
        bottom: 0px;
        left: 30px;
        color: red;
    }

    .form {
        width: 500px;
        height: 500px;
        float: right;
        margin: 30px;
        position: relative;
    }

    .form img {
        width: 100px;
        height: 100px;
        margin: 5px auto;
    }

    .footer {
        background-color: #eaebeb;

        padding: 20px;
        margin: 0 auto;
        position: fixed;
        text-align: center;
        width: 100%;
        bottom: 0;
    }

    /*video和canvas标签位置重合使显示出来的人脸模型和视频重合*/
    #webcam,
    #overlay {
        position: absolute;
        top: 10px;
        left: 30px;
    }
</style>
</body>

</html>