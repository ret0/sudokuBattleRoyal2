var sudokuSocket = Stomp.over(new SockJS('/sockjsendpoint'));

sudokuSocket.connect({}, function (frame) {

    console.log('Connected: ' + frame);

    sudokuSocket.subscribe('/app/game/players', function (message) {
        console.log("Subscribe called, message body: " + message.body);
    });

    sudokuSocket.subscribe('/topic/game/login', function (message) {
        console.log("topic/game/login called, message body: " + message.body);
    });


}, function (error) {
    console.error('Error:' + error);
});
