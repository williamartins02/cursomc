package com.williamartins.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//class auxiliar
public class URL {

	// metodo para descodificar um paramentro
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			return "";
		}
	}
/*Metodo que pega string e converte para uma lista de numeros inteiros, e quebra em pedacinhos
 * ele vai te que pegar a string que é monte de numero separado em virgula quebra os numero em varios pedacinhos
 * e converte cada pedaço em numero inteiro e adicionar na minha lista, tudo na URL.
 * */
	public static List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");// split quebra a string em pedaçinho de acordo com o parametro q passou
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;

		/* ou usar esse metodo que faz a msm funçao de cima */
		// return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}
