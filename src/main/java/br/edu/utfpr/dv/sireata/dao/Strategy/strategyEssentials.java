package br.edu.utfpr.dv.sireata.dao.Strategy;

import java.sql.SQLException;
import java.util.List;





public abstract class strategyEssentials<Essentials> {

	
	public abstract int buscaPorID(int id) throws SQLException;
	
	public abstract int salvar(Essentials ess) throws SQLException;
	
	public abstract List<Essentials> listarTodos(boolean apenasAtivos) throws SQLException;
	
	public abstract void excluir(int id) throws SQLException;
	
	
	
}
