$(document).ready(function () {
    const video = $('#webcam')[0];
    const overlay = $('#overlay')[0];
    cc = overlay.getContext('2d');


    function onStreaming(stream) {
        video.srcObject = stream;

        // let mediaRecorder = new MediaRecorder(stream);
        // mediaRecorder.ondataavailable = function (blob) {
        //     console.log(blob)
        // }
        // mediaRecorder.start(1000)
        // console.log(stream)
    }

    navigator.mediaDevices
        .getUserMedia({
            video: true,
        })
        .then(onStreaming);

});
