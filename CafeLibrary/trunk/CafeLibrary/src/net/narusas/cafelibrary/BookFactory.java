package net.narusas.cafelibrary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 북을 얻어오는 기능을 수행하는 팩토리 객체.
 * 
 * @author narusas
 * 
 */
public abstract class BookFactory {

	protected static List<BookFactory> plugins = new LinkedList<BookFactory>();
	private static BookFactory instance;
	private String name;

	public BookFactory(String name) {
		this.name = name;
	}

	public static void addPlugin(BookFactory bf) {
		if (plugins.contains(bf) == false) {
			plugins.add(bf);
		}
		instance = bf;
	}

	public static BookFactory getService() {
		return instance;
	}

	public static List<BookFactory> listPlugins() {
		return Collections.unmodifiableList(plugins);
	}

	public String getName() {
		return name;
	}

	/**
	 * 특정 조건을 만족하는 첫번째 북을 반환한다. 일반적으로 ISBN을 검색 대상으로 준다.
	 * 
	 * @param searchTarget
	 * @return
	 */
	public abstract Book findSpecificBook(String searchTarget);

	/**
	 * 조건을 만족하는 북의 목록을 반환한다. 일반적으로 제목이나, 저자명을 준다. 만약 검색에 시간이 걸리는 타입이라면 백그라운드
	 * 쓰래드를 이용하여 지속적으로 BookList에 추가하니, BookListListener를 이용하여 이를 들을수도 있다.
	 * 
	 * @param searchTarget
	 * @return
	 */
	public abstract BookList findBooks(String searchTarget);

}
