package jdbcExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectionFactory;

//public class basics {
//	public static void main(String[] args) {
//		String sql = 
//				"SELECT FIRST_NAME || ' ' || LAST_NAME FULL_NAME FROM EMPLOYEES";
//		
//		try(Connection connection = ConnectionFactory.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//			
//			try(ResultSet resultSet = preparedStatement.executeQuery()) {
//				while (resultSet.next()) {
//					System.out.printf("FULL_NAME=%s%n",resultSet.getString("FULLNAME"));
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("検索時にエラーが発生しました");
//			System.out.println(e.getMessage());
//		}
//	}
//}

//public class basics {
//	public static void main(String[] args) {
//		String targetName = "K%";
//		
//		String sql = "SELECT FIRST_NAME || ' ' || LAST_NAME FULL_NAME, SALARY "
//					+ "FROM EMPLOYEES "
//					+ "WHERE SALARY >= 10000 AND LAST_NAME LIKE ? "
//					+ "ORDER BY SALARY DESC";
//		
//		try(Connection connection = ConnectionFactory.getConnection();
//			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//			
//			preparedStatement.setString(1, targetName);
//			
//			try(ResultSet resultSet = preparedStatement.executeQuery()) {
//				while (resultSet.next()) {
//					System.out.printf("FULLNAME = %s, SALARY = %d%n",
//							resultSet.getString("FULL_NAME"),
//							resultSet.getInt("SALARY"));
//				}
//			}
//		} catch (SQLException e) {
//			System.out.println("検索時にエラーが発生しました");
//			System.out.println(e.getMessage());
//		}
//	}
//}

public class basics {
	public static void main(String[] args) {
		
		String sql = "SELECT EMPLOYEE_ID "
					+ "FROM EMPLOYEES "
					+ "ORDER BY EMPLOYEE_ID DESC";
		
		try(Connection connection = ConnectionFactory.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					System.out.printf("EMPLOYEE_ID = %s%n",
							resultSet.getString("EMPLOYEE_ID"));
				}
			}
		} catch (SQLException e) {
			System.out.println("検索時にエラーが発生しました");
			System.out.println(e.getMessage());
		}
	}
}