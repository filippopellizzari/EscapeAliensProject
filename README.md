# Escape Aliens Project

Project Name : "Escape From The Aliens In Outer Space"

Authors: Filippo Pellizzari, Nicola Saporetti

Java implementation of the board game "Escape from the Aliens in Outer Space", with a client-server architecture. The software architecture adheres the MVC pattern and other useful design patterns (such as Strategy and Factory Method). The application includes both graphic and command line interface. 

## Instructions

To Play in CLI mode:
1. run class "Server" in package "connection";
2. run class "Client" in package "cli".

To Play in GUI mode: 
1. run class "Server" in package "connection" 
2. run class "ClientGui" in package "gui";
3. choose connection (RMI or Socket) through console;
4. choose map (Fermi, Galilei or Galvani) through console;
5. wait for a game available.

To start a game, at least two clients have to choose the same map.
 


