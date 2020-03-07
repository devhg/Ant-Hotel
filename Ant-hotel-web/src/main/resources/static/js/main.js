// var videoInput = document.getElementById('webcam');
// var canvasInput = document.getElementById('overlay');
// var cc = canvasInput.getContext('2d');


$(document).ready(function () {
    const video = $('#webcam')[0];
    const overlay = $('#overlay')[0];
    cc = overlay.getContext('2d');
    // const overlay_eye = overlay.getContext('2d');
    // const ctrack = new clm.tracker();
    // ctrack.init();
    //
    // // 根据输出的数组中人脸相应位置的坐标，圈出眼睛的位置
    // function getEyes(positions) {
    //     const minX = positions[23][0] - 6;
    //     const maxX = positions[28][0] + 6;
    //     const minY = positions[24][1] - 6;
    //     const maxY = positions[26][1] + 6;
    //
    //     const width = maxX - minX;
    //     const height = maxY - minY;
    //
    //     return [minX, minY, width, height];
    // }

    // 持续监测人脸
    // function detect() {
    //     // 检查是否检测到人脸
    //     requestAnimationFrame(detect);
    //     let currentPosition = ctrack.getCurrentPosition();
    //
    //     overlay_eye.clearRect(0, 0, 400, 300);
    //     if (currentPosition) {
    //         //  在overlay canvas上画出检测到的人脸:
    //         ctrack.draw(overlay);
    //
    //         // 用红色画出人眼位置:
    //         const eyesRect = getEyes(currentPosition);
    //         overlay_eye.strokeStyle = 'red';
    //         overlay_eye.strokeRect(eyesRect[0], eyesRect[1], eyesRect[2], eyesRect[3]);
    //
    //     }
    // }

    function onStreaming(stream) {
        video.srcObject = stream;
        // ctrack.start(video);
        // detect();
    }

    navigator.mediaDevices
        .getUserMedia({
            video: true,
        })
        .then(onStreaming);

});
