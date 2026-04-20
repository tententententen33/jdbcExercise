package jdbcExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import config.ConnectionFactory;

public class basics_test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("------検索する社員番号の範囲を入力-----");
		System.out.println();
		
//		System.out.println("検索する社員番号の最小範囲を入力してください（半角数字のみ打ちこみ可能）");
//		int min_TargetID = sc.nextInt();
		
		int min_TargetID;

		while (true) {
			System.out.println("検索する社員番号の最小範囲を入力してください（0以上の半角数字）");

			if (!sc.hasNextInt()) {
				System.out.println("入力が正しくありません。半角数字で入力してください。");
				sc.next();
				continue;
			}

			min_TargetID = sc.nextInt();

			if (min_TargetID < 0) {
				System.out.println("0未満の数値は入力できません。もう一度入力してください。");
				continue;
			}

			break;
		}
		
//		System.out.println("検索する社員番号の最大範囲を入力してください（半角数字のみ打ちこみ可能）");
//		int max_TargetID = sc.nextInt();
		
		int max_TargetID;
		while (true) {
			System.out.println("検索する社員番号の最大範囲(MAX:206)を入力してください（半角数字で入力）");

			if (sc.hasNextInt()) {
				max_TargetID = sc.nextInt();
				break;
			} else {
				System.out.println("入力が正しくありません。半角数字で入力してください。");
				sc.next(); 
			}
		}
		
		System.out.println("-----検索する方の名前の入力-----");
		System.out.println();
		
		System.out.println("検索する方の名前を半角アルファベットで入力してください(大文字、小文字両方可能）");
		String targetName = sc.next();
		
		String sql = "SELECT "
				+ "EMPLOYEE_ID, "
				+ "FIRST_NAME || ' ' || LAST_NAME FULL_NAME, "
				+ "SALARY "
				+ "FROM EMPLOYEES "
				+ "WHERE EMPLOYEE_ID >= ? AND EMPLOYEE_ID <= ? "
				+ "AND UPPER(FIRST_NAME) || ' ' || UPPER(LAST_NAME) LIKE UPPER(?) "
				+ "ORDER BY EMPLOYEE_ID ASC";
		
		int loopCount = 0;
		
		try(Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, min_TargetID);
			preparedStatement.setInt(2, max_TargetID);
			preparedStatement.setString(3, "%" + targetName + "%");
			
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					System.out.printf("EMPLOYEEID = %d, FULLNAME = %s, SALARY = %d%n", 
							resultSet.getInt("EMPLOYEE_ID"), 
							resultSet.getString("FULL_NAME"), 
							resultSet.getInt("SALARY"));
					loopCount++;
				}
			}
			
			if(loopCount == 0) {
				System.out.println("検索対象が存在しません");
			}
			
		} catch (SQLException e) {
//			System.out.println("接続に失敗しました");
			System.out.println("検索時にエラーが発生しました");
			System.out.println(e.getMessage());
		}
		sc.close();
	}
}
