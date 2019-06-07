package com.seminario;

public class Confeitaria {

	public static void main(String[] args) {
		Sobremesa cupcake = new Cupcake(new Chantilly());
		cupcake.fazerSobremesa();
		
		cupcake = new Cupcake(new Granulado());
		cupcake.fazerSobremesa();
		
		Sobremesa bolo = new Bolo(new Granulado());
		bolo.fazerSobremesa();
	}

}
