package teste;

import sincronizacaoreceita.SincronizacaoReceita;
import sincronizacaoreceita.ReceitaService;

public class Test {

	public static void main(String[] args) {
		System.out.println("Receita Service access is "+ ((SincronizacaoReceita.SyncWithService(new String[] {"0101", "123456", "100.50", "A"})) ? "ok.":"failed."));
	}
}
