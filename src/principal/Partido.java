package principal;

import java.util.LinkedList;
import java.util.List;

public class Partido implements Comparable<Partido>{
		
		private String nome;
		private int votos;
		private LinkedList<Candidato> candidatos;
		
		/* Setters e Getters */
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
			int nvotos = 0;
			for (Candidato candidato : this.candidatos){
					nvotos = nvotos + candidato.getNumVotos();
			}
			return nvotos;
		}
		
		/* Adição do número de votos do candidato na contagem total do partido */
		public void addVotos(int votos) {
			this.votos = this.votos + votos;
		}
		
		/* Adição de um candidato pertencente ao partido */
		public boolean addCandidato(Candidato c){
			this.addVotos(c.getNumVotos());
			return candidatos.add(c);
		}
		
		public LinkedList<Candidato> getCandidatos(){
			return (LinkedList<Candidato>) candidatos.clone();
		}
		
		/* Contagem do número de candidatos eleitos do partido */
		public int getNEleitos(){
			int eleitos = 0;
			
			for (Candidato candidato : this.candidatos){
				if(candidato.isEleito()) eleitos++;
			}
			
			return eleitos;
		}
		
		public boolean equals(	Partido c){
			if(this.getNome().equals(c.getNome())) return true;
			else return false;			
		}
		
		@Override
		public String toString() {
			if (this.getVotos() == 0 || this.getVotos() == 1) return getNome()+ ", " + this.getVotos() + " votos, " + getNEleitos()+ " candidato eleito";
			else return getNome()+ ", " + this.getVotos() + " votos, " + getNEleitos()+ " candidatos eleitos";
		}

		// Comparador por votos
		@Override
		public int compareTo(Partido p) {
		        return  p.getVotos() - this.getVotos();
		}
}
