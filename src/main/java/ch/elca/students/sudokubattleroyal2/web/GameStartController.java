package ch.elca.students.sudokubattleroyal2.web;

import ch.elca.students.sudokubattleroyal2.game.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStartController {

    @Autowired
    private GameManager gameManager;

    @RequestMapping("/admin/start")
    public void createAndStartGame() {
        gameManager.createAndStart();
    }
}
