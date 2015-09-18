var sudokuSocket = Stomp.over(new SockJS('/sockjsendpoint'));

sudokuSocket.connect({}, function (frame) {

    console.log('Connected: ' + frame);

    sudokuSocket.subscribe('/topic/game/login', function (message) {
        console.log("/topic/game/login called: " + message.body);
    });
    sudokuSocket.subscribe('/app/game/players', function (message) {
        console.log("app/game/players called: " + message.body);
    });
    sudokuSocket.subscribe('/topic/game/update', function (message) {
        console.log(message);
        updateGameState(JSON.parse(message.body));
    });
    sudokuSocket.subscribe('/user/queue/attempt', function (message) {
        console.log(message);
        updateAttempt(JSON.parse(message.body));
    });
}, function (error) {
    console.error('Error:' + error);
});

function setupInputHandler() {
    $('input').on('input', function (element) {
        if (this.value.length > 1) {
            this.value = this.value.slice(0, 1);
        }
        var x = $(this).attr("data-x");
        var y = $(this).attr("data-y");
        sudokuSocket.send("???", {}, JSON.stringify({x: x, y: y, value: this.value}));
    });
}

function updateGameState(gameUpdate) {
    if (gameUpdate.type === "CORRECT" || gameUpdate.type === "FINISHED") {
        setFieldValue(gameUpdate.x, gameUpdate.y, gameUpdate.value);
    }
}

function updateAttempt(gameUpdate) {
    var fieldInput = getFieldInput(gameUpdate.x, gameUpdate.y);
    if (gameUpdate.type === "CORRECT" || gameUpdate.type === "FINISHED") {
        setValueOnField(fieldInput, gameUpdate.value); //we set value again to assure sync with animation
        correctAnimationOnField(fieldInput);
    } else if (gameUpdate.type === "WRONG") {
        setValueOnField(fieldInput, 0);
        wrongAnimationOnField(fieldInput);
    } else if (gameUpdate.type === "TOO_LATE") {
        tooLateAnimationOnField(fieldInput);
    }
}

function getFieldInput(x, y) {
    return $('input[data-x=' + x + '][data-y=' + y + ']')[0];
}

function setFieldValue(x, y, value) {
    setValueOnField(getFieldInput(x, y), value);
}

function setValueOnField(fieldInput, value) {
    if (value === 0) {
        fieldInput.value = "";
    } else {
        fieldInput.value = value;
        fieldInput.disabled = true;
    }
}

function correctAnimationOnField(fieldInput) {
    $(fieldInput).transition({"background-color": "green"});
}

function wrongAnimationOnField(fieldInput) {
    $(fieldInput).transition({"background-color": "red"})
        .transition({"background-color": "white"});
}

function tooLateAnimationOnField(fieldInput) {
    $(fieldInput).transition({"background-color": "blue"})
        .transition({"background-color": "white"});
}


