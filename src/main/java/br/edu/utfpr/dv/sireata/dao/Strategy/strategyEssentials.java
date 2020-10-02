package br.edu.utfpr.dv.sireata.dao.Strategy;

import java.sql.SQLException;
import java.util.List;





public abstract interface strategyEssentials<Essentials> {

	
	public  int buscaPorID(int id) throws SQLException;
	
	public  int salvar(Essentials ess) throws SQLException;
	
	public  List<Essentials> listarTodos(boolean apenasAtivos) throws SQLException;
	
	public  void excluir(int id) throws SQLException;
	
	
	
}
