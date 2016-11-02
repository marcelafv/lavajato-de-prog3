package leitor;

import java.util.Scanner;

public class Candidato implements Comparable<Candidato>{

	/**
	 * @param args
	 */
	
	private int index, numCandidato;
	private String nome;
	private String[] partido;   // partido[0] é Partido e partido[1] é Coligação  ( divisão feita pelo split() )
	private Double numVotos;
	private String porcentagemVotos;
	
	public int getIndex() {
		return index;
	}

	public int getNumCandidato() {
		return numCandidato;
	}

	public String getNome() {
		return nome;
	}

	public String[] getPartido() {
		return partido;
	}
	
	public Double getNumVotos() {
		return numVotos;
	}

	public String getPorcentagemVotos() {
		return porcentagemVotos;
	}
	
	
	public void lerCandidato(Scanner entrada) {
		/* Impressões de teste comentadas */
		
		index = Integer.parseInt(entrada.next());
        //System.out.println("Index: "+index);
        numCandidato = Integer.parseInt(entrada.next());
        //System.out.println("Numero: "+numCandidato);
        nome = entrada.next();
        //System.out.println("Nome: "+nome);
        partido = entrada.next().split("-");
        //System.out.println("Partido: "+partido);
        //coligacao = entrada.next();
        numVotos = Double.parseDouble(entrada.next());
        //System.out.println("Numero de votos: "+numVotos);
        porcentagemVotos = entrada.next();
       	//System.out.println("Porcentagem: "+porcentagemVotos);
	}
	
	@Override
	public String toString() {
		if (getPartido().length == 1) return getIndex()+" - "+getNome()+" ("+getPartido()[0]+", "+getNumVotos()+" votos)";
		else return getIndex()+" - "+getNome()+" ("+getPartido()[0]+", "+getNumVotos()+" votos)	- Coligação: "+getPartido()[1];
	}
	
	// Comparador por índice
	@Override
	public int compareTo(Candidato can) {
	        return index - can.index;
	}
	
	/*
	// Comparador por número de votos
	public static class ComparadorVotos implements Comparator { 
		public int compare(Object o1, Object o2) { 
			return (((Candidato)o1).numVotos - ((Candidato)o2).numVotos); 
		} 
	} 
	*/
}
