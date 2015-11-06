package MongoDBExample.tr.com.java_mongodb;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws UnknownHostException {

		MongoClient mongoClient = new MongoClient("localhost", 27017);

		DB db = mongoClient.getDB("personal");
		/*
		 * authentication is required Begin
		 */

		// boolean authentication = db.authenticate("personal",
		// "123456".toCharArray());

		/*
		 * End
		 */

		/*
		 * Display All Databases Notes:document (data), collection (table)
		 */

		List<String> dbs = mongoClient.getDatabaseNames();

		for (String dbName : dbs) {
			System.out.println("DbName:" + dbName);
		}

		DBCollection table = db.getCollection("mesut");
		System.out.println("tables:" + table);

		/*
		 * Save Example Begin
		 */

		DBCollection tableInsert = db.getCollection("mesut");
		BasicDBObject documentInsert = new BasicDBObject();
		documentInsert.put("name", "mesut");
		documentInsert.put("lastName", "Sarıtaş");
		documentInsert.put("age", 27);
		documentInsert.put("sex", "male");
		tableInsert.insert(documentInsert);

		/*
		 * Save Example End
		 */

		/*
		 * Update Example Begin
		 */

		DBCollection tableUpdate = db.getCollection("mesut");
		BasicDBObject query = new BasicDBObject();
		query.put("name", "mesut");

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", "mesut-updated");

		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);

		tableUpdate.update(query, updateObject);

		/*
		 * Update Example End
		 */

		/*
		 * Find Example Begin
		 */

		DBCollection tableFind = db.getCollection("mesut");
		BasicDBObject searchQuery = new BasicDBObject();

		searchQuery.put("name", "mesut-updated");

		DBCursor cursor = tableFind.find(searchQuery);
		while (cursor.hasNext()) {

			System.out.println("Result:" + cursor.next());

		}

		/*
		 * Find Example End
		 */

		/*
		 * Delete Example Begin
		 */

		DBCollection tableDelete = db.getCollection("mesut");
		BasicDBObject deleteQuery = new BasicDBObject();

		deleteQuery.put("name", "mesut");

		tableDelete.remove(deleteQuery);
		/*
		 * Delete Example End
		 */

	}
}
