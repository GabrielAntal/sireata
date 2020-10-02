package br.edu.utfpr.dv.sireata.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class pautaTests {
	
	
	@Test
	void compId() {
		Pauta pa = new Pauta();
		
		pa.setIdPauta(17);
		
		int num = pa.getIdPauta();
		
		assertEquals(17, num);
	} 
	
	
	@Test
	void compDesc() {
		Pauta pa = new Pauta();
		
		pa.setDescricao("Ciência sobre terra plana");
		
		String num = pa.getDescricao();
		String desc = "Ciência sobre terra plana";
		
		assertEquals(desc, num);
	}
	
	
	@Test
	void compTitulo() {
		Pauta pa = new Pauta();
		
		pa.setTitulo("Terra é Plana");
		
		String num = pa.getTitulo();
		String til = "Terra é um Geóide ";
		
		assertEquals(til, num);
	} 

}
