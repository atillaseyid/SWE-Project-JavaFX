package Models;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GameState {


    private String timeInText;

    private String player1;
    private String player2;

    private String winner;

    private int turn;
}
