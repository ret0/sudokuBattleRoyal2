var sudokuSocket = Stomp.over(new SockJS('/sockjsendpoint'));

sudokuSocket.connect({},function(frame) {

    var username = frame.headers['user-name'];

    console.log('Connected: ' + frame);
    sudokuSocket.subscribe('/topic/game/login', function (message) {
        console.log(message);
        addConnectedPlayer(JSON.parse(message.body));
    });
    sudokuSocket.subscribe('/app/game/players', function (message) {
        updateConnectedPlayers(JSON.parse(message.body));
    });
    sudokuSocket.subscribe('/topic/game/start', function (message) {
        initBoard(JSON.parse(message.body));
    });
    sudokuSocket.subscribe('/topic/game/update', function (message) {
        console.log(message);
        update(JSON.parse(message.body));
    });
    sudokuSocket.subscribe('/user/queue/attempt', function (message) {
        console.log(message);
        updateAttempt(JSON.parse(message.body));
    });
}, function(error) {
    console.error('Error:' + error);
});

function initBoard(board) {
    for (var x = 0; x < board.length; x++) {
        for (var y = 0; y < board[0].length; y++) {
            setFieldValue(x, y, board[x][y]);
        }
    }
}

function update(gameUpdate) {
    updateScoring(gameUpdate.playerName, gameUpdate.scoreDelta);
    if (gameUpdate.update === "CORRECT" || gameUpdate.update === "FINISHED") {
        setFieldValue(gameUpdate.x, gameUpdate.y, gameUpdate.value);
    }
}

function updateAttempt(gameUpdate) {
    var fieldInput = getFieldInput(gameUpdate.x, gameUpdate.y);
    if (gameUpdate.update === "CORRECT" || gameUpdate.update === "FINISHED") {
        setValueOnField(fieldInput, gameUpdate.value); //we set value again to assure sync with animation
        correctAnimationOnField(fieldInput);
    } else if (gameUpdate.update === "WRONG") {
        setValueOnField(fieldInput, 0);
        wrongAnimationOnField(fieldInput);
    } else if (gameUpdate.update == "TOO_LATE") {
        tooLateAnimationOnField(fieldInput);
    }
}

function getFieldInput(x, y) {
    return $('input[data-x=' + x + '][data-y=' + y + ']')[0];
}

function setFieldValue(x, y, value) {
    setValueOnField(getFieldInput(x,y), value);
}

function setValueOnField(fieldInput, value) {
    if (value === 0) {
        fieldInput.value = "";
    } else {
        fieldInput.value = value;
    }
}

function updateConnectedPlayers(players) {
    ranking.players = players;
}

function addConnectedPlayer(playerConnectedEvent) {
    ranking.players.unshift({playerName: playerConnectedEvent.username, score : 0});
}

function updateScoring(playerName, scoreDelta) {
    _.find(ranking.players, { 'playerName': playerName }).score += scoreDelta;
    ranking.players.sort(function(player1, player2){return player2.score - player1.score});
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

function setupInputHandler() {
    $('input').on('input', function (element) {
        if (this.value.length > 1) {
            this.value = this.value.slice(0,1);
        }
        var x = $(this).attr("data-x");
        var y = $(this).attr("data-y");
        sudokuSocket.send("/app/solve", {}, JSON.stringify({x: x, y: y, value: this.value}));
    });
}
var ranking;
function setupComponents() {
    ranking = new Vue({
        el: '#ranking',
        data: {
          players: []
        }
    });
}
