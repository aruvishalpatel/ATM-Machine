package com.aruvishal.atm_machine.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;

public class DBUtil {
	private static ResultSet rs;

	// private static final Logger LOGGER =
	// Logger.gerLogger(DBUtil.class.getName());
	public static boolean executInsertUpdateDelete(Connection con, String query) throws Exception {
		boolean status = false;
		PreparedStatement prepare = null;
		try {
			prepare = con.prepareStatement(query);
			final int result = prepare.executeUpdate();
			if (result > 0) {
				status = true;
			}
		} catch (final SQLException e) {
			// LOGGER.error(e);
		} finally {
			if (prepare != null) {
				try {
					prepare.close();
				} catch (final Exception e) {
					// LOGGER.error(e);
				}
			}
		}
		return status;
	}

	public static List<Map<String, Object>> executeSelectQuery(Connection con, String query) throws Exception {
		rs = null;
		Statement statement = null;
		List<Map<String, Object>> list = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery(query);
			final ResultSetMetaData md = rs.getMetaData();
			final int columns = md.getColumnCount();
			list = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				final Map<String, Object> row = new HashMap<String, Object>(columns);
				for (int i = 1; i <= columns; ++i) {
					row.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(row);
			}
		} catch (final SQLException e) {
			// LOGGER.error(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final Exception e) {
					// LOGGER.error(e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (final Exception e) {
					// LOGGER.error(e);
				}
			}
		}
		return list;
	}

	public static int getresultSetCount(Connection con, String query) throws Exception {
		int count = 0;
		ResultSet rs = null;
		Statement statement = null;
		try {
			statement = con.createStatement();
			rs = statement.executeQuery(query);
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (final SQLException e) {
			// LOGGER.error(e);
		}

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final Exception e) {
					// LOGGER.error(e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (final Exception e) {
					// LOGGER.error(e);
				}
			}
		}
		return count;
	}

	public static void safelyCloseConnection(Connection con, boolean isException) {
		if (con != null) {
			try {
				if (isException) {
					con.rollback();
				} else {
					con.commit();
				}
			} catch (final Exception ex) {
				// LOGGER.error(ex);
			}
			try {
				con.close();
			} catch (final Exception ex) {
				// LOGGER.error(ex);
			}
		}
	}

}
