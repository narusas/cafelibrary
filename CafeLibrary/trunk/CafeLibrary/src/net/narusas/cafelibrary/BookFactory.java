package net.narusas.cafelibrary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ���� ������ ����� �����ϴ� ���丮 ��ü.
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
	 * Ư�� ������ �����ϴ� ù��° ���� ��ȯ�Ѵ�. �Ϲ������� ISBN�� �˻� ������� �ش�.
	 * 
	 * @param searchTarget
	 * @return
	 */
	public abstract Book findSpecificBook(String searchTarget);

	/**
	 * ������ �����ϴ� ���� ����� ��ȯ�Ѵ�. �Ϲ������� �����̳�, ���ڸ��� �ش�. ���� �˻��� �ð��� �ɸ��� Ÿ���̶�� ��׶���
	 * �����带 �̿��Ͽ� ���������� BookList�� �߰��ϴ�, BookListListener�� �̿��Ͽ� �̸� �������� �ִ�.
	 * 
	 * @param searchTarget
	 * @return
	 */
	public abstract BookList findBooks(String searchTarget);

}
