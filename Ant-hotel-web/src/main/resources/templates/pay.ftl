<!DOCTYPE html>
<html>

<head>
    <title>Ant Hotel 住宿信息人员登记系统</title>
    <script src="/js/utils.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<div class="header">
    <h1>Ant Hotel 住宿消费交付平台</h1>
</div>
<div class="main">
    <div class="qrcode">
        <img src="images/alipay.png">
        <img src="images/wxpay.png">
    </div>
    <div class="hint">
        支付宝只服务于有营业执照的商户，已经放弃了个人。截止目前（2020-03-01）无法以个人身份（或以个人为主体）直接申请API。所以这里不再进行详细实现。
        微信同支付宝，不支付个人申请。
    </div>
</div>
<div class="footer">
    <p><a href="#">Ant Hotel 技术组 </a>版权所有</p>
    <p>Copyright © 2020 Ant Hotel. All Rights Reserved.</p>
</div>
<style>
    .qrcode {
        margin: 0 auto;
        text-align: center;
    }

    .hint {
        margin: 50px auto;
        width: 55%;
        color: red;
    }
</style>
</body>

</html>