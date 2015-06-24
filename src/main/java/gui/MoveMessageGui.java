package gui;


import dto.DTOGame;

public class MoveMessageGui implements MessageGui{

	@Override
	public void receive(DTOGame dtoGame, RightPanel rp){
		rp.getLogPanel().getTextArea().append("<giocatore " + (dtoGame.getPlayerNumber()+1)
				+ ">" + " mosso con successo in settore "
				+ dtoGame.getCoordinate(dtoGame.getPlayerNumber()));
		if (dtoGame.getHatchCardColor() != null) {
			switch (dtoGame.getHatchCardColor()) {
			case RED:
				rp.getMessagePanel().getTextArea().append("<giocatore "
								+ (dtoGame.getPlayerNumber()+1)
								+ ">"
								+ " pesca carta scialuppa rossa:\n il giocatore non si salva "
								+ "e il settore rimane bloccato");
				break;
			case GREEN:
				rp.getMessagePanel().getTextArea().append("<giocatore "
								+ (dtoGame.getPlayerNumber()+1)
								+ ">"
								+ " pesca carta scialuppa verde:\n il giocatore ha vinto "
								+ "e il settore rimane bloccato");
				break;
			}
		}
	}
}
