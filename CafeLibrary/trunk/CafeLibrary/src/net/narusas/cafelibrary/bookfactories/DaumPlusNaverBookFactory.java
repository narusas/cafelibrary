package net.narusas.cafelibrary.bookfactories;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookFactory;
import net.narusas.cafelibrary.BookList;

public class DaumPlusNaverBookFactory extends BookFactory {

	private DaumFetcher daumFetcher;
	private NaverFetcher naverFetcher;

	public DaumPlusNaverBookFactory() {
		super("다음+네이버");
		daumFetcher = new DaumFetcher();
		naverFetcher = new NaverFetcher();
	}

	@Override
	public BookList findBooks(String searchTarget) {
		return new DaumPlusNaverSearchBookList(daumFetcher, naverFetcher, searchTarget);
	}

	@Override
	public Book findSpecificBook(String searchTarget) {
		return findBooks(searchTarget).get(0);
	}

}
