package main;

import app.ManagerPanel;
import db.TableManager;
import model.Admin;
import model.Client;

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
		//manager.makeLoginPanel();
		manager.makeClientPanel();

	}

	public void showFrame() {
		manager.showFrame();
	}
}
