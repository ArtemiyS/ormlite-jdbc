package com.j256.ormlite.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.j256.ormlite.TestUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableInfo;

public class SqlServerDatabaseTypeTest extends BaseJdbcDatabaseTypeTest {

	@Override
	protected void setDatabaseParams() throws SQLException {
		databaseUrl = "jdbc:sqlserver:db";
		connectionSource = new JdbcConnectionSource(DEFAULT_DATABASE_URL);
		databaseType = new SqlServerDatabaseType();
	}

	@Override
	protected boolean isDriverClassExpected() {
		return false;
	}

	@Override
	@Test
	public void testEscapedEntityName() throws Exception {
		String word = "word";
		assertEquals("\"" + word + "\"", TestUtils.appendEscapedEntityName(databaseType, word));
	}

	@Override
	@Test
	public void testLimitAfterSelect() throws Exception {
		assertTrue(databaseType.isLimitAfterSelect());
	}

	@Override
	@Test
	public void testLimitFormat() throws Exception {
		Dao<Foo, String> dao;
		try {
			connectionSource.setDatabaseType(databaseType);
			dao = createDao(Foo.class, true);
		} finally {
			connectionSource.setDatabaseType(new H2DatabaseType());
		}
		QueryBuilder<Foo, String> qb = dao.queryBuilder();
		int limit = 1232;
		qb.limit(limit);
		String query = qb.prepareStatementString();
		assertTrue(query + " should start with stuff", query.startsWith("SELECT TOP " + limit + " "));
	}

	@Test
	public void testBoolean() throws Exception {
		TableInfo<AllTypes> tableInfo = new TableInfo<AllTypes>(connectionSource, AllTypes.class);
		assertEquals(9, tableInfo.getFieldTypes().length);
		FieldType booleanField = tableInfo.getFieldTypes()[1];
		assertEquals("booleanField", booleanField.getDbColumnName());
		StringBuilder sb = new StringBuilder();
		List<String> additionalArgs = new ArrayList<String>();
		List<String> statementsBefore = new ArrayList<String>();
		databaseType.appendColumnArg(sb, booleanField, additionalArgs, statementsBefore, null, null);
		assertTrue(sb.toString().contains("BIT"));
	}

	@Test
	public void testByte() throws Exception {
		TableInfo<AllTypes> tableInfo = new TableInfo<AllTypes>(connectionSource, AllTypes.class);
		assertEquals(9, tableInfo.getFieldTypes().length);
		FieldType byteField = tableInfo.getFieldTypes()[3];
		assertEquals("byteField", byteField.getDbColumnName());
		StringBuilder sb = new StringBuilder();
		List<String> additionalArgs = new ArrayList<String>();
		List<String> statementsBefore = new ArrayList<String>();
		databaseType.appendColumnArg(sb, byteField, additionalArgs, statementsBefore, null, null);
		assertTrue(sb.toString().contains("SMALLINT"));
	}

	@Test
	public void testDate() throws Exception {
		TableInfo<AllTypes> tableInfo = new TableInfo<AllTypes>(connectionSource, AllTypes.class);
		assertEquals(9, tableInfo.getFieldTypes().length);
		FieldType byteField = tableInfo.getFieldTypes()[2];
		assertEquals("dateField", byteField.getDbColumnName());
		StringBuilder sb = new StringBuilder();
		List<String> additionalArgs = new ArrayList<String>();
		List<String> statementsBefore = new ArrayList<String>();
		databaseType.appendColumnArg(sb, byteField, additionalArgs, statementsBefore, null, null);
		assertTrue(sb.toString().contains("DATETIME"));
	}

	@Test
	public void testGeneratedIdBuilt() throws Exception {
		TableInfo<GeneratedId> tableInfo = new TableInfo<GeneratedId>(connectionSource, GeneratedId.class);
		assertEquals(2, tableInfo.getFieldTypes().length);
		StringBuilder sb = new StringBuilder();
		List<String> additionalArgs = new ArrayList<String>();
		List<String> statementsBefore = new ArrayList<String>();
		databaseType.appendColumnArg(sb, tableInfo.getFieldTypes()[0], additionalArgs, statementsBefore, null, null);
		assertTrue(sb.toString().contains(" IDENTITY"));
		assertEquals(1, additionalArgs.size());
		assertTrue(additionalArgs.get(0).contains("PRIMARY KEY"));
	}
}
