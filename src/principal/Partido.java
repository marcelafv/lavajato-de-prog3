package principal;

import java.util.LinkedList;


public class Partido {
	
	private String nome;
	private int votos;
	private LinkedList<Candidato> candidatos;
	
	public Partido(){
		candidatos = new LinkedList<Candidato>();
	}
	
	public Partido(String s){
		this.nome = s;
		candidatos = new LinkedList<Candidato>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getVotos() {
		return votos;
	}
	public void addVotos(int votos) {
		this.votos = this.votos + votos;
	}
	
	public boolean addCandidato(Candidato c){
		return candidatos.add(c);
	}
	
	public LinkedList<Candidato> getCandidatos(){
		return (LinkedList<Candidato>) candidatos.clone();
	}
	
	public int getNEleitos(){
		int eleitos = 0;
		
		for (Candidato candidato : this.candidatos){
			if(candidato.isEleito()) eleitos++;
		}
		
		return eleitos;
	}
	
	public boolean equals(Partido c){
		if(this.getNome() == c.getNome()) return true;
		else return false;		
	}
	
	@Override
	public String toString() {
		return getNome()+ ", " + getVotos() + " votos , " + getNEleitos()+ " candidatos eleitos";
	}
	

}
