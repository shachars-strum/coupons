package com.shachar.coupons.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.beans.Purchase;
import com.shachar.coupons.utils.JdbcUtils;

public class PurchaseDao {

	public long createPurchase(Purchase purchase) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "INSERT INTO purchases (couponid,amountofcoupons, implementdate,customerid ) VALUES(?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setLong(1, purchase.getCouponId());
			preparedStatement.setInt(2, purchase.getAmountOfCoupons());
			preparedStatement.setDate(3, purchase.getImplementDate());
			preparedStatement.setLong(4, purchase.getCustomerId());



			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long purchaseId;
				purchaseId = resultSet.getLong(1);
				return purchaseId;
			}

			throw new ApplicationException(ErrorTypes.FAIL_TO_GENERATE_ID,"Couldn`t generate purchase ID");		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to create new purchase", e);

		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Purchase getPurchasebyPurchaseId(long Id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT *FROM purchases WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, Id);

			preparedStatement.execute();


			ResultSet resultSet = preparedStatement.getResultSet();	

			if(resultSet.next()) {
				return createPurchaseFromeResultSet(resultSet);
			}
			throw new ApplicationException(ErrorTypes.ID_WAS_NOT_FOUND, "purchase id is not found");	
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get purchase", e);		
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	private Purchase createPurchaseFromeResultSet(ResultSet resultSet) throws SQLException {
		long purchaseId = resultSet.getLong("id");
		Date implementDate = resultSet.getDate("implementdate");
		Long companyId = resultSet.getLong("couponid");
		Long customerId = resultSet.getLong("customerid");
		int amountOfCoupons = resultSet.getInt("amountofcoupons");


		return new Purchase (purchaseId, implementDate, companyId, customerId,amountOfCoupons );

	}

	public void deletePurchase(long id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`purchases` WHERE (`id` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete purchase", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}	
	}

	public void updatePurchase(Purchase purchase) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE purchases SET implementdate=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setDate(1, purchase.getImplementDate());
			preparedStatement.setLong(2,purchase.getPurchaseId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update coupon", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<Purchase> getAllPurchase() throws ApplicationException {
		ArrayList<Purchase> purchaseList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM purchases";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {

				purchaseList.add(createPurchaseFromeResultSet(resultSet));
			}
			return purchaseList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get all purchases", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public boolean isPurchaseExistByPurchaseId(long userId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.purchases WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, userId);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if the purchase exist", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public void deletePurchasesCustomer(long customerId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`purchases` WHERE (`customerid` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, customerId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete purchase", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}	
	}

	public void deletePurchasesCoupon(long couponId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`purchases` WHERE (`couponid` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, couponId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete purchase", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}	
	}

	public List<Purchase> getPurchasesCustomer(long customerId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM purchases WHERE customerid=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);

			// Executing the query
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();

			List<Purchase> purchases = new ArrayList<>();
			while (resultSet.next()) {
				Purchase purchase = createPurchaseFromeResultSet(resultSet);
				purchases.add(purchase);
			}
			return purchases;

		} catch (SQLException e) {


			throw new ApplicationException( ErrorTypes.GENERAL_ERROR, "Failed to get purchases",e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}


}





