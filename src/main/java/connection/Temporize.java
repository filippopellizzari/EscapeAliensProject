package connection;

public class Temporize implements Runnable {

	private int time;
	private DatabaseCreateGame database;
	private TypeOfMap mapType;
	
	public Temporize(int time, DatabaseCreateGame databaseGame, TypeOfMap mapType) {
		this.time=time;
		this.database=databaseGame;
		this.mapType=mapType;
	}

	@Override
	public void run() {
		try {
			System.out.println("Sono il thread creato per cronometrare la partita mi metto a dormire");
			Thread.sleep(time*1000);
			System.out.println("Sono il thread creato per cronometrare la partita mi sveglio");
			for(int i=0;i<database.getPlayerWithRelativeConnection().size();i++) {		//rimuovi la giusta partita dal database
				if(database.getPlayerWithRelativeConnection().get(i).getMapType()==mapType) {
					database.blockGame(i);
					System.out.println("Sono il thread creato per cronometrare la partita mi metto a dormire in attesa che sia creata");
					database.getPlayerWithRelativeConnection().get(i).deleteGame();
					System.out.println("Sono il thread creato per cronometrare la partita mi sveglio dopo che è stata creata");
					database.removeGame(i); 		//rimuovilo
				}
			}
			System.out.println("Sono il thread creato per cronometrare la partita ho finito");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
