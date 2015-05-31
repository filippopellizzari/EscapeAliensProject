package rmi;

import connection.ViewForPlayer;

public interface GetView {
	ViewForPlayer getView(String MapName, String TypeMap);
}
