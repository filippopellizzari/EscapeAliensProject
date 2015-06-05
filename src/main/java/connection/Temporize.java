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
			Thread.sleep(60000);
			for(int i=0;i<database.getPlayerWithRelativeConnection().size();i++) {		//rimuovi la giusta partita dal database
				if(database.getPlayerWithRelativeConnection().get(i).getMapType()==mapType)
					database.removeGame(i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
