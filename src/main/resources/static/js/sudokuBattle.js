var sudokuSocket = Stomp.over(new SockJS('/sockjsendpoint'));

sudokuSocket.connect({}, function (frame) {

    console.log('Connected: ' + frame);

    sudokuSocket.subscribe('/app/game/players', function (message) {
        console.log("Subscribe called, message body: " + message.body);
    });


}, function (error) {
    console.error('Error:' + error);
});

