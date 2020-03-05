package com.shachar.coupons.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.beans.Customer;
import com.shachar.coupons.utils.JdbcUtils;

public class CustomerDao {

	public long createCustomer(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "INSERT INTO customers (id, amountofkids, ismaried, phone, dateofbirth,address) VALUES(?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setLong(1,customer.getCustomerId());
			preparedStatement.setInt(2, customer.getAmountOfKids());
			preparedStatement.setBoolean(3, customer.isMaried());
			preparedStatement.setString(4, customer.getPhone());
			preparedStatement.setDate(5, customer.getDateOfBirth());
			preparedStatement.setString(6, customer.getAdress());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long customerId;
				customerId = resultSet.getLong(1);
				return customerId;
			}

			throw new ApplicationException(ErrorTypes.FAIL_TO_GENERATE_ID,"Couldn`t generate customer ID");		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to create new customer", e);

		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Customer getCustomerByCustomerId(long customerId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT *FROM customers WHERE Id = ?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, customerId);

			preparedStatement.execute();


			ResultSet resultSet = preparedStatement.getResultSet();	

			if(resultSet.next()) {
				return createCustomerFromeResultSet(resultSet);
			}
			throw new ApplicationException(ErrorTypes.ID_WAS_NOT_FOUND, "customer id is not found");	
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get customer", e);		
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	private Customer createCustomerFromeResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("id");
		int amountOfKids = resultSet.getInt("amountOfKids");
		boolean isMaried = resultSet.getBoolean("ismaried");
		String phone = resultSet.getString("phone");
		Date dateOfBirth = resultSet.getDate("dateOfBirth");
		String address = resultSet.getString("address");




		return new Customer (id, isMaried, amountOfKids, dateOfBirth, address, phone );
	}


	public void deleteCustomer(long id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`customers` WHERE (`id` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete customer", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	public void upDateCustomer(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE customers SET amountofkids=?, ismaried=?, phone=?, dateofbirth=?, address=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, customer.getAmountOfKids());
			preparedStatement.setBoolean(2, customer.isMaried());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setDate(4, customer.getDateOfBirth());
			preparedStatement.setString(5, customer.getAdress());
			preparedStatement.setLong(6,customer.getCustomerId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update coupon", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	
	public boolean isCustomerExistByCustomerId(long customerId) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.customers WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, customerId);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if customer exist by customer id", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}
	
	public ArrayList<Customer> getAllCustomers() throws Exception {
		ArrayList<Customer> customersList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM customers";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {

				customersList.add(createCustomerFromeResultSet(resultSet));
			}
			return customersList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get all customers", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}


}
