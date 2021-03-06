package br.edu.utfpr.dv.sireata.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dv.sireata.dao.Strategy.strategyEssentials;
import br.edu.utfpr.dv.sireata.model.Pauta;

public  class PautaDAO implements strategyEssentials<Pauta> {
	
	public Pauta buscarPorId(int id) throws SQLException{
		
		try(
			Connection conn = ConnectionDAO.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pautas WHERE idPauta = ?");
				){
			stmt.setInt(1, id);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()){
					return this.carregarObjeto(rs);
				}else{
					return null;
				}
			}

		
		}
	}
	
	public List<Pauta> listarPorAta(int idAta) throws SQLException{

		try(Connection conn =ConnectionDAO.getInstance().getConnection();
				Statement  stmt = conn.createStatement();
				){
			

			try(ResultSet rs = stmt.executeQuery("SELECT * FROM pautas WHERE idAta=" + String.valueOf(idAta) + " ORDER BY ordem")){
				List<Pauta> list = new ArrayList<Pauta>();
				
				while(rs.next()){
					list.add(this.carregarObjeto(rs));
				}
				
				return list;
			}
		
			
		}
	}
	
	public int salvar(Pauta pauta) throws SQLException{
		boolean insert = (pauta.getIdPauta() == 0);
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = ConnectionDAO.getInstance().getConnection()){
			
			if(insert){
				stmt = conn.prepareStatement("INSERT INTO pautas(idAta, ordem, titulo, descricao) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			}else{
				stmt = conn.prepareStatement("UPDATE pautas SET idAta=?, ordem=?, titulo=?, descricao=? WHERE idPauta=?");
			}
			
			stmt.setInt(1, pauta.getAta().getIdAta());
			stmt.setInt(2, pauta.getOrdem());
			stmt.setString(3, pauta.getTitulo());
			stmt.setString(4, pauta.getDescricao());
			
			if(!insert){
				stmt.setInt(5, pauta.getIdPauta());
			}
			
			stmt.execute();
			
			if(insert){
				rs = stmt.getGeneratedKeys();
				
				if(rs.next()){
					pauta.setIdPauta(rs.getInt(1));
				}
			}
			
			return pauta.getIdPauta();
		}finally {
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((rs != null) && !rs.isClosed())
				rs.close();
		}
		
	}
	
	public void excluir(int id) throws SQLException{
		
		
		try(
				Connection conn = ConnectionDAO.getInstance().getConnection();
				Statement stmt = conn.createStatement()
			){
			
		
			stmt.execute("DELETE FROM pautas WHERE idPauta=" + String.valueOf(id));
		}
	}
	
	private Pauta carregarObjeto(ResultSet rs) throws SQLException{
		Pauta pauta = new Pauta();
		
		pauta.setIdPauta(rs.getInt("idPauta"));
		pauta.getAta().setIdAta(rs.getInt("idAta"));
		pauta.setOrdem(rs.getInt("ordem"));
		pauta.setTitulo(rs.getString("titulo"));
		pauta.setDescricao(rs.getString("descricao"));
		
		return pauta;
	}

}
