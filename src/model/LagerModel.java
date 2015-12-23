package model;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class LagerModel extends Observable implements TreeModel, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7091360518037662600L;
	private Lager root;
	public LagerModel(Lager root){
		this.root = root;
	}
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof Lager){
			Lager lager = (Lager) parent;
			if((lager.getChildList().size() - 1) >= index){
				Object childObjekt = lager.getChildList().get(index);
				return childObjekt;
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof Lager){
				Lager lager = (Lager) parent;
				if(lager.getChildList().isEmpty()){
					return 0;
				}
				else{
					return lager.getChildList().size();
				}
		}
		return -1;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lager getRoot() {
		return root;
	}

	@Override
	public boolean isLeaf(Object node) {
		if(node instanceof Lager){
			Lager lager = (Lager) node;
			if(lager.getChildList().isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	public boolean isRoot(Lager object){
		if(object.equals(root)){
			return true;
		}
		return false;
	}
	public void lagerLoeschen(Lager lager){
		if(lager.getVater() != null){
			Lager vater = lager.getVater();
			vater.getChildList().remove(lager);
			setChanged();
			notifyObservers();
		}
	}
	public void lagerHinzufuegen(Lager vater, Lager hinzuzufuegendesLager){
		vater.getChildList().add(hinzuzufuegendesLager);
		setChanged();
		notifyObservers();
	}
}
