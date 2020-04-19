<!DOCTYPE html>
<html>

<head>
    <title>Ant Hotel 住宿信息人员登记系统</title>
    <script src="/js/sweetalert.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css">
</head>

<body>
<div class="header">
    <h1>Ant Hotel 住宿信息人员登记系统</h1>
</div>
<div class="main">
    <div class="video">
        <video id="webcam" width="450" height="400" autoplay loop></video>
        <canvas id="overlay" width="450" height="400"></canvas>
        <div class="protocol">
            请确保本人手持有效身份证，通过摄像头人证识别。本酒店郑重承诺将对一切用户信息全过程加密处理，防止隐私泄露。
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
                    <input type="text" class="form-control" disabled id="name" value="辉辉辉" placeholder="姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="id" class="col-sm-2 control-label">身&ensp;份&ensp;证</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" disabled id="id" value="372324200001018888"
                           placeholder="身份证号">
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-2 control-label">住&emsp;&emsp;址</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" disabled id="address" value="山东省淄博市张店区xxx小区2单元11栋233号"
                           placeholder="住址">
                </div>
            </div>
            <div class="form-group">
                <label for="date" class="col-sm-2 control-label">有效期至</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" disabled id="date" value="2020-12-31" placeholder="住址">
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
<script src="/js/utils.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/js/main.js"></script>
<script>
    let orderId = getRequest().orderId;

    function submitForm() {
        if ($('#check').prop('checked')) {
            let formData = {
                idImage: getBase64ImageDataURL(document.getElementById("pic")),
                name: $('#name').val(),
                userId: $('#id').val(),
                orderId: orderId,
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
                    if (text.code == 200) {
                        swal({
                            title: "识别成功",
                            text: text.data,
                            icon: "success",
                            buttons: {
                                butto1: {
                                    text: "已付定金",
                                    value: false
                                },
                                button2: {
                                    text: "跳转支付",
                                    value: true
                                }
                            }
                        }).then(function (value) {
                            if (value) {
                                window.location.href = "/pay?orderId=" + orderId;
                            }
                        });
                    } else {
                        swal({
                            title: "识别失败",
                            text: text.message,
                            icon: "error",
                            button: "再试一次",
                        })
                    }
                },
                error: function (error) {
                    alert(JSON.stringify(error))
                }
            })
            ;
        } else {
            swal({
                title: "小提示",
                text: "请阅读并通过相关协议",
                icon: "warning",
                button: "确定",
            })
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
</body>

</html>