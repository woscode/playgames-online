package com.wos.playgamesonline.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wos.playgamesonline.model.tictactoe.*;

@Controller
@SessionAttributes("game")
@RequestMapping("/")
public class PlayOnlineGamesController {
	
	static final Logger logger = LoggerFactory.getLogger(PlayOnlineGamesController.class);

	@GetMapping("/")
	public String tictactoe(@ModelAttribute("game") XOGame game) {
		return "tictactoe";
	}
	
	@PostMapping("/")
	public String tictactoeMove(
			@ModelAttribute("game") XOGame game,
			@RequestParam("square_id") String squareId, 
			@RequestParam(value="restart", required=false, defaultValue="false") boolean restart,
			@RequestParam(value = "player_move_first", required = false) boolean playerMoveFirst) {
		if (restart) {
			logger.info("Is player move first? : {}", playerMoveFirst);
			game.restart(playerMoveFirst);
			if (!game.isPlayerMoveFirst())
				game.move(4);
		} else {
			logger.info("Player move on {}", squareId);
			game.move(Integer.valueOf(squareId));
			game.randomMove();
			// game.move(Integer.valueOf(squareId));
		}
		return "tictactoe";
	}

	public String goodbye(SessionStatus status) {
	    status.setComplete();
	    return "tictactoe";
	 }

	
	@ModelAttribute("game")
	private XOGame tictactoeGame() {
		return new XOGame (
				new XOPlayer(0L, "nickname1", "password1", Mark.X),
				new XOPlayer(1L, "nickname2", "password1", Mark.O)
			);
	}
}
