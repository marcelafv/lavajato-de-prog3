package principal;

import java.util.TreeSet;

public class Eleicao {
	
	private static Eleicao instancia;
	private TreeSet<Candidato> listaCandidatos = new TreeSet<Candidato>();
	
	private Eleicao(){
		
	}
	
	public boolean addCandidato(Candidato c){
		
		return listaCandidatos.add(c);
	}

}
