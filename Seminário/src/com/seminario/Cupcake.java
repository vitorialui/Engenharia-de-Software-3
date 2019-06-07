package com.seminario;

public class Cupcake implements Sobremesa {
	
	protected Confeito confeito;

	public Cupcake(Confeito confeito) {
		this.confeito = confeito;
	}

	@Override
	public void fazerSobremesa() {
		System.out.println("Fazendo cupcake");
		confeito.confeitar();
	}
}
