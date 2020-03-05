package com.shachar.coupons.dao;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.Company;
import com.shachar.coupons.beans.Coupon;
import com.shachar.coupons.enums.CouponscCategory;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponDao {

	private Coupon coupon;

	public CouponDao() {
		super();
		this.coupon = new Coupon();
	}

	public long createCoupon(Coupon coupon) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "INSERT INTO coupons (price, title, companyId, maximumAmountOfPurchases,expriationDate,"
					+ " couponcategory, creationdate) VALUES(?,?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setFloat(1, coupon.getPrice());
			preparedStatement.setString(2, coupon.getTitle());
			preparedStatement.setLong(3, coupon.getCompanyId());
			preparedStatement.setInt(4, coupon.getMaximumAmountOfPurchases());
			preparedStatement.setDate(5, coupon.getExpriationDate());
			preparedStatement.setString(6, coupon.getCouponCategory().toString());
			preparedStatement.setDate(7, coupon.getCreationCouponDate());
			

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long couponId;
				couponId = resultSet.getLong(1);
				return couponId;
			}

			throw new ApplicationException(ErrorTypes.FAIL_TO_GENERATE_ID,"Couldn`t generate coupon ID");		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to create new coupon", e);

		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Coupon getCouponByCouponId(long Id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT *FROM coupons WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, Id);

			preparedStatement.execute();


			ResultSet resultSet = preparedStatement.getResultSet();	

			if(resultSet.next()) {
				return createCouponFromeResultSet(resultSet);
			}
			throw new ApplicationException(ErrorTypes.ID_WAS_NOT_FOUND, "coupon is not found");			}


		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get coupon", e);		
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	private Coupon createCouponFromeResultSet(ResultSet resultSet) throws SQLException {
		
		long couponId = resultSet.getLong("id");
		float price = resultSet.getFloat("price");
		String title = resultSet.getNString("title");
		long companyId = resultSet.getLong("companyId");
		int maximumAmountOfPurchases = resultSet.getInt("maximumAmountOfPurchases");
		Date expriationDate = resultSet.getDate("expriationDate");
		CouponscCategory couponCategory = CouponscCategory.valueOf(resultSet.getString("couponcategory"));
		Date CreationCouponDate = resultSet.getDate("creationdate");




		return new Coupon(couponId, price, title, companyId, maximumAmountOfPurchases, expriationDate, couponCategory,CreationCouponDate);
	}
	
	public void deleteCoupon(long id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`coupons` WHERE (`id` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete coupon", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE coupons SET title=?, price=?, maximumamountofpurchases=?, expriationdate=?, couponcategory=?, creationdate=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, coupon.getTitle());
			preparedStatement.setFloat(2, coupon.getPrice());
			preparedStatement.setInt(3, coupon.getMaximumAmountOfPurchases());
			preparedStatement.setDate(4, coupon.getExpriationDate());
			preparedStatement.setString(5,coupon.getCouponCategory().toString());
			preparedStatement.setDate(6, coupon.getCreationCouponDate());
			preparedStatement.setLong(7, coupon.getCouponId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update coupon", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	public ArrayList<Coupon> getAllCoupons() throws ApplicationException {
		ArrayList<Coupon> couponList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {

				couponList.add(createCouponFromeResultSet(resultSet));
			}
			return couponList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get all coupons", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	public boolean isCouponExistByCouponId(long id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.coupons WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setLong(1, id);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if coupon exist by couponId", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}
	
	public boolean isCompanyExistByCompanyId (long companyId) throws ApplicationException {
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
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if company exist by companyId", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}
	
	public List<Coupon> getCouponByComanyId(Long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE companyid=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the query
			preparedStatement.executeQuery();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Coupon> coupons = new ArrayList<>();
			while (resultSet.next()) {
				Coupon coupon = createCouponFromeResultSet(resultSet);
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			 
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get coupons",e);

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	public void deleteCouponsCompanies(long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`coupons` WHERE (`Companyid` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, companyId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete coupon", e);	
		} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public List<Coupon> getCompanyCoupons(long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons WHERE companyid=?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, companyId);

			preparedStatement.executeQuery();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Coupon> coupons = new ArrayList<>();
			while (resultSet.next()) {
				Coupon coupon = createCouponFromeResultSet(resultSet);
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			 
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get coupons",e);

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public List<Coupon> getCouponsByCategory(CouponscCategory category) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons WHERE couponcategory=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, category.toString());

			preparedStatement.executeQuery();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Coupon> coupons = new ArrayList<>();
			
			while (resultSet.next()) {
				Coupon coupon = createCouponFromeResultSet(resultSet);
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			 
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get coupons", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	
	public List<Coupon> getCompanyCoupons(float maxPrice) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
		
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons WHERE price<?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setFloat(1, maxPrice);

			preparedStatement.executeQuery();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Coupon> coupons = new ArrayList<>();
			while (resultSet.next()) {
				Coupon coupon = createCouponFromeResultSet(resultSet);
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			 
		
			throw new ApplicationException( ErrorTypes.GENERAL_ERROR, "Failed to get coupons",e);

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void updateAmpuntOfCoupons(long couponId,int newAmountOfCoupons ) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE coupons SET  maximumamountofpurchases=? WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, newAmountOfCoupons);
			preparedStatement.setLong(2, couponId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update Amount of coupons", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}		
	}

	public List<Coupon> getPurchasedCoupons() throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT distinct coupons.id, coupons.title, coupons.price, coupons.companyid,"
					+ " coupons.maximumamountofpurchases, coupons.creationdate, coupons.expriationdate, coupons.couponcategory FROM coupons\r\n" + 
					"INNER JOIN purchases ON coupons.id = purchases.couponid ";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();

			List<Coupon> coupons = new ArrayList<>();

			while (resultSet.next()) {
				Coupon coupon = createCouponFromeResultSet(resultSet);
				coupons.add(coupon);
			}
			return coupons;

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.INVALID_COUPONS, "Failed to get coupons", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
		
	}
	}

	





	


