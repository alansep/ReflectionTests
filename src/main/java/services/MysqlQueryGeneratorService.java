package services;

import interfaces.crudgenerators.DeleteQueryGenerator;
import interfaces.crudgenerators.InsertQueryGenerator;
import interfaces.crudgenerators.SelectQueryGenerator;
import interfaces.crudgenerators.UpdateQueryGenerator;

public class MysqlQueryGeneratorService<Tipo> implements SelectQueryGenerator<Tipo>, InsertQueryGenerator<Tipo>,
		UpdateQueryGenerator<Tipo>, DeleteQueryGenerator<Tipo> {

	public String gerarDelete(Tipo objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarDelete(Tipo objeto, String clausula) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarUpdate(Tipo objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarUpdate(Tipo objeto, String clausula) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarInsert(Tipo objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarSelect(Tipo objeto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarSelect(Tipo objeto, String clausula) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
