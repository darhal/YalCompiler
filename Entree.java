package yal.outils.tableDesSymboles;

public abstract class Entree {
	
	private String idf;
	private int noLigne;
	
	public Entree(String i, int noLigne) {
		idf = i;
		this.noLigne = noLigne;
	}

	public String getIdf() {
		return idf;
	}

	public void setIdf(String idf) {
		this.idf = idf;
	}
	
	public int getNoLigne() {
		return noLigne;
	}
	
	/**
	 * @return Retourne le type de la varriable (ici on gère que des entiers)
	 */
	public String getType() {
		return "int";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		
		if(this instanceof EntreeVar && (obj.getClass() == EntreeParam.class || obj.getClass() == EntreeVar.class  && ((Entree) obj).getIdf().equals(idf)))
			return true;
		
		if(this instanceof EntreeProg && obj.getClass() == EntreeProg.class && ((Entree) obj).getIdf().equals(idf) 
				&& ((EntreeProg) obj).getNbParam() == ((EntreeProg) this).getNbParam())
			return true;
		if(this instanceof EntreeParam && obj.getClass() == EntreeParam.class && ((Entree) obj).getIdf().equals(idf))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		// Ajouter le truc de bloc dans le hash
		return idf.hashCode();
	}

}