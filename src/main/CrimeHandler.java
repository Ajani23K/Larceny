package main;

import java.util.ArrayList;

import entity.Entity;

public class CrimeHandler {
	GamePanel gp;
	int crimerating = 0;
	public ArrayList<Entity> savedInventory = new ArrayList<>();
	public ArrayList<String> crimesCommited = new ArrayList<>();
	
	public CrimeHandler(GamePanel gp) {
		this.gp = gp;
		
	}
	public void saveInventory(ArrayList<Entity> inventory) {
		savedInventory.addAll(inventory);
	}
	public void clearSavedInventory() {
		savedInventory.clear();
	}
	public void increaseCR() {
		crimerating++;
		if(crimerating > 5) {
			crimerating = 5;
		}
	}
	public void decreaseCR() {
		crimerating--;
		if(crimerating < 0) {
			crimerating = 0;
		}
	}
	public void checkStealing(ArrayList<Entity> inventory) {
		if(savedInventory.size() < inventory.size()) {
			increaseCR();
			crimesCommited.add("Stealing");
			gp.ui.showMessage("You have been caught stealing");
			System.out.println("You have been caught stealing");
		}
		clearSavedInventory();
	}

}
