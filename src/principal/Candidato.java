package principal;

import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

public class Candidato implements Comparable<Candidato>{

	/**
	 * @param args
	 */
	
	private int index, numCandidato;
	private String nome;
	private Partido partido;
	private Coligacao coligacao;
	private int numVotos;
	private String porcentagemVotos;
	private boolean eleito;
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	public int getNumCandidato() {
		return numCandidato;
	}

	public String getNome() {
		return nome;
	}
	
	public void setPartido(String s){
		Partido p = Eleicao.getInstance().containsPartido(s);
		p.addCandidato(this);
		this.partido = p;
	}

	public Partido getPartido() {
		return partido;
	}
	
	public void setColigacao(String s){
		Coligacao c = Eleicao.getInstance().containsColigacao(s);
		c.addPartido(this.partido);
		this.coligacao = c;
	}
	
	public Coligacao getColigacao() {
		return coligacao;
	}
	
	public int getNumVotos() {
		return numVotos;
	}

	public String getPorcentagemVotos() {
		return porcentagemVotos;
	}
	
	public boolean isEleito(){
		return eleito;
	}
	
	private void evalIndex(String s){
		if(s.charAt(0) == '*'){
			this.eleito = true;
			this.index = Integer.parseInt(s.substring(1,5));
		}else if(s.charAt(0) == '#'){
			this.eleito = false;
			this.index = Integer.parseInt(s.substring(1,5));
		}else{
			this.eleito = false;
			this.index = Integer.parseInt(s);
		}
	}
	
	
	public void lerCandidato(Scanner entrada) {
		//Variavel temporária para partidoString, partidoString[0] é Partido e partidoString[1] é Coligação  ( divisão feita pelo split() )
		String[] partidoString;
		NumberFormat f = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
		/* Impressões de teste comentadas */
		
		this.evalIndex(entrada.next());
        
		//System.out.println("Index: "+index);
		this.numCandidato = Integer.parseInt(entrada.next());
        //System.out.println("Numero: "+numCandidato);
		this.nome = entrada.next();
        //System.out.println("Nome: "+nome);
		partidoString = entrada.next().split("-");
		
		this.setPartido(partidoString[0]);
		//System.out.println("Partido: "+partido);
		//System.out.println(partidoString[1]);
		
		try{
			this.setColigacao(partidoString[1]);
		}catch(ArrayIndexOutOfBoundsException e){
			this.setColigacao(partidoString[0]);;
		}
		
        //coligacao = entrada.next();
		
		try{
		this.numVotos = f.parse(entrada.next()).intValue();
		}catch (ParseException e){}
		
		
		//System.out.println("Numero de votos: "+numVotos);
		this.porcentagemVotos = entrada.next();
       	//System.out.println("Porcentagem: "+porcentagemVotos);
	}
	
	@Override
	public String toString() {
		if (getColigacao() == null) return getNome()+" ("+getPartido().getNome()+", "+getNumVotos()+" votos)";
		else return getNome()+" ("+getPartido().getNome()+", "+getNumVotos()+" votos)	- Coligação: "+getColigacao().getNome();
	}
	
	// Comparador por votos
	@Override
	public int compareTo(Candidato can) {
	        return  can.numVotos - this.numVotos;
	}
	
	
	
	/*
	// Comparador por número de votos
	public static class ComparadorVotos implements Comparator { 
		public int compare(Object o1, Object o2) { 
			return (((Candidato)o1).numVotos - ((Candidato)o2).numVotos); 
		} 
	} 
	*/
	
	public boolean equals(Candidato c){
		if(this.getNome().equals(c.getNome())) return true;
		else return false;		
	}
}
