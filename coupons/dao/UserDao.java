package com.shachar.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shachar.coupons.application_exceptoon.ApplicationException;
import com.shachar.coupons.application_exceptoon.ErrorTypes;
import com.shachar.coupons.beans.User;
import com.shachar.coupons.enums.UserType;
import com.shachar.coupons.utils.JdbcUtils;

public class UserDao {
	private User user;


	public UserDao() {
		super();
		this.user = new User();

	}


	public long createUser(User user) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "INSERT INTO users (userName, passwordHash, usertype) VALUES(?,?,?)";

			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setInt(2, user.getPasswordHash());
			//preparedStatement.setLong(3, user.getCompanyId());
			preparedStatement.setString(3, user.getUserType().toString());


			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long userId;
				userId = resultSet.getLong(1);
				return userId;
			}
			throw new ApplicationException(ErrorTypes.FAIL_TO_GENERATE_ID,"Couldn`t generate User ID");		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to create new user", e);
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public User getUserByUserId(long Id) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "SELECT *FROM users WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, Id);

			preparedStatement.execute();


			ResultSet resultSet = preparedStatement.getResultSet();	

			if(resultSet.next()) {
				return createUserFromeResultSet(resultSet);
			}
			throw new ApplicationException(ErrorTypes.ID_WAS_NOT_FOUND, "User is not found");	
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get user", e);		
		} finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	private User createUserFromeResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("id");
		String userName = resultSet.getString("username");
		int passwordHash = resultSet.getInt("passwordHash");
		Long companyId = resultSet.getLong("companyId");
		UserType userType = UserType.valueOf(resultSet.getString("usertype"));




		return new User (id, userName, passwordHash, companyId, userType);
	}

	public void deleteUser(long id) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`users` WHERE (`id` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete user", e);	
			} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void upDateUser(User user) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "UPDATE users SET username=?, passwordHash=?, usertype=?, companyid=? WHERE id=?";
			preparedStatement = connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setInt(2, user.getPasswordHash());
			preparedStatement.setString(3, user.getUserType().toString());
			preparedStatement.setLong(4, user.getCompanyId());
			preparedStatement.setLong(5, user.getUserId());
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to update coupon", e);

		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<User> getAllUsers() throws ApplicationException {
		ArrayList<User> usersList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM users";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			while (resultSet.next()) {

				usersList.add(createUserFromeResultSet(resultSet));
			}
			return usersList;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to get all users", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public int getUserPasswordHash(String username) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT passwordhash FROM coupons.users WHERE username=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, username);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				int passwordhash = resultSet.getInt(1);
				return passwordhash;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed get password hash", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
		throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get users");
	}

	public boolean isUserExistByUserName(String userName) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.users WHERE username=?";

			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, userName);
			preparedStatement.executeQuery();

			ResultSet resultSet = preparedStatement.getResultSet();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if user exist by username", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public boolean isUsrExistByUserId(long userId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			String sqlStatement = "SELECT * FROM coupons.users WHERE id=?";

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
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to check if user exist", e);
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}
	
	public void deleteUserByCompanyId(long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			String sqlStatement = "DELETE FROM `coupons`.`users` WHERE (`companyid` = ?);";


			preparedStatement = connection.prepareStatement(sqlStatement);

			preparedStatement.setLong(1, companyId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorTypes.SQL_ERROR, "Failed to delete user", e);	
			} 
		finally {

			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}


	public List<User> getCompanyUsers(long companyId) throws ApplicationException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM users WHERE companyid=?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the query
			preparedStatement.executeQuery();
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				User user = createUserFromeResultSet(resultSet);
				users.add(user);
			}
			return users;

		} catch (SQLException e) {
			 
			// If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			throw new ApplicationException(ErrorTypes.GENERAL_ERROR, "Failed to get user",e);

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

}
