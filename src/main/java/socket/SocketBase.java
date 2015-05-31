package socket;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class SocketBase {
	protected Scanner in;
	protected PrintWriter out;
	protected int Port;
	protected String Host;
	public abstract void startClient();
}
