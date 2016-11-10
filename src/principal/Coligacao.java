
	package principal;

	import java.util.LinkedList;
	import java.util.List;

	public class Coligacao implements Comparable<Coligacao>{
		
		private String nome;
		private int votos;
		private LinkedList<Partido> partidos;
		
		public Coligacao(){
			partidos = new LinkedList<Partido>();
		}
		
		public Coligacao(String s){
			this.nome = s;
			partidos = new LinkedList<Partido>();
		}
		
		/* Setters e Getters */
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public List<Partido> getPartidos(){
			return (List<Partido>) partidos.clone();
		}

		public int getVotos() {
			int nvotos = 0;
			for (Partido partido : partidos){
				for (Candidato candidato : partido.getCandidatos()){
						nvotos = nvotos + candidato.getNumVotos();
				}
			}
			return nvotos;
		}
		
		/* Adição de votos ao total da coligação */
		public void addVotos(int votos) {
			this.votos = this.votos + votos;
		}
		
		/* Adição de um partido à lista da coligação */
		public boolean addPartido(Partido p){
			for(Partido partido : this.partidos){
				if(partido.getNome().equals(p.getNome())){	
					return true;
				}
			}
			partidos.add(p);
			return true;
		}
	
		/* Contagem do número de candidatos eleitos da coligação */
		public int getNEleitos(){
			int eleitos = 0;
			
			for (Partido partido : this.partidos){
				eleitos = eleitos + partido.getNEleitos();
			}
			
			return eleitos;
		}
		
		public boolean equals(Coligacao c){
			if(this.getNome().equals(c.getNome())) return true;
			else return false;		
		}
		
		@Override
		public String toString() {
			if (this.getVotos() == 0 || this.getVotos() == 1) return getNome()+ ", " + getVotos() + " votos, " + getNEleitos() + " candidato eleito.";
			else return getNome()+ ", " + getVotos() + " votos, " + getNEleitos() + " candidatos eleitos.";			
		}
		
		@Override
		public int compareTo(Coligacao p) {
		        return  p.getVotos() - this.getVotos();
		}

	}
