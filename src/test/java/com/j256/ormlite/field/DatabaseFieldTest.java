package com.j256.ormlite.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.j256.ormlite.BaseJdbcTest;
import com.j256.ormlite.dao.Dao;

public class DatabaseFieldTest extends BaseJdbcTest {

	@Test
	public void testBaseClassAnnotations() throws Exception {
		Sub sub = new Sub();
		String stuff = "djeqpodjewdopjed";
		sub.stuff = stuff;

		Dao<Sub, Object> subDao = createDao(Sub.class, true);
		assertEquals(0, sub.id);
		assertEquals(1, subDao.create(sub));
		Sub sub2 = subDao.queryForId(sub.id);
		assertNotNull(sub2);
		assertEquals(sub.stuff, sub2.stuff);
	}

	private static class Base {
		@DatabaseField(id = true)
		int id;
		public Base() {
			// for ormlite
		}
	}

	private static class Sub extends Base {
		@DatabaseField
		String stuff;
		public Sub() {
			// for ormlite
		}
	}
}
