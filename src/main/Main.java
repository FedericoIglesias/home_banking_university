package main;

import app.ManagerPanel;

public class Main {
	ManagerPanel manager;

	public static void main(String[] args) {
		Main prin = new Main();
		prin.iniciarManager();
		prin.showFrame();
	}

	public void iniciarManager() {
		manager = new ManagerPanel();
		manager.makeManager();
		manager.makeLoginPanel();	
	}

	public void showFrame() {
		manager.showFrame();
	}
}
