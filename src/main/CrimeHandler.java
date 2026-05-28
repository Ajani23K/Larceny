package main;

import java.util.ArrayList;

import entity.Entity;

public class CrimeHandler {
	GamePanel gp;
	public int crimerating = 0;
	public ArrayList<Entity> savedInventory = new ArrayList<>();
	public ArrayList<String> crimesCommited = new ArrayList<>();
	public ArrayList<Entity> savedObjects = new ArrayList<>();
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
		String text = "";
		System.out.println("saved inventory size: "+savedInventory.size() +" player inventory size: "+inventory.size());
		if(savedInventory.size() < inventory.size()) {
			increaseCR();
			crimesCommited.add("Stealing");
			text = "You have been caught stealing";
			System.out.println("You have been caught stealing");
			gp.ui.showMessage(text);
		}
		clearSavedInventory();
		
	}
	public void addObject(Entity entity) {
		savedObjects.add(entity);
	}
	public void clearObject() {
		savedObjects.clear();
	}
	public ArrayList<Entity> sendObjects() {
		return savedObjects;
	}

}
