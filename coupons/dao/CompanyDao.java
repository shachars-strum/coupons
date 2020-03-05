package com.shachar.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.enums.CompanyType;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.utils.JdbcUtils;

public class CompanyDao  {

	public long createCompany(Company company) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "INSERT INTO companies (companyname, address, phone, type) VALUES(?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getAdress());
			preparedStatement.setString(3, company.getPhone());
			preparedStatement.setString(4, company.getType().toString());


			preparedStatement.executeUpdate();


			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long companyId;
				companyId = resultSet.getLong(1);
				return companyId;
			}

			throw new ApplicationException(ErrorTypes.FAIL_TO_GENERATE_ID,"Couldn`t generate company ID");		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to create new company", e);

		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Company getCompanyByCompanyId(long Id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT *FROM companies WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, Id);

			preparedStatement.execute();


			ResultSet resultSet = preparedStatement.getResultSet();	

			if(resultSet.next()) {
				return createCompanyFromeResultSet(resultSet);
			}
			throw new ApplicationException(ErrorTypes.ID_WAS_NOT_FOUND, "company is not found");			}


		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get company", e);		
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	private Company createCompanyFromeResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("id");
		String companyName = resultSet.getString("companyname");
		String adress = resultSet.getString("address");
		String phone = resultSet.getString("phone");
		CompanyType type = CompanyType.valueOf(resultSet.getString("type"));

		return new Company (id, companyName, adress, phone, type);
	}


	public void deleteCompany(long id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`companies` WHERE (`id` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete company", e);	

		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void upDateCompany(Company company) throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE companies SET companyname=?, address=?, phone=?, type=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getAdress());
			preparedStatement.setString(3, company.getPhone());
			preparedStatement.setString(4, company.getType().toString());
			preparedStatement.setLong(5, company.getCompanyId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update coupon", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<Company> getAllCompanies() throws ApplicationException {
		ArrayList<Company> companyList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM companies";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {

				companyList.add(createCompanyFromeResultSet(resultSet));
			}
			return companyList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get all companeis", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}


	public boolean isCompanyExistByCompanyName(String companyName) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.companies WHERE companyname=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, companyName);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if company exist by companyName", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public boolean isCompanyExistByCompanyId(long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.companies WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if company exist", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}


}





