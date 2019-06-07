package com.seminario;

public class Bolo implements Sobremesa{
	
	protected Confeito confeito;

	public Bolo(Confeito confeito) {
		this.confeito = confeito;
	}

	@Override
	public void fazerSobremesa() {
		System.out.println("Fazendo bolo");
		confeito.confeitar();
	}
	
}
