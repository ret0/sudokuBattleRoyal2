var sudokuSocket = Stomp.over(new SockJS('??'));

sudokuSocket.connect({}, function (frame) {

    console.log('Connected: ' + frame);

    sudokuSocket.subscribe('??', function (message) {
        console.log("Subscribe called, message body: " + message.body);
    });


}, function (error) {
    console.error('Error:' + error);
});

