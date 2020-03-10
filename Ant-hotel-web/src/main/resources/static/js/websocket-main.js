$(document).ready(function () {
    const video = $('#webcam')[0];
    const overlay = $('#overlay')[0];
    cc = overlay.getContext('2d');

    var videoElement = document.querySelector('video');
    var downloadLink = document.querySelector('a#downloadLink');

    function onStreaming(stream) {
        video.srcObject = stream;

        let mediaRecorder = new MediaRecorder(stream);
        mediaRecorder.ondataavailable = function (blob) {
            console.log(blob)
        }
        mediaRecorder.start(1000)
        console.log(stream)
    }

    // navigator.mediaDevices
    //     .getUserMedia({
    //         video: true,
    //     })
    //     .then(onStreaming);


    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

    if (getBrowser() == "Chrome") {
        var constraints = {
            "video": {
                "mandatory": {
                    "minWidth": 640,
                    "maxWidth": 640,
                    "minHeight": 480,
                    "maxHeight": 480
                },
                "optional": []
            }
        };// Chrome
    } else if (getBrowser() == "Firefox") {
        var constraints = {
            audio: true,
            video: {
                width: {
                    min: 640,
                    ideal: 640,
                    max: 640
                },
                height: {
                    min: 480,
                    ideal: 480,
                    max: 480
                }
            }
        }; // Firefox
    }

    var mediaRecorder;
    var chunks = [];
    var count = 0;
    var url = "ws://127.0.0.1:8081/websocket/zgh";

    var webSocket = null;

    if (webSocket == null) {
        webSocket = new WebSocket(url);
    }

    webSocket.onerror = function (event) {
        console.log(event.data);
    };

    webSocket.onopen = function (event) {
        console.log("建立socket通信成功")
        webSocket.send("");
    };


    window.onbeforeunload = function () {
        webSocket.onclose();
    };


    webSocket.onerror = function () {
        console.log("WebSocket连接发生错误");
        webSocket = new WebSocket(url);
    };


    webSocket.onclose = function () {
        console.log("WebSocket连接关闭");
        mediaRecorder.stop();
        // webSocket = new WebSocket(url);
    };
    webSocket.binaryType = "arraybuffer";

    webSocket.onmessage = function (event) {
        console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    };


    function startRecording(stream) {
        console.log('Start recording...');
        if (typeof MediaRecorder.isTypeSupported == 'function') {
            if (MediaRecorder.isTypeSupported('video/webm;codecs=vp9')) {
                var options = {
                    mimeType: 'video/webm;codecs=h264'
                };
            } else if (MediaRecorder.isTypeSupported('video/webm;codecs=h264')) {
                var options = {
                    mimeType: 'video/webm;codecs=h264'
                };
            } else if (MediaRecorder.isTypeSupported('video/webm;codecs=vp8')) {
                var options = {
                    mimeType: 'video/webm;codecs=vp8'
                };
            }
            console.log('Using ' + options.mimeType);
            mediaRecorder = new MediaRecorder(stream, options);
        } else {
            mediaRecorder = new MediaRecorder(stream);
        }

        mediaRecorder.start(10);

        var url = window.URL || window.webkitURL;
        console.log(stream);
        video.srcObject = stream;

        mediaRecorder.ondataavailable = function (e) {
            chunks.push(e.data);
            var reader = new FileReader();
            reader.addEventListener("loadend", function () {
                // reader.result 包含转化为类型数组的blob
                var buf = new Uint8Array(reader.result);
                webSocket.send(buf);
            });
            reader.readAsArrayBuffer(e.data);
        };

        mediaRecorder.onerror = function (e) {
            console.log('Error: ', e);
        };

        mediaRecorder.onstart = function () {
            console.log('Started & state = ' + mediaRecorder.state);
        };

        mediaRecorder.onstop = function () {
            console.log('Stopped  & state = ' + mediaRecorder.state);

            var blob = new Blob(chunks, {
                type: "video/webm"
            });
            chunks = [];

            var videoURL = window.URL.createObjectURL(blob);

            downloadLink.href = videoURL;
            videoElement.src = videoURL;
            downloadLink.innerHTML = 'Download video file';

            var rand = Math.floor((Math.random() * 10000000));
            var name = "video_" + rand + ".webm";

            downloadLink.setAttribute("download", name);
            downloadLink.setAttribute("name", name);
        };

        mediaRecorder.onpause = function () {
            console.log('Paused & state = ' + mediaRecorder.state);
        }

        mediaRecorder.onresume = function () {
            console.log('Resumed  & state = ' + mediaRecorder.state);
        }

        mediaRecorder.onwarning = function (e) {
            console.log('Warning: ' + e);
        };
    }


    if (typeof MediaRecorder === 'undefined' || !navigator.getUserMedia) {
        alert('MediaRecorder not supported on your browser, use Firefox 30 or Chrome 49 instead.');
    } else {
        navigator.getUserMedia(constraints, startRecording, errorCallback);
    }

    function errorCallback(error) {
        console.log('navigator.getUserMedia error: ', error);
    }

    // browser ID
    function getBrowser() {
        var nVer = navigator.appVersion;
        var nAgt = navigator.userAgent;
        var browserName = navigator.appName;
        var fullVersion = '' + parseFloat(navigator.appVersion);
        var majorVersion = parseInt(navigator.appVersion, 10);
        var nameOffset, verOffset, ix;

        // In Opera, the true version is after "Opera" or after "Version"
        if ((verOffset = nAgt.indexOf("Opera")) != -1) {
            browserName = "Opera";
            fullVersion = nAgt.substring(verOffset + 6);
            if ((verOffset = nAgt.indexOf("Version")) != -1)
                fullVersion = nAgt.substring(verOffset + 8);
        }
        // In MSIE, the true version is after "MSIE" in userAgent
        else if ((verOffset = nAgt.indexOf("MSIE")) != -1) {
            browserName = "Microsoft Internet Explorer";
            fullVersion = nAgt.substring(verOffset + 5);
        }
        // In Chrome, the true version is after "Chrome"
        else if ((verOffset = nAgt.indexOf("Chrome")) != -1) {
            browserName = "Chrome";
            fullVersion = nAgt.substring(verOffset + 7);
        }
        // In Safari, the true version is after "Safari" or after "Version"
        else if ((verOffset = nAgt.indexOf("Safari")) != -1) {
            browserName = "Safari";
            fullVersion = nAgt.substring(verOffset + 7);
            if ((verOffset = nAgt.indexOf("Version")) != -1)
                fullVersion = nAgt.substring(verOffset + 8);
        }
        // In Firefox, the true version is after "Firefox"
        else if ((verOffset = nAgt.indexOf("Firefox")) != -1) {
            browserName = "Firefox";
            fullVersion = nAgt.substring(verOffset + 8);
        }
        // In most other browsers, "name/version" is at the end of userAgent
        else if ((nameOffset = nAgt.lastIndexOf(' ') + 1) < (verOffset = nAgt.lastIndexOf('/'))) {
            browserName = nAgt.substring(nameOffset, verOffset);
            fullVersion = nAgt.substring(verOffset + 1);
            if (browserName.toLowerCase() == browserName.toUpperCase()) {
                browserName = navigator.appName;
            }
        }
        // trim the fullVersion string at semicolon/space if present
        if ((ix = fullVersion.indexOf(";")) != -1)
            fullVersion = fullVersion.substring(0, ix);
        if ((ix = fullVersion.indexOf(" ")) != -1)
            fullVersion = fullVersion.substring(0, ix);

        majorVersion = parseInt('' + fullVersion, 10);
        if (isNaN(majorVersion)) {
            fullVersion = '' + parseFloat(navigator.appVersion);
            majorVersion = parseInt(navigator.appVersion, 10);
        }

        return browserName;
    }

});
