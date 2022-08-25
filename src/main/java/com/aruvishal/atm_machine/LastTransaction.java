package com.aruvishal.atm_machine;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;

public class LastTransaction {
	public static void lastTransaction(String sourse_account) throws Exception {
		String query = ("SELECT target_account,source_account,transactional_money,date_time FROM transaction_histry where source_account="
				+ sourse_account + " or target_account=" + sourse_account) + " ORDER BY id DESC LIMIT 10";
		Connection con = DBConnection.getConnection();
		List<Map<String, Object>> result = DBUtil.executeSelectQuery(con, query);
		// Map<String, Object> out = result.get(0);

		// List<Map<String, Object>> employees =
		// template.queryForList(selectSql);

		if (result != null && !result.isEmpty()) {

			for (Map<String, Object> result1 : result) {

				for (Iterator<Map.Entry<String, Object>> it = result1.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					System.out.print(key + " = " + value + "          ");
				}

				System.out.println();

			}

		}

	}

}
